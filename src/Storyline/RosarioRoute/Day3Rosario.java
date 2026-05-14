package Storyline.RosarioRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day3Rosario implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {}; 
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("You suddenly have the urge to see him.", "StreetEvening.jpg"),
        SceneEntry.narrator("But you quickly shake that thought away, knowing Rosario might not tolerate something like this.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(scratches head in frustration)", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Argh… what am I thinking?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "I can't be crushing on my supervisor like this.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "I'm not in high school anymore!", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("As you stand on the pavement, conflicted, you overhear a familiar voice across the street.", "StreetEvening.jpg"),
        SceneEntry.narrator("You turn—and to your surprise, you see Rosario walking with someone.", "StreetEvening.jpg"),
        SceneEntry.narrator("You squint your eyes.", "StreetEvening.jpg"),
        SceneEntry.narrator("It's Amaya.", "StreetEvening.jpg"),
        SceneEntry.narrator("Your best friend.", "StreetEvening.jpg"),
        SceneEntry.narrator("And somehow… you don't like what you're seeing.", "StreetEvening.jpg"),
        SceneEntry.narrator("Not just because they're together—but because Rosario looks relaxed with her.", "StreetEvening.jpg"),
        SceneEntry.narrator("A bitter feeling forms in your chest.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "It's fine…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "They're probably just close…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Amaya's my best friend.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Rosario's just my supervisor…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "I shouldn't feel like this.", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("But your hands clench.", "StreetEvening.jpg"),
        SceneEntry.narrator("Your feet shift restlessly, unsure whether to move closer… or walk away.", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY3_CHOICE_FIRST"),
        SceneEntry.narrator("You look at Rosario nervously.", "StreetEvening.jpg"),
        SceneEntry.narrator("He lets out a small laugh.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "You are a strange person…", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "But not in a bad way.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("He smiles.", "StreetEvening.jpg"),
        SceneEntry.narrator("Then, he reaches into his pocket.", "StreetEvening.jpg"),
        SceneEntry.narrator("A chocolate bar.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Treat yourself.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "You've been working hard.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("You stare at the chocolate, then back at him.", "StreetEvening.jpg"),
        SceneEntry.narrator("Before you can respond, he nods and walks away.", "StreetEvening.jpg"),
        SceneEntry.narrator("You remain there, trying to process everything.", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY3_CHOICE_THIRD"),
        SceneEntry.narrator("You collapse onto your bed as soon as you get home.", "Bedroom.jpg"),
        SceneEntry.narrator("You replay everything that happened today.", "Bedroom.jpg"),
        SceneEntry.narrator("But the image of Rosario and Amaya together…", "Bedroom.jpg"),
        SceneEntry.narrator("It still bothers you.", "Bedroom.jpg"),
        SceneEntry.narrator("But what Rosario did somehow makes up for everything.", "Bedroom.jpg"),
        SceneEntry.narrator("And for some reason… you want more moments like that.", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "Too short…", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "We should've talked more.", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "Wait… what am I even saying?", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "Sigh…", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "Rosario can't just be a happy crush if it makes me jealous of my best friend.", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "But he's admirable…", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "As a leader… as a coworker…", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "But as a person?", "none", "Bedroom.jpg"),
        SceneEntry.dialogue("{name}", "He confuses me.", "none", "Bedroom.jpg"),
        SceneEntry.choiceMarker("ROSAIRO_DAY3_CHOICE_FOURTH"),
        SceneEntry.narrator("Exhaustion slowly pulls you to sleep.", "Bedroom.jpg"),
        SceneEntry.narrator("The night comes to an end.", "Bedroom.jpg"),
    };
    
    // First choice - Run after them or not
    private static final ChoiceEntry[] CHOICES_FIRST = {
        new ChoiceEntry("Run after them", 5, 0, Character.ROSARIO,
            SceneEntry.narrator("Before you can change your mind, your feet move.", "StreetEvening.jpg"),
            SceneEntry.narrator("You jog toward them.", "StreetEvening.jpg"),
            SceneEntry.narrator("The quiet street makes their voices clear", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "…It was pretty expensive, but worth the money.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Uh-huh…", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Oh! That's my bus!", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Gotta go! Bye, Rosario!", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Goodbye. Take care.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.narrator("You slow down as Amaya boards the bus.", "StreetEvening.jpg"),
            SceneEntry.narrator("Rosario begins to turn toward you.", "StreetEvening.jpg"),
            SceneEntry.narrator("Panic sets in.", "StreetEvening.jpg"),
            SceneEntry.choiceMarker("ROSARIO_DAY3_CHOICE_SECOND")
        ),
    };
    
    // Second choice - After being seen
    private static final ChoiceEntry[] CHOICES_SECOND = {
        new ChoiceEntry("Hide behind a post", 2,0, Character.ROSARIO,
            SceneEntry.narrator("You quickly hide behind a post—though not very effectively.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "You're not very discreet.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Show yourself.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.narrator("You step out awkwardly.", "StreetEvening.jpg"),
            SceneEntry.narrator("Rosario's eyes widen—then he chuckles.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Why are you hiding?", "single:Rosario_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I… I just saw you and got nervous.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("He chuckles softly, keeping a bit of distance.", "StreetEvening.jpg")
        ),
        new ChoiceEntry("Stay frozen", 3,0, Character.ROSARIO,
            SceneEntry.narrator("You stay in place, frozen.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Why are you here?", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Uh… just getting some fresh air.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Fresh air…", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "But you look out of breath.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Uh… I just took a small jog.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "I see.", "single:Rosario_Casual", "StreetEvening.jpg")
        ),
        new ChoiceEntry("Run back home", -5,0, Character.ROSARIO,
            SceneEntry.narrator("You turn and run—", "StreetEvening.jpg"),
            SceneEntry.narrator("THWACK!", "StreetEvening.jpg"),
            SceneEntry.narrator("You trip.", "StreetEvening.jpg"),
            SceneEntry.narrator("Before you hit the ground, someone catches you.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Careful. Are you okay?", "single:Rosario_Soft", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Oh… I think so…", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("You try to stand, but lose balance again.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Your foot might be hurt.", "single:Rosario_Concerned", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Try to rest it for a while.", "single:Rosario_Concerned", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Okay… thank you.", "none", "StreetEvening.jpg")
        ),
    };
    
    // Third choice - After chocolate
    private static final ChoiceEntry[] CHOICES_THIRD = {
        new ChoiceEntry("Go straight home", 0,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "No… what am I thinking?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I should just go home.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("You walk quickly without looking back.", "StreetEvening.jpg")
        ),
    };
    
    // Fourth choice - End of night reflection
    private static final ChoiceEntry[] CHOICES_FOURTH = {
        new ChoiceEntry("Focus on work", 0,5,
            SceneEntry.dialogue("{name}", "I can't risk my job over a crush.", "none", "Bedroom.jpg"),
            SceneEntry.dialogue("{name}", "I need to get over this.", "none", "Bedroom.jpg")
        ),
        new ChoiceEntry("Give yourself a chance", 2,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "Maybe it's too early to give up.", "none", "Bedroom.jpg"),
            SceneEntry.dialogue("{name}", "Just wait, Rosario…", "none", "Bedroom.jpg")
        ),
    };
    
    @Override
    public SceneEntry[] getMorningScenes() { return MORNING; }
    @Override
    public SceneEntry[] getAfternoonScenes() { return new SceneEntry[0]; }
    @Override
    public SceneEntry[] getEveningScenes() { return EVENING; }
    
    private static final HashMap<String, ChoiceEntry[]> choiceMap = new HashMap<>();
    static {
        choiceMap.put("ROSARIO_DAY3_CHOICE_FIRST", CHOICES_FIRST);
        choiceMap.put("ROSARIO_DAY3_CHOICE_SECOND", CHOICES_SECOND);
        choiceMap.put("ROSARIO_DAY3_CHOICE_THIRD", CHOICES_THIRD);
        choiceMap.put("ROSARIO_DAY3_CHOICE_FOURTH", CHOICES_FOURTH);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day3Rosario.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}