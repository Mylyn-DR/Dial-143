package Storyline.ClomaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day4Cloma implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("Another day begins at the office. You feel more comfortable after the last few days.", "MorningOffice.jpg"),
        SceneEntry.narrator("During the morning, you do your work.", "MorningOffice.jpg"),
        SceneEntry.narrator("Finally, it's break time.", "MorningOffice.jpg"),
        SceneEntry.narrator("Amaya walks in holding the umbrella.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Hey! I have a favor.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Oh! Hi! What is it?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Can you return this umbrella to Miss Cloma?", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Wait, me?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Of course you.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "But you're the one who borrowed it!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Yeah, I know.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Then why don't you do it?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Because she likes talking to you, not me.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Amaya!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "(laughs) Just go.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Fine.", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You take the umbrella and walk toward Miss Cloma's table.", "MorningOffice.jpg"),
        SceneEntry.narrator("Your heart beats a little faster as you approach her.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Excuse me, Miss Cloma…", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Cloma", "Oh! Hello!", "single:Cloma_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Here's your umbrella from yesterday.", "none", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY4_MORNING_CHOICE"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The workday is finally over. You pack your things and leave the office building.", "EveningOffice.jpg"),
        SceneEntry.narrator("You stop by the 6/70 convenience store to buy something to eat.", "ConvenienceStore.jpg"),
        SceneEntry.narrator("As you look at the shelves, someone speaks behind you.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Oh… it's you.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Oh—hi, Celeres.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Buying dinner?", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Yeah. I was too tired to cook.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Same. Work was noisy today… I hate that.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Rough day?", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Hmm. Something like that.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.narrator("The convenience store door opens.", "ConvenienceStore.jpg"),
        SceneEntry.narrator("It's Miss Cloma.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Oh. Good evening, Miss Cloma.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Cloma", "Good evening, Celeres.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Just buying food before going home.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Cloma", "I see. Oh! Hello.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Oh—good evening, Miss Cloma.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Cloma", "I didn't expect to see you here.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "I just stopped by to buy dinner.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "Same here.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.narrator("The three of you stand there for a moment.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Celeres", "I'll go pay first.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
        SceneEntry.narrator("Now it's just you and Cloma near the shelves.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Cloma", "Long day?", "single:Cloma_Smile", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Yeah… but it's okay.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Cloma", "I'm glad. Actually… if you're free tomorrow… Would you like to have dinner with me?", "single:Cloma_Smile", "ConvenienceStore.jpg"),
        SceneEntry.narrator("Your heart suddenly beats faster.", "ConvenienceStore.jpg"),
        SceneEntry.narrator("From the counter, Celeres quietly glances back, noticing the moment.", "ConvenienceStore.jpg"),
        SceneEntry.choiceMarker("CLOMA_DAY4_EVENING_CHOICE"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Thank Her With Snack", 10, 5, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Thank you again for yesterday! By the way, I also brought you a snack… just a small thank you.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "Oh! That's so thoughtful… You really didn't have to, but thank you!", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I just wanted to show my appreciation.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "(smiles warmly) Thank you. That's very kind of you.", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("Her smile makes your heart beat a little faster.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Quickly Hand Umbrella", -10, 0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Thank you.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Cloma", "Oh… thank you.", "single:Cloma_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Okay… bye.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You quickly walk away.", "MorningOffice.jpg"),
            SceneEntry.narrator("Cloma looks a little surprised as you leave.", "MorningOffice.jpg"),
            SceneEntry.narrator("From the other table, Amaya watches and shakes her head.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Wow… that was awkward.", "single:Amaya_Whisper", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Accept Dinner Invitation", 10, 0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "I'd like that… dinner sounds nice.", "none", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Cloma", "(smiles softly) Good. Then it's a plan. I'll tell you the place tomorrow.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
            SceneEntry.narrator("From the counter, Celeres raises an eyebrow slightly.", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Celeres", "Huh… interesting.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
            SceneEntry.narrator("Your heart feels strangely light.", "ConvenienceStore.jpg")
        ),
        new ChoiceEntry("Politely Decline Invitation", 5, 2, Character.CLOMA,
            SceneEntry.dialogue("{name}", "I'm sorry… I might be busy tomorrow.", "none", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Cloma", "Oh, I understand. Maybe some other time then.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
            SceneEntry.narrator("For a moment, she looks a little disappointed.", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Celeres", "(quietly) You sure about that?", "single:Celeres_Casual", "ConvenienceStore.jpg"),
            SceneEntry.narrator("You suddenly wonder if you made the right choice.", "ConvenienceStore.jpg")
        ),
        new ChoiceEntry("Panic And Stumble Answer", -5,0, Character.CLOMA,
            SceneEntry.dialogue("{name}", "Uh—dinner?! Tomorrow?! I mean—I—uh—", "none", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Cloma", "(confused smile) It's okay… you can think about it.", "single:Cloma_Smile", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Celeres", "(from the counter) Relax. It's just dinner.", "single:Celeres_Casual", "ConvenienceStore.jpg"),
            SceneEntry.narrator("Your face feels hot as both of them look at you.", "ConvenienceStore.jpg")
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
        choiceMap.put("CLOMA_DAY4_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CLOMA_DAY4_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day4Cloma.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}