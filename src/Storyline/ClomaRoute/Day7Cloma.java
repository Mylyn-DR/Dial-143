package Storyline.ClomaRoute;

import Storyline.*;

public class Day7Cloma implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {};
    private static final SceneEntry[] EVENING = {};
    
    public static SceneEntry[] getGoodEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("The workweek is ending, and everything feels calm. Today will decide how things go with Miss Cloma.", "ParkingArea.jpg"),
            SceneEntry.narrator("You see her across the room, smiling at you as she finishes some paperwork.", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "Miss Cloma! Wait…", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "Oh? Yes?", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "Um… can we talk? Somewhere private?", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "Alright. Come with me.", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.narrator("She leads you to the parking area. The place is calm, away from everyone.", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "Miss Cloma…. Uhmmm… I… I like you.", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "You… do? (She pauses, then smiles shyly.) Actually… I was hoping you'd say that.", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "Huh?", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "Because… I like you too.", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.narrator("You both laugh softly, feeling shy but happy. Your heart races as the moment finally sinks in.", "ParkingArea.jpg"),
            SceneEntry.narrator("Without thinking, you hug her. She gently hugs you back.", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "Maybe… we can spend more time together… outside of work?", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "Yeah… I'd really like that.", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "I don't want this moment to end….", "none", "ParkingArea.jpg"),
            SceneEntry.dialogue("Cloma", "Do you want to… stay a little longer? Maybe just walk around or talk?", "single:Cloma_Smile", "ParkingArea.jpg"),
            SceneEntry.dialogue("{name}", "I'd like that.", "none", "ParkingArea.jpg"),
            SceneEntry.narrator("Instead of going home, you both decide to stay a little longer… talking and enjoying the night together.", "ParkingArea.jpg"),
            SceneEntry.narrator("From that moment on, you start a new chapter… growing closer and learning more about each other.", "ParkingArea.jpg"),
            SceneEntry.narrator("★ CLOMA GOOD ENDING ★", "CLOMA_GOOD.png")
        };
    }
    
    public static SceneEntry[] getNeutralEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("You think about confessing… but in the end, you decide to keep things as they are.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Miss Cloma… It's been really nice spending time with you these past days.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "Yes… It has. I enjoy talking with you.", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Me too.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "I hope we can continue working well together.", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Of course.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You both smile, keeping things light and easy.", "MorningOffice.jpg"),
            SceneEntry.narrator("Even without saying how you truly feel, you're happy… staying by her side as a friend.", "MorningOffice.jpg"),
            SceneEntry.narrator("★ CLOMA NEUTRAL ENDING ★", "CLOMA_NEUTRAL.png")
        };
    }
    
    public static SceneEntry[] getBadEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("You gather your courage and finally speak your feelings.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Miss Cloma… I like you.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "(She pauses, her expression soft but a little sad) I'm sorry… I just don't feel the same way.", "single:Cloma_Smile", "EveningOffice.jpg"),
            SceneEntry.narrator("Your chest feels heavy, but you try not to show it.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "But… I hope we can still be friendly at work.", "single:Cloma_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Of course, Miss Cloma.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Things don't turn out the way you hoped… but you accept it.", "EveningOffice.jpg"),
            SceneEntry.narrator("Some feelings remain unreturned… and slowly fade with time.", "EveningOffice.jpg"),
            SceneEntry.narrator("★ CLOMA BAD ENDING ★", "CLOMA_BAD.png")
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