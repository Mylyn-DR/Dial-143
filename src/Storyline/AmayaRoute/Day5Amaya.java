package Storyline.AmayaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day5Amaya implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("A day ahead of yourself, yet here you are. Worrying like a concerned lover in the breakroom. "
                + "Two people you've befriended in this big company building decide it's high time to bother you.", "BreakRoom.jpg"),
        SceneEntry.dialogue("Cloma", "Well, well, if it isn't our resident worrier. You've been orbiting Amaya like a concerned satellite all week.", "single:Cloma_Smile", "BreakRoom.jpg"),
        SceneEntry.dialogue("{name}", "W-what!", "none", "BreakRoom.jpg"),
        SceneEntry.dialogue("Celeres", "Don't tease them, Cloma.", "double:Cloma_Smile,Celeres_Casual", "BreakRoom.jpg"),
        SceneEntry.dialogue("Celeres", "You're worried about her, right? We all are, a little.", "single:Celeres_Casual", "BreakRoom.jpg"),
        SceneEntry.choiceMarker("AMAYA_MORNING_CHOICE5"),
        SceneEntry.narrator("Suddenly, the very person you worried about entered the picture.", "BreakRoom.jpg"),
        SceneEntry.dialogue("Amaya", "What are you guys talking about?", "single:Amaya_Casual", "BreakRoom.jpg"),
        SceneEntry.dialogue("Cloma", "Nothing important.", "single:Cloma_Casual", "BreakRoom.jpg"),
        SceneEntry.dialogue("Celeres", "Just work stuff.", "single:Celeres_Casual", "BreakRoom.jpg"),
        SceneEntry.dialogue("Amaya", "Hmmm... okay.", "single:Amaya_Casual", "BreakRoom.jpg"),
        SceneEntry.narrator("She glances at you briefly, then looks away.", "BreakRoom.jpg"),
        SceneEntry.dialogue("Amaya", "Break's over. Let's go.", "single:Amaya_Casual", "BreakRoom.jpg"),
        SceneEntry.narrator("They all go back to their work. Answers remained uncertain.", "MorningOffice.jpg"),
        SceneEntry.narrator("But as you walk, Amaya falls into step beside you. Her hand brushes yours.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "um, hey {name}?", "single:Amaya_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Yeah?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "…Never mind.", "single:Amaya_Sad", "MorningOffice.jpg"),
        SceneEntry.narrator("She speeds up, leaving you behind.", "MorningOffice.jpg"),
    };
    private static final SceneEntry[] AFTERNOON = {};
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("You and Amaya walk home together under dim streetlights. The city is quieter at this hour – just the distant sound of traffic and the occasional siren.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Hey… where's Rosario?", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "I think he left earlier.", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("The silence between you feels heavier than usual.", "StreetEvening.jpg"),
        SceneEntry.narrator("A dog barks somewhere in the distance. Amaya kicks a loose pebble. It skitters across the sidewalk and disappears into the gutter.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "You've been quiet all day.", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "So have you.", "none", "StreetEvening.jpg"),
        
        SceneEntry.narrator("In your suprise, she laughs. Hard.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Yeah. I guess so.", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("AMAYA_EVENING_CHOICE5"),
    };
    private static final ChoiceEntry[] CHOICES_M5 = {
        new ChoiceEntry("Ask about Amaya", 15, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Yeah. I am. She's been acting differently. Is something wrong?", "none", "BreakRoom.jpg"),
            SceneEntry.dialogue("Cloma", "Straight to the point, huh.", "single:Cloma_Casual", "BreakRoom.jpg"),
            SceneEntry.dialogue("Celeres", "Alright… but don't spread this around.", "single:Celeres_Casual", "BreakRoom.jpg"),
            SceneEntry.dialogue("Cloma", "Amaya didn't use to work this much.", "single:Cloma_Sad", "BreakRoom.jpg"),
            SceneEntry.dialogue("Celeres", "Something happened with her family.", "single:Celeres_Casual", "BreakRoom.jpg"),
            SceneEntry.dialogue("{name}", "Family. Right. I remember seeing them once.", "none", "BreakRoom.jpg"),
            SceneEntry.dialogue("Celeres", "Uh uh. Medical problems. Bills.", "single:Celeres_Sad", "BreakRoom.jpg"),
            SceneEntry.dialogue("Cloma", "She started taking every shift she could after that.", "single:Cloma_Sad", "BreakRoom.jpg"),
            SceneEntry.dialogue("{name}", "So that's why she looks so tired.", "none", "BreakRoom.jpg"),
            SceneEntry.dialogue("Celeres", "She doesn't like relying on anyone. Believes that even if someone wishes to donate money to support her, it feels like an obligation to pay them back for her.", "single:Celeres_Sad", "BreakRoom.jpg")
        ),
        new ChoiceEntry("Avoid the topic", 5, 0,
            SceneEntry.dialogue("{name}", "I just came here to rest.", "none", "BreakRoom.jpg"),
            SceneEntry.dialogue("Cloma", "Sure you did.", "single:Cloma_Casual", "BreakRoom.jpg"),
            SceneEntry.dialogue("Celeres", "Alright.", "single:Celeres_Casual", "BreakRoom.jpg"),
            SceneEntry.narrator("The conversation doesn't go any further.", "BreakRoom.jpg"),
            SceneEntry.narrator("Whatever's bothering Amaya… you're still unsure.", "BreakRoom.jpg")
        ),
    };
    private static final ChoiceEntry[] CHOICES_E8 = {
        new ChoiceEntry("Ask about her past", 20, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Amaya… can I ask you something?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Hmm?", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "You've been working a lot lately. I'm just… worried about you.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("She pauses for a moment. The stars above her flicker.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "You don't have to worry. I'm fine.", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I don't think you are.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "I said I'm fine.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Is it… because of your family?", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("She stops walking. Completely still.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Who told you that?", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "No one had to. I just want to understand. I remember seeing your parents before. You used to mention them a lot, but now you didn't. Guessed that something must've happened.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "… My mom got sick. A few months ago.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.narrator("Her voice cracks on the last word.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "The bills are piling up. My dad's business isn't doing well either. So I just… keep working.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "…", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Why do you care so much?", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Because it's you.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "You're really unfair sometimes.", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.narrator("Her voice softens.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "But… thanks. For asking.", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "What about your art?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "..", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Why did you say you went to IT? Weren't you supposed to take up Fine Arts? Wasn't that your childhood dream?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Oh, well. We all need money don't we?", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "I don't have time for dreams anymore, PC. I have responsibilities.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "You can have both.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "… Can I?", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.narrator("The question hangs in the air. She's not asking about art anymore.", "StreetEvening.jpg"),
            SceneEntry.narrator("She's asking if she's allowed to want things. To be happy. To be something other than the eldest daughter who holds everything together.", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "I think you can. If someone reminds you.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "And who's going to do that?", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Me.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("She stares at you for a long moment. The streetlight hums. Somewhere, a door slams.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Okay.", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.narrator("She starts walking again. This time, she doesn't walk ahead.", "StreetEvening.jpg"),
            SceneEntry.narrator("She walks beside you.", "StreetEvening.jpg")
        ),
        new ChoiceEntry("Stay silent", -15, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "…", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "You're quiet tonight.", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Just tired.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Yeah… same here.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.narrator("You want to say something. Ask something. But the words won't come. Minutes have already passed but the tension was strong. Words wished to be said, but never did.", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "I'll grab a bus here. Anyways… see you tomorrow.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Yeah. See you.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("She leaves before anything else can be said, you walked away.", "StreetEvening.jpg"),
            SceneEntry.narrator("You want to turn back, to see her again.", "StreetEvening.jpg"),
            SceneEntry.narrator("And before you realized it.", "StreetEvening.jpg"),
            SceneEntry.narrator("You're already home.", "StreetEvening.jpg")
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
        choiceMap.put("AMAYA_MORNING_CHOICE5", CHOICES_M5);
        choiceMap.put("AMAYA_EVENING_CHOICE5", CHOICES_E8);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day5Amaya.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}