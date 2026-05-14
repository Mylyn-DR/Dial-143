package Storyline.ClomaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day3Cloma implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {}; 
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The rain pours heavily across the streets.", "StreetEvening.jpg"),
        SceneEntry.narrator("After a long day, emotions feel a little heavier than usual.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "PC?", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Oh… hi, Amaya.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "It's raining… how are we getting home?", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "You have an umbrella, right?", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Uh… I don't have one. What about you?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Me neither! What now?", "single:Amaya_Mad", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Maybe… we can just run?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Run? Are you serious? It's raining heavily!", "single:Amaya_Mad", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Hey… it's not that bad.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Not that bad? My shoes are already soaked!", "single:Amaya_Mad", "StreetEvening.jpg"),
        SceneEntry.narrator("The two of you stand there, unsure what to do.", "StreetEvening.jpg"),
        SceneEntry.narrator("Suddenly, someone walks out of the building.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Oh—Miss Cloma is looking at us… And she's coming this way.", "single:Amaya_Surprised", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Really?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "Hi there. Are you two arguing?", "single:Cloma_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "I saw you from inside… Looks like you don't have an umbrella.", "single:Cloma_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Uh… hello, Miss Cloma…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "We were just figuring out how to get home without getting soaked.", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "I see. Here—you can use my umbrella.", "single:Cloma_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "What about you?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "Don't worry, I still have another one in my office. Actually… I can give you two a ride.", "single:Cloma_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Oh! Thank you, Miss, but I'm fine. I need to stop by somewhere.", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "I see. How about you?", "single:Cloma_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "(whispers) I can tell you like her. I've known you for so long—go for it!", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Cloma", "Here, take my umbrella too, Amaya.", "single:Cloma_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Thank you so much, Miss!", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("Amaya smiles, waves, and walks away.", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY3_EVENING_CHOICE"),
        SceneEntry.narrator("The rain slowly calms as you arrive home.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(Today felt different…)", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("You think about Miss Cloma—her kindness, her smile.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(Maybe… I'll get another chance to talk to her like that again.)", "none", "StreetEvening.jpg"),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Accept the ride", 10, 0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Uh… okay. Thank you, Miss Cloma.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Cloma", "Alright. Come with me.", "single:Cloma_Smile", "StreetEvening.jpg"),
            SceneEntry.narrator("You step into the car as the rain continues outside.", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "The rain is really heavy today.", "single:Cloma_Casual", "INT_CAR"),
            SceneEntry.dialogue("{name}", "Yeah… I didn't expect someone to be as kind as you.", "none", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "Sometimes people just want to help. Especially someone who works hard—like you.", "single:Cloma_Smile", "INT_CAR"),
            SceneEntry.dialogue("{name}", "Me?", "none", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "Yes. I've noticed how hardworking and polite you are.", "single:Cloma_Smile", "INT_CAR"),
            SceneEntry.dialogue("{name}", "Oh… I didn't think anyone noticed.", "none", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "I do. Little things matter.", "single:Cloma_Smile", "INT_CAR"),
            SceneEntry.narrator("The car moves slowly through the rain.", "INT_CAR"),
            SceneEntry.dialogue("{name}", "You're really kind, Miss Cloma.", "none", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "And you're really sweet.", "single:Cloma_Smile", "INT_CAR"),
            SceneEntry.narrator("After a few minutes, you arrive.", "INT_CAR"),
            SceneEntry.dialogue("{name}", "We're here.", "none", "INT_CAR"),
            SceneEntry.dialogue("Cloma", "Take care. Don't catch a cold.", "single:Cloma_Smile", "INT_CAR"),
            SceneEntry.dialogue("{name}", "Thank you so much.", "none", "INT_CAR"),
            SceneEntry.narrator("You step out of the car, your heart still racing.", "EXT_HOME")
        ),
        new ChoiceEntry("Decline politely", -5, 3, Character.CLOMA,
            SceneEntry.dialogue("{name}", "No, it's okay… I'll be fine.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Cloma", "Are you sure?", "single:Cloma_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Yes. Thank you, Miss.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("Miss Cloma nods and walks away.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Why didn't you accept?", "single:Amaya_Playful", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I'm shy…", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Haha! You're impossible!", "single:Amaya_Smile", "StreetEvening.jpg")
        ),
    };
    
    public SceneEntry[] getMorningScenes() { return MORNING; }
    public SceneEntry[] getAfternoonScenes() { return new SceneEntry[0]; }
    public SceneEntry[] getEveningScenes() { return EVENING; }
    
    private static final HashMap<String, ChoiceEntry[]> choiceMap = new HashMap<>();
    static {
        choiceMap.put("CLOMA_DAY3_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day3Cloma.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}