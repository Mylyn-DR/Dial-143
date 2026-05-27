package Storyline.CeleresRoute;

import Storyline.*;

public class Day7Celeres implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {};
    private static final SceneEntry[] EVENING = {};
    
    public static SceneEntry[] getGoodEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("The workweek is ending, and everything feels calm.", "MorningOffice.jpg"),
            SceneEntry.narrator("You see him across the room, quietly finishing his work before looking up and meeting your eyes.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I like being with you. The noisy office feels calm when you're around.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I'm not good with words… but I want to keep spending time with you.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I'd like that too.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Then… let's take things slow.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Okay.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("From that day on, the two of you continue learning more about each other and slowly grow closer.", "MorningOffice.jpg"),
            SceneEntry.narrator("★ CELERES GOOD ENDING ★", "CELERES_GOOD.png")
        };
    }
    
    public static SceneEntry[] getNeutralEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("The workweek is ending. You decide to speak your feelings, but the outcome is uncertain.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Celeres… I think I like you.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…I didn't expect that.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I just wanted to be honest.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I appreciate it… But I think we're better as friends.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "That's okay.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You both remain casual friends, keeping things light and friendly at work.", "MorningOffice.jpg"),
            SceneEntry.narrator("★ CELERES NEUTRAL ENDING ★", "CELERES_NEUTRAL.png")
        };
    }
    
    public static SceneEntry[] getBadEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("The workweek is ending, but tonight's words feel heavy.", "MorningOffice.jpg"),
            SceneEntry.narrator("You decide to confess anyway.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Celeres… I like you.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…I'm sorry.", "single:Celeres_Sad", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Oh…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I don't feel the same.", "single:Celeres_Sad", "MorningOffice.jpg"),
            SceneEntry.narrator("After that, things become distant between you.", "MorningOffice.jpg"),
            SceneEntry.narrator("Now Celeres only talks to you when your PC needs fixing.", "MorningOffice.jpg"),
            SceneEntry.narrator("★ CELERES BAD ENDING ★", "CELERES_BAD.png")
        };
    }
    
    @Override
    public SceneEntry[] getMorningScenes() { return MORNING; }
    @Override
    public SceneEntry[] getAfternoonScenes() { return AFTERNOON; }
    @Override
    public SceneEntry[] getEveningScenes() { return EVENING; }
    
    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        return null; 
    }
}