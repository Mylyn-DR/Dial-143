package Storyline;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import Main.GameAPI.Segment;

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
    
    private DayInterface         dayScript;
    private SceneManagerDelegate delegate;
    private Segment              segment    = Segment.MORNING;
    private int                  sceneIndex = 0;
    private int                  subSceneIndex = 0;
    
    private String choiceID = "null";
    private String chosenChoiceID = "null";
    
    private final ArrayList<SceneEntry> subSceneQueue = new ArrayList<>();
    private RouteManager activeRoute, lpStorage;

    /** Original constructor for backward compatibility */
    public SceneManager(DayInterface dayScript, SceneManagerDelegate delegate) {
        this(dayScript, delegate, null, null);
    }
    
    /** New constructor with RouteManager support */
    public SceneManager(DayInterface dayScript, SceneManagerDelegate delegate, RouteManager activeRoute, RouteManager lpStorage) {
        this.dayScript = dayScript;
        this.delegate  = delegate;
        this.activeRoute = activeRoute;
        this.lpStorage = lpStorage;
    }
    
    public SceneManager(SceneManagerDelegate delegate, RouteManager activeRoute, RouteManager lpStorage) {
        this(null, delegate, activeRoute, lpStorage);
    }

    public void start(){
        if (choiceID.equalsIgnoreCase("null")){
            subSceneQueue.clear();
            loadScene(sceneIndex++);
        } else {
            ChoiceEntry[] choices = dayScript.getChoicesForMarker(choiceID);
            for (ChoiceEntry c : choices) {
                if (c.label.equals(chosenChoiceID)) {
                    onChoicePicked(c);
                    advanceScene();
                    break;
                }
            }
        }
    }
    
    public void clear(){
        System.out.println("Cleared");
        subSceneQueue.clear();
        subSceneIndex = 0;
        sceneIndex = 0; 
        choiceID = "null";
        chosenChoiceID = "null";
        dayScript = null;
        lpStorage = null;
        activeRoute = null;
    }

    /** Called on every player click. Drains sub-scenes before advancing main index. */
    public void advanceScene() {
        if (subSceneQueue.size() > subSceneIndex && !subSceneQueue.isEmpty()) {
            System.out.println("--------Subscene: " + subSceneIndex + "/"+ subSceneQueue.size());
            playEntry(subSceneQueue.get(subSceneIndex++));
        } else {
            sceneIndex++;
            choiceID = "null";
            chosenChoiceID = "null";
            subSceneIndex = 0;
            subSceneQueue.clear();
            loadScene(sceneIndex);
        }
    }
    
    public String getChosenChoiceID(){ return chosenChoiceID; }
    public void setChosenChoiceID(String id){ chosenChoiceID = id; }
    public String getChoiceID(){ return choiceID; }
    public int getSubSceneIndex(){ return subSceneIndex; }
    public void setChoiceID(String choiceID){ this.choiceID = choiceID; }
    public void setSubSceneIndex(int idx){ this.subSceneIndex = idx; }
    private SceneEntry[] getCurrentScenes() {
        return switch (segment) {
            case MORNING -> dayScript.getMorningScenes();
            case AFTERNOON -> dayScript.getAfternoonScenes();
            case EVENING -> dayScript.getEveningScenes();
            case ENDING -> activeRoute.getEndingScene(lpStorage.getActiveLP());
        };
    }
    
    public void setDayScript(DayInterface dayScript) { this.dayScript = dayScript; }
    public void setActiveRoute(RouteManager rm){ activeRoute = rm; }
    public void setLPStorage(RouteManager rm){ lpStorage = rm; }
    
    /** Called by InteractionPanel after a choice is selected. */
    public void onChoicePicked(ChoiceEntry chosen) {
        chosen.lpRewards.forEach((character, amount) -> delegate.addLP(amount, character));
        chosenChoiceID = chosen.label;
        System.out.println("Selected: " + chosenChoiceID);
        subSceneQueue.clear();
        if (chosen.subScenes != null) {
            System.out.println("subscene load");
            for (SceneEntry s : chosen.subScenes) subSceneQueue.add(s);
        }
        advanceScene();
    }

    public int     getCurrentSceneIndex() { return sceneIndex; }
    public Segment getCurrentSegment()    { return segment;    }
    public void    setCurrentSegment(Segment segment){ this.segment = segment; }
    public void setCurrentSceneIndex(int idx){ sceneIndex = idx; }

    private void loadScene(int index) {
        SceneEntry[] scenes = getCurrentScenes();
        System.out.println("!!!!!!!!!!!!!!! SCENE INDEX: " + index);
        if (index >= scenes.length) {
            delegate.onSceneEnd();
            if (segment == Segment.MORNING) delegate.onMorningComplete();
            else if (segment == Segment.AFTERNOON) delegate.onAfternoonComplete();
            else if (segment == Segment.EVENING) delegate.onEveningComplete();
            else delegate.onEndingComplete();
            return; 
        }
        playEntry(scenes[index]);
    }


    private void playEntry(SceneEntry scene) {
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
                ChoiceEntry[] choices = dayScript.getChoicesForScene(sceneIndex, segment.name());
                if (choices == null) {
                    advanceScene();
                    return;
                }
                delegate.showChoices(choices, () ->
                    SwingUtilities.invokeLater(this::advanceScene));
            }
            case SceneEntry.TYPE_CHOICE_MARKER -> {
                choiceID = scene.choiceId;
                System.out.println("Choice marker encountered: " + choiceID);
                ChoiceEntry[] choices = dayScript.getChoicesForMarker(choiceID);
                subSceneIndex = 0;
                if (choices == null) {
                    System.out.println("No choices found for marker: " + choiceID);
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

}