package Storyline.CeleresRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day6Celeres implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("The morning sun streams through the office windows.", "MorningOffice.jpg"),
        SceneEntry.narrator("Today is the big company party, and everyone is busy preparing.", "MorningOffice.jpg"),
        SceneEntry.narrator("Decorations are being set up, tables arranged, and the staff bustling around with excitement.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) This is going to be fun… but also nerve-wracking.", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You notice Celeres quietly organizing the technical setup.", "MorningOffice.jpg"),
        SceneEntry.narrator("True to his calm style, he seems unfazed by all the commotion.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY6_MORNING_CHOICE")
    };

    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The awarding ceremony has ended, and the place feels completely different tonight.", "EventStage.jpg"),
        SceneEntry.narrator("Music fills the air, lights glow warmly, and everyone is laughing and talking.", "EventStage.jpg"),
        SceneEntry.narrator("You are standing near the drink table when you see Celeres walking toward you.", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Why is my heart beating faster…?", "none", "EventStage.jpg"),
        SceneEntry.narrator("He stops in front of you, looking a little nervous.", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "Hey…", "single:Celeres_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "Hi.", "none", "EventStage.jpg"),
        SceneEntry.narrator("He scratches the back of his neck, clearly trying to find the right words.", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "Can you… be my partner for the night?", "single:Celeres_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Did he really just ask me that…?", "none", "EventStage.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY6_EVENING_CHOICE"),
        SceneEntry.narrator("The party is over. Music fades into the distance, and the office is quiet again.", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Being with him tonight… felt perfect.", "none", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "(mind) I want more nights like this.", "none", "EventStage.jpg"),
        SceneEntry.narrator("You step into the cool night air, a warm feeling lingering in your chest.", "StreetEvening.jpg"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Offer Help With Setup", 5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "Need a hand with the setup?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Uh… sure, I guess. Don't get in my way.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.narrator("You work side by side, sharing quiet moments of laughter as preparations continue.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Focus On Your Tasks", 0, 10,
            SceneEntry.dialogue("{name}", "(mind) Maybe I'll just focus on my own things…", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("Celeres glances at you occasionally, his sleepy gaze softening.", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Happily Accept His Offer", 10, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "(Smiling) I'd love to.", "none", "EventStage.jpg"),
            SceneEntry.narrator("Celeres exhales a breath he didn't realize he was holding.", "EventStage.jpg"),
            SceneEntry.dialogue("Celeres", "…I was hoping you'd say that.", "single:Celeres_Smile", "EventStage.jpg"),
            SceneEntry.dialogue("{name}", "Were you nervous to ask me?", "none", "EventStage.jpg"),
            SceneEntry.dialogue("Celeres", "A little.", "single:Celeres_Smile", "EventStage.jpg"),
            SceneEntry.narrator("He gives a small, shy smile.", "EventStage.jpg"),
            SceneEntry.dialogue("Celeres", "Come on… let's enjoy the party.", "single:Celeres_Smile", "EventStage.jpg"),
            SceneEntry.narrator("The two of you spend the night together—talking, laughing, and staying close the entire time.", "EventStage.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Being beside him like this… I don't want this night to end.", "none", "EventStage.jpg")
        ),
        new ChoiceEntry("Decline And Mingle Elsewhere", -50, 10, Character.CELERES,
            SceneEntry.dialogue("{name}", "We don't need partners for this party… you should go mingle with the others.", "none", "EventStage.jpg"),
            SceneEntry.narrator("Celeres goes quiet for a moment.", "EventStage.jpg"),
            SceneEntry.narrator("His shoulders slowly drop.", "EventStage.jpg"),
            SceneEntry.dialogue("Celeres", "…Okay.", "single:Celeres_Sad", "EventStage.jpg"),
            SceneEntry.narrator("He gives a small nod before turning away and disappearing into the crowd.", "EventStage.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Why does it suddenly feel colder here…?", "none", "EventStage.jpg")
        )
    };
    
    @Override
    public SceneEntry[] getMorningScenes() { return MORNING; }
    @Override
    public SceneEntry[] getAfternoonScenes() { return AFTERNOON; }  
    @Override
    public SceneEntry[] getEveningScenes() { return EVENING; }

    private static final HashMap<String, ChoiceEntry[]> choiceMap = new HashMap<>();
    static {
        choiceMap.put("CELERES_DAY6_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CELERES_DAY6_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day6Celeres.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}