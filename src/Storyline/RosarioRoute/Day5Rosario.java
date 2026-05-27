package Storyline.RosarioRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day5Rosario implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("You arrive at the office super early. The place is quiet, with only a few employees settling into their tasks.", "MorningOffice.jpg"),
        SceneEntry.narrator("As you pass by Rosario's office, you suddenly hear soft laughter. You stop, curious.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Huh… why is he laughing to himself so early?", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You knock gently on his door twice, but Rosario doesn't notice. He's too focused on his screen.", "MorningOffice.jpg"),
        SceneEntry.narrator("Carefully, you slightly open the door and peek inside. Rosario is smiling at his computer screen, completely absorbed.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Wait… what is he watching??", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You step closer, and suddenly, Rosario notices you.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Oh… you're here. Is there a problem?", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Oh, hi! No problem at all. I just heard your laugh from outside. I knocked, but I guess you didn't hear me… so I came in. Sorry!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Oh, no worries. I'm just watching hamster videos. I usually do this in the morning before starting work.", "single:Rosario_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Aww… he's so cute watching hamster videos. I didn't know he had this side… I really love it.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Oh my gosh… I love hamster videos too!", "none", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY5_MORNING_CHOICE"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("You were walking home, still thinking about what you saw earlier that morning.", "StreetEvening.jpg"),
        SceneEntry.narrator("Your heart wouldn't let you stay away. Without thinking, you turned back and hurried to Rosario's office, hoping he might still be there.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Rosario… I need to see him…", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("You speed-walk through the building, climbing the stairs, eyes scanning the hallways for his figure.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) I can't keep acting like a fool in front of him…", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("Your thoughts swirl, unsure what his reaction might be. Then a familiar voice interrupts your racing mind.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "(Player)!", "single:Amaya_Smirk", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Oh… Amaya! You startled me, haha.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "What are you doing back here? Did you forget something?", "single:Amaya_Smirk", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Uh… It's…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Should I tell her the real reason?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Huh… are you running back to see him again?", "single:Amaya_Smirk", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Maybe… I just… wanted to check if he's still here.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "(smirking) You've got it bad, huh?", "single:Amaya_Smirk", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Oh no… she knows…", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("You nod sheepishly. Amaya chuckles and points you toward Rosario's office.", "StreetEvening.jpg"),
        SceneEntry.narrator("You take a deep breath and knock softly before entering. Rosario is at his desk, reviewing documents, but he looks up and smirks.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Well, if it isn't my favorite troublemaker… back so soon?", "single:Rosario_Smirk", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Did he just… Oh nevermind…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(smiling) I couldn't stay away… besides, I needed to make sure someone as handsome as you isn't slacking off.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Oh? So that's how it is, huh… bold and clever. I like it.", "single:Rosario_RaiseEyebrow", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) He's smirking… I can't let him have all the fun.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Careful, Rosario… don't make me start thinking you actually missed me.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Hm… so that's the game you're playing. Smart comeback, PC… I'm speechless.", "single:Rosario_LaughSoft", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Did I just… leave him speechless?", "none", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY5_EVENING_CHOICE"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Watch With Him", 10,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "I still have time before work… can I watch with you?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Sure! You can sit here beside me.", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Thank you!", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I really enjoy watching hamster videos. And you know what?", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Oh… he's opening up.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "What is it?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I plan to have a pet hamster someday. They're such a stress reliever for me.", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "That's a good idea.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You both continue watching until the workday officially begins.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Leave Immediately", -5,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "I should go now…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Oh…. okay.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.narrator("You leave the office quietly, missing the chance to see another side of him.", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Tease Him Back", 10,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "You know, if you keep looking at me like that, I might start expecting chocolate as a reward.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Oh? Is that a challenge?", "single:Rosario_Laugh", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Maybe it is. Can you handle it?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Hmmm…. I didn't know you were this, (Player)", "single:Rosario_Thinking", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I learned from the best.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "…So that's how it is, huh. You're bold, PC.", "single:Rosario_Surprised", "StreetEvening.jpg"),
            SceneEntry.narrator("Rosario looks at you for a moment, clearly caught off guard by your comeback.", "StreetEvening.jpg"),
            SceneEntry.narrator("The playful tension between you grows stronger, and you can't help but smile.", "StreetEvening.jpg")
        ),
        new ChoiceEntry("Act Shy But Smile", 5,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "H-Hey… don't make me flustered like this.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Oh, I like this side of you… very cute.", "single:Rosario_Smirk", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I'm serious… stop teasing me.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "I'll try… But I can't promise anything.", "single:Rosario_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "You're impossible.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Maybe… but you still came back to see me.", "single:Rosario_Smile", "StreetEvening.jpg"),
            SceneEntry.narrator("You both laugh softly, and the moment feels warm and comfortable.", "StreetEvening.jpg"),
            SceneEntry.narrator("Even simple teasing with Rosario makes your heart beat a little faster.", "StreetEvening.jpg")
        ),
        new ChoiceEntry("Leave", -10,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "I should go now. I think I disturbed you.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "No, you're not. But I think you should go home because it's already late. Take care.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Okay, bye. Goodnight.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Rosario", "Goodnight.", "single:Rosario_Casual", "StreetEvening.jpg"),
            SceneEntry.narrator("You quietly leave the office.", "StreetEvening.jpg"),
            SceneEntry.narrator("As you walk away, you wonder if you missed a chance to stay a little longer with him.", "StreetEvening.jpg")
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
        choiceMap.put("ROSARIO_DAY5_EVENING_CHOICE", CHOICES_MORNING);
        choiceMap.put("ROSARIO_DAY5_MORNING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day5Rosario.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}