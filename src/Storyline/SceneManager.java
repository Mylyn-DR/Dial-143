package Storyline;

import Main.GameAPI;
import javax.swing.SwingUtilities;
import java.util.ArrayDeque;
import java.util.Deque;

public class SceneManager {

    public interface SceneManagerDelegate {
        void showBackground(String filename);
        void showSprite(String spriteSpec);
        void showDialogue(String speaker, String text);
        void showNarrator(String text);
        void showChoices(ChoiceEntry[] choices, Runnable onChosen);
        void showIdentityCreation(Runnable onComplete);
        void showRouteSelection();
        void addPP(int amount);
        void addLP(int amount, String lpCharacter);
        void onMorningComplete();
        void onAfternoonComplete();
        void onEveningComplete();
        void onEndingComplete();
        void waitForPreload();
        void onSceneEnd();
        int getPlayerPP();
    }

    public enum Segment { MORNING, AFTERNOON, EVENING, ENDING }

    // ── Dependencies ─────────────────────────────────────────────────────────
    private final GameAPI gameAPI;
    private DayInterface dayScript;
    private SceneManagerDelegate delegate;
    private RouteManager activeRoute, lpStorage;

    // ── Sub-scene queue (temporary, not saved) ───────────────────────────────
    private final Deque<SceneEntry> subSceneQueue = new ArrayDeque<>();

    // ── Constructor ──────────────────────────────────────────────────────────
    public SceneManager(GameAPI gameAPI, DayInterface dayScript, 
                        SceneManagerDelegate delegate,
                        RouteManager activeRoute, RouteManager lpStorage) {
        this.gameAPI = gameAPI;
        this.dayScript = dayScript;
        this.delegate = delegate;
        this.activeRoute = activeRoute;
        this.lpStorage = lpStorage;
    }

    // ── Public API ───────────────────────────────────────────────────────────
    public void setDayScript(DayInterface dayScript) {
        this.dayScript = dayScript;
    }

    public void setRouteManager(RouteManager rm) {
        this.activeRoute = rm;
    }
    
    public void setActiveRoute(RouteManager route) {
        this.activeRoute = route;
    }

    public void start(Segment segment, int startIndex) {
        // SYNC: Write to GameState
        gameAPI.setCurrentSegment(convertSegment(segment));
        gameAPI.setDialogueScene(startIndex);
        
        subSceneQueue.clear();
        delegate.waitForPreload();
        loadScene(startIndex);
    }

    public void advanceScene() {
        if (!subSceneQueue.isEmpty()) {
            playEntry(subSceneQueue.poll());
        } else {
            int nextIndex = gameAPI.getDialogueScene() + 1;
            gameAPI.setDialogueScene(nextIndex);
            loadScene(nextIndex);
        }
    }

    public void onChoicePicked(ChoiceEntry chosen) {
        // Apply PP reward (if any)
        if (chosen.ppReward != 0) {
            delegate.addPP(chosen.ppReward);
        }
        // Apply LP rewards
        chosen.lpRewards.forEach((character, amount) -> delegate.addLP(amount, character));

        subSceneQueue.clear();
        if (chosen.subScenes != null) {
            for (SceneEntry s : chosen.subScenes) {
                subSceneQueue.add(s);
            }
        }

        if (!subSceneQueue.isEmpty()) {
            playEntry(subSceneQueue.poll());
        } else {
            SwingUtilities.invokeLater(this::advanceScene);
        }
    }

    public int getCurrentSceneIndex() {
        return gameAPI.getDialogueScene();
    }

    public Segment getCurrentSegment() {
        return convertSegment(gameAPI.getCurrentSegment());
    }

    public void playEndingScenes(SceneEntry[] endingScenes) {
        if (endingScenes == null || endingScenes.length == 0) return;
        subSceneQueue.clear();
        for (SceneEntry scene : endingScenes) {
            subSceneQueue.add(scene);
        }
        playEntry(subSceneQueue.poll());
    }

    public boolean isQueueEmpty() {
        return subSceneQueue.isEmpty();
    }

    // ── Internal ─────────────────────────────────────────────────────────────
    private void loadScene(int index) {
        SceneEntry[] scenes = getCurrentScenes();
        System.out.println("Loading scene index: " + index + " of " + scenes.length);
        
        if (index >= scenes.length) {
            delegate.onSceneEnd();
            
            Segment currentSeg = getCurrentSegment();
            switch (currentSeg) {
                case MORNING -> delegate.onMorningComplete();
                case AFTERNOON -> delegate.onAfternoonComplete();
                case EVENING -> delegate.onEveningComplete();
                case ENDING -> delegate.onEndingComplete();
            }
            return;
        }
        playEntry(scenes[index]);
    }

    private SceneEntry[] getCurrentScenes() {
        Segment currentSeg = getCurrentSegment();
        
        return switch (currentSeg) {
            case MORNING -> dayScript.getMorningScenes();
            case AFTERNOON -> dayScript.getAfternoonScenes();
            case EVENING -> dayScript.getEveningScenes();
            case ENDING -> {
                if (activeRoute != null) {
                    yield activeRoute.getEndingScene(lpStorage.getActiveLP());
                } else {
                    yield new SceneEntry[0];
                }
            }
        };
    }

    private void playEntry(SceneEntry scene) {
        if (scene == null) {
            advanceScene();
            return;
        }

        switch (scene.type) {
            case SceneEntry.TYPE_NARRATOR -> {
                delegate.showBackground(scene.background);
                delegate.showSprite("none");
                delegate.showNarrator(scene.text);
            }

            case SceneEntry.TYPE_DIALOGUE -> {
                delegate.showBackground(scene.background);
                delegate.showSprite(scene.spriteSpec);
                delegate.showDialogue(scene.speaker, scene.text);
            }

            case SceneEntry.TYPE_IDENTITY -> {
                delegate.showIdentityCreation(this::advanceScene);
            }

            case SceneEntry.TYPE_CHOICE -> {
                ChoiceEntry[] choices = dayScript.getChoicesForScene(
                    getCurrentSceneIndex(), 
                    getCurrentSegment().name()
                );
                if (choices == null) {
                    advanceScene();
                    return;
                }
                delegate.showChoices(choices, () ->
                    SwingUtilities.invokeLater(this::advanceScene));
            }

            case SceneEntry.TYPE_CHOICE_MARKER -> {
                ChoiceEntry[] choices = dayScript.getChoicesForMarker(scene.choiceId);
                if (choices == null) {
                    advanceScene();
                    return;
                }
                delegate.showChoices(choices, () ->
                    SwingUtilities.invokeLater(this::advanceScene));
            }

            case SceneEntry.TYPE_ROUTE -> {
                delegate.showRouteSelection();
            }

            default -> advanceScene();
        }
    }

    // ── Helper: Convert between GameAPI.Segment and SceneManager.Segment ─────
    private Segment convertSegment(GameAPI.Segment apiSegment) {
        return switch (apiSegment) {
            case MORNING -> Segment.MORNING;
            case AFTERNOON -> Segment.AFTERNOON;
            case EVENING -> Segment.EVENING;
            case ENDING -> Segment.ENDING;
        };
    }

    private GameAPI.Segment convertSegment(Segment smSegment) {
        return switch (smSegment) {
            case MORNING -> GameAPI.Segment.MORNING;
            case AFTERNOON -> GameAPI.Segment.AFTERNOON;
            case EVENING -> GameAPI.Segment.EVENING;
            case ENDING -> GameAPI.Segment.ENDING;
        };
    }
}