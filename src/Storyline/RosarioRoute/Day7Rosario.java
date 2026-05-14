package Storyline.RosarioRoute;

import Storyline.*;

public class Day7Rosario implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {};
    private static final SceneEntry[] EVENING = {};
    
    // ── Good Ending (60+ LP) ───────────────────────────────────────────────────
    public static SceneEntry[] getGoodEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("Another overnight at the office…. But this time feels different.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "You're still here?", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Yeah… you too.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Want some coffee?", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Hmm…. maybe next time.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Soft music starts playing from somewhere.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Looks like the speakers are still on.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.narrator("He smiles and looks at you.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Can I have this dance?", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Here? Seriously?", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Just for a while.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.narrator("He offers his hand.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "….Okay.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You both slowly dance.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "This feels…. Cute.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I'm glad I'm with you.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Me too.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Instead of coffee, the night ends with a quiet dance… just the two of you.", "EveningOffice.jpg"),
            SceneEntry.narrator("★ ROSARIO GOOD ENDING ★", "ROSARIO_GOOD.png")
        };
    }
    
    // ── Neutral Ending (40-59 LP) ─────────────────────────────────────────────
    public static SceneEntry[] getNeutralEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("Another long day. You and Rosario stay a bit longer to finish work.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Coffee?", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Yeah, I need that.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You both sit down with coffee.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "You've been doing well lately.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Thanks. I'm trying my best.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I'm glad we're working together.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Me too.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You both smile.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Maybe….. This is okay for now.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You spend the night talking and laughing…. As good friends.", "EveningOffice.jpg"),
            SceneEntry.narrator("★ ROSARIO NEUTRAL ENDING ★", "ROSARIO_NEUTRAL.png")
        };
    }
    
    // ── Bad Ending (Below 40 LP) ──────────────────────────────────────────────
    public static SceneEntry[] getBadEnding() {
        return new SceneEntry[] {
            SceneEntry.narrator("A new day at the office.", "MorningOffice.jpg"),
            SceneEntry.narrator("Rosario walks past you.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Good morning.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Good morning…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Please send the report later.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Okay.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He nods and leaves.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "It feels like he's… distant.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("Later.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Good work.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Thank you.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He gives a small nod, then walks away.", "MorningOffice.jpg"),
            SceneEntry.narrator("He still talks to you… but it feels cold. Like something is missing.", "MorningOffice.jpg"),
            SceneEntry.narrator("★ ROSARIO BAD ENDING ★", "ROSARIO_BAD.png")
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