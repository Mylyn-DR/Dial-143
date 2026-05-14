package Storyline.ClomaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day6Cloma implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("The office is buzzing this morning.", "MorningOffice.jpg"),
        SceneEntry.narrator("Today is the big company celebration.", "MorningOffice.jpg"),
        SceneEntry.narrator("Everyone is busy setting up decorations, arranging tables, and checking the sound system.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Alright… everything looks amazing. I just hope tonight goes well.", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You notice Cloma quietly reviewing the seating arrangements and ensuring everything is perfect.", "MorningOffice.jpg"),
        SceneEntry.narrator("Even in her calm and friendly manner, she's clearly focused.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY6_MORNING_CHOICE"),
    };

    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("After the awarding, everyone gathers for dinner.", "IntRestaurant.jpg"),
        SceneEntry.narrator("You sit at a table with Amaya.", "IntRestaurant.jpg"),
        SceneEntry.dialogue("Amaya", "Congrats again! We both got awards!", "single:Amaya_Smile", "IntRestaurant.jpg"),
        SceneEntry.dialogue("{name}", "Thanks! You too.", "none", "IntRestaurant.jpg"),
        SceneEntry.narrator("Suddenly, Miss Cloma approaches your table.", "IntRestaurant.jpgv"),
        SceneEntry.dialogue("Cloma", "Hello. Is anyone sitting next to you?", "single:Cloma_Smile", "IntRestaurant.jpg"),
        SceneEntry.dialogue("{name}", "No… it's okay.", "none", "IntRestaurant.jpg"),
        SceneEntry.dialogue("Cloma", "Mind if I join you?", "single:Cloma_Smile", "IntRestaurant.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY6_EVENING_CHOICE"),
        SceneEntry.narrator("The dinner continues.", "IntRestaurant.jpg"),
        SceneEntry.narrator("You laugh, talk, and enjoy the night.", "IntRestaurant.jpg"),
        SceneEntry.narrator("Tonight feels special… like the start of something more.", "IntRestaurant.jpg"),
        SceneEntry.narrator("The day slowly ends, leaving memories of pride, excitement, and a little spark between you and Miss Cloma.", "IntRestaurant.jpg"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Offer To Help Cloma", 5, 5, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Need a hand with that, Miss Cloma?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "Oh! That would be really helpful, thank you.", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("You help her set up decorations and organize the awards.", "MorningOffice.jpg"),
            SceneEntry.narrator("She glances at you with a warm smile, and your chest feels lighter.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Focus On Own Tasks", 0,10,
            SceneEntry.dialogue("{name}", "(mind) Maybe I should handle my own preparations…", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You get to work, but you can't help noticing Cloma moving gracefully around the room, calm and composed.", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Invite Her To Sit", 10,5, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Of course, please sit here.", "none", "IntRestaurant.jpg"),
            SceneEntry.dialogue("Cloma", "Thank you.", "single:Cloma_Smile", "IntRestaurant.jpg"),
            SceneEntry.narrator("Your heart races as she sits across from you.", "IntRestaurant.jpg")
        ),
        new ChoiceEntry("Politely Hesitate First", 5,0,  Character.CLOMA,
            SceneEntry.dialogue("{name}", "Um… sure, I guess it's fine.", "none", "IntRestaurant.jpg"),
            SceneEntry.dialogue("Cloma", "(smiles) Thank you.", "single:Cloma_Smile", "IntRestaurant.jpg"),
            SceneEntry.narrator("She sits, and you notice her gentle expression.", "IntRestaurant.jpg")
        ),
        new ChoiceEntry("Say No Politely", -5,-2,  Character.CLOMA,
            SceneEntry.dialogue("{name}", "Actually… maybe another time?", "none", "IntRestaurant.jpg"),
            SceneEntry.dialogue("Cloma", "Oh… okay.", "single:Cloma_Casual", "IntRestaurant.jpg"),
            SceneEntry.narrator("She walks away, leaving a small sting of disappointment.", "IntRestaurant.jpg")
        ),
    };
    
    
    @Override
    public SceneEntry[] getMorningScenes() { return MORNING; }
    @Override
    public SceneEntry[] getAfternoonScenes() { return AFTERNOON; }
    @Override
    public SceneEntry[] getEveningScenes() { return EVENING; }
    
    private static final HashMap<String, ChoiceEntry[]> choiceMap = new HashMap<>();
    static {
        choiceMap.put("CLOMA_DAY6_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CLOMA_DAY6_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day6Cloma.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}