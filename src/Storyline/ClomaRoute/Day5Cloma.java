package Storyline.ClomaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day5Cloma implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("A new day begins at the office.", "MorningOffice.jpg"),
        SceneEntry.narrator("But today feels different.", "MorningOffice.jpg"),
        SceneEntry.narrator("You keep thinking about what happened last night.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Dinner… with Miss Cloma tonight…", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("Amaya suddenly appears beside your desk.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Good morning!", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Oh! Morning.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Sooooo…", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "So…?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I saw something interesting yesterday.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "What do you mean?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "You. At the convenience store. With Miss Cloma.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Wait—how did you—", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I was there too! You didn't see me.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Amaya!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "So… dinner tonight, huh?", "single:Amaya_Smiling", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Keep your voice down!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Relax. I'm just teasing you.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.narrator("You feel a little embarrassed.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY5_MORNING_CHOICE"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The workday finally ends.", "EveningOffice.jpg"),
        SceneEntry.narrator("Tonight is the dinner with Miss Cloma.", "EveningOffice.jpg"),
        SceneEntry.narrator("Your heart beats faster as you walk outside.", "EXT_RESTAURANT"),
        SceneEntry.narrator("A familiar car stops nearby.", "EXT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Good evening.", "single:Cloma_Smile", "EXT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Good evening, Miss Cloma.", "none", "EXT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Come on, let's go.", "single:Cloma_Smile", "EXT_RESTAURANT"),
        SceneEntry.narrator("You hop in.", "EXT_RESTAURANT"),
        SceneEntry.narrator("This moment feels important somehow.", "EXT_RESTAURANT"),
        SceneEntry.narrator("After a short drive, you arrive at a small restaurant.", "EXT_RESTAURANT"),
        SceneEntry.narrator("The place is calm and not too crowded.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "This is the place.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "It looks nice.", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "I come here sometimes when I want a quiet dinner.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.narrator("You walk inside and sit at a table.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "You can order anything you like.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Oh! Okay, thank you.", "none", "INT_RESTAURANT"),
        SceneEntry.narrator("After ordering, a quiet moment passes.", "INT_RESTAURANT"),
        SceneEntry.narrator("You feel a little nervous sitting across from her.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "So… how was your day?", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "It was okay. Just the usual work.", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "I see. You've been doing well lately.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Really?", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Yes. I noticed your effort.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Thank you, Miss Cloma.", "none", "INT_RESTAURANT"),
        SceneEntry.narrator("The food arrives after a few minutes.", "INT_RESTAURANT"),
        SceneEntry.narrator("The conversation slowly becomes more comfortable.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Oh, right. I almost forgot to mention something.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "What is it?", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Tomorrow we have an event at the company. It's a small awarding event for the employees.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Oh… I didn't know that.", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Some people will receive an award like the best employee, etc. It should be a nice event.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.narrator("She pauses for a moment and looks at you.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "You should come tomorrow.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Me?", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "Yes. I noticed that you've been doing a great job lately. You handled many calls well. I think you might receive an award tomorrow.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "Really…?", "none", "INT_RESTAURANT"),
        SceneEntry.narrator("You feel surprised hearing that.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "That's why I wanted you to come to the event.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.narrator("You're not sure what to say.", "INT_RESTAURANT"),
        SceneEntry.choiceMarker("CLOMA_DAY5_EVENING_CHOICE"),
        SceneEntry.narrator("After a while, you both finish eating and chat a little more.", "INT_RESTAURANT"),
        SceneEntry.narrator("Soon, it's time to leave. Miss Cloma walks you back to the car.", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "See you tomorrow.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.dialogue("{name}", "See you tomorrow. Thank you, and goodnight, Miss Cloma.", "none", "INT_RESTAURANT"),
        SceneEntry.dialogue("Cloma", "You're welcome. Goodnight.", "single:Cloma_Smile", "INT_RESTAURANT"),
        SceneEntry.narrator("The car drives off, and the day quietly comes to an end.", "INT_RESTAURANT"),
        SceneEntry.narrator("You feel your heart a little lighter as you head home.", "INT_RESTAURANT"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Tell Amaya The Truth", 5, 0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Okay… yes. I'm a little nervous about tonight.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "That's normal! Just be yourself.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I'll try…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Don't worry. You got this.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("Her words make you feel a little more confident.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Pretend Nothing Happened", 2,0,  Character.CLOMA,
            SceneEntry.dialogue("{name}", "It's just dinner. Nothing special.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Hmm… really?", "single:Amaya_Playful", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Yeah.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Sure… keep telling yourself that.", "single:Amaya_Smirking", "MorningOffice.jpg"),
            SceneEntry.narrator("Amaya walks away laughing.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Maybe it's not \"just dinner\"…", "none", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Gratefully Accept Praise", 10, 0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Thank you, Miss Cloma. That really means a lot to me.", "none", "INT_RESTAURANT"),
            SceneEntry.dialogue("Cloma", "You deserve it. Keep doing your best.", "single:Cloma_Smile", "INT_RESTAURANT"),
            SceneEntry.narrator("Her words make you feel more confident.", "INT_RESTAURANT")
        ),
        new ChoiceEntry("Stay Humble About Praise", 5, 2, Character.CLOMA,
            SceneEntry.dialogue("{name}", "I'm not sure if I deserve it yet… but I'll try my best.", "none", "INT_RESTAURANT"),
            SceneEntry.dialogue("Cloma", "That's a good attitude.", "single:Cloma_Smile", "INT_RESTAURANT"),
            SceneEntry.narrator("She smiles at you.", "INT_RESTAURANT")
        ),
        new ChoiceEntry("Doubt Your Achievements", -5,0,  Character.CLOMA,
            SceneEntry.dialogue("{name}", "I don't think I'll win anything…", "none", "INT_RESTAURANT"),
            SceneEntry.dialogue("Cloma", "Don't say that. You've been doing well.", "single:Cloma_Smile", "INT_RESTAURANT"),
            SceneEntry.narrator("Her voice sounds reassuring.", "INT_RESTAURANT")
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
        choiceMap.put("CLOMA_DAY5_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CLOMA_DAY5_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day5Cloma.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}