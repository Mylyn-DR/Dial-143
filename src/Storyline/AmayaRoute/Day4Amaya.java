package Storyline.AmayaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day4Amaya implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("The next day, the usual buzz of the call center feels louder—more chaotic than usual.", "MorningOffice.jpg"),
        SceneEntry.narrator("You notice Amaya on a particularly difficult call.", "MorningOffice.jpg"),
        SceneEntry.narrator("Her \"customer service voice\" is perfect, but the moment she hangs up, her smile disappears.", "MorningOffice.jpg"),
        SceneEntry.narrator("For a moment, she just stares at her monitor, shoulders slumped.", "MorningOffice.jpg"),
        SceneEntry.narrator("Before you can approach her, a voice calls out.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "PC, come here for a second.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Yes, sir?", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "I want you to check these papers. Just double-check for any margin errors.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.narrator("Before he can hand them over, Amaya suddenly steps in.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I'll do it!", "single:Amaya_Casual", "MorningOffice.jpg"),
        SceneEntry.narrator("She grabs the papers and quickly skims through them.", "MorningOffice.jpg"),
        SceneEntry.narrator("She pats them neatly after.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I can finish this in a week—no, even less!", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "You'll raise my pay if I do, right?", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Amaya… I wasn't asking for volunteers.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "And I'm definitely not asking you to take on more work.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Oh, come on! I can do this!", "single:Amaya_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "You know me—always looking for overtime!", "single:Amaya_Casual", "MorningOffice.jpg"),
        SceneEntry.narrator("Rosario glances at you.", "MorningOffice.jpg"),
        SceneEntry.narrator("There's something in his expression… disappointment? Hurt?", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Amaya, you've worked more than enough.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "It's time to rest.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "But! PC, you know I can do this, right?", "single:Amaya_Casual", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("AMAYA_MORNING_CHOICE"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("After what felt like an endless day, your shift finally ends.", "EveningOffice.jpg"),
        SceneEntry.narrator("You, Rosario, and Amaya walk out together.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "PC, walk with me for a bit?", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "I wanted to talk about the team project.", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.narrator("Suddenly, Amaya squeezes between you and Rosario.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Actually, PC and I were going to grab something to eat.", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Right, PC?", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "We were?", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Yeah. We were.", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Amaya, I wasn't trying to steal them.", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I know.", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "But we have plans.", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.narrator("The walk becomes tense.", "StreetEvening.jpg"),
        SceneEntry.narrator("Amaya stays close, brushing against you.", "StreetEvening.jpg"),
        SceneEntry.narrator("Whenever Rosario speaks, she cuts in first.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "PC, what do you think about the new workflow software?", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "It's fine, right, PC?", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "I… yeah, it's fine.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "I was asking PC, Amaya.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "You got your answer, didn't you?", "single:Amaya_Mad", "StreetEvening.jpg"),
        SceneEntry.narrator("A phone suddenly buzzes.", "StreetEvening.jpg"),
        SceneEntry.narrator("Amaya checks it.", "StreetEvening.jpg"),
        SceneEntry.narrator("Her expression drops.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Everything okay?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Yeah… I just have to go.", "single:Amaya_Sad", "StreetEvening.jpg"),
        SceneEntry.narrator("She turns to Rosario.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Don't keep them too long.", "single:Amaya_Mad", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Wouldn't dream of it.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.narrator("She walks away quickly.", "StreetEvening.jpg"),
        SceneEntry.narrator("You watch her until she disappears.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "She really cares about you.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "She's just protective.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "We've known each other since high school.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "No.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "That was jealousy.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "What?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "PC… can I be honest?", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Of course.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "I hope you let her be herself.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "The real her.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("He pauses.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "She's overworked.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "That smile she shows? It's been fake for a long time.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "You noticed too?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Everyone has.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "But she pushes people away.", "single:Rosario_Casual", "StreetEvening.jpg"),
        SceneEntry.narrator("He looks at you seriously.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "What she showed tonight…", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "That jealousy… that emotion…", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "That's her real self.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Why are you telling me this?", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "Because I like you.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "But she needs you more than I do.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("Rosario", "She reminds me of who I used to be.", "single:Rosario_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("He gives a small nod and walks away.", "StreetEvening.jpg"),
        SceneEntry.narrator("Leaving you alone.", "StreetEvening.jpg"),
        SceneEntry.narrator("With your thoughts.", "StreetEvening.jpg"),
        SceneEntry.narrator("And his confession.", "StreetEvening.jpg"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Tell Amaya to rest", -10, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Amaya, stop.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "(blinks) What?", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "You need to rest. I mean it.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "I don't need—", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I'll handle the papers.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Go take a break. Please.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("Amaya stares at you.", "MorningOffice.jpg"),
            SceneEntry.narrator("Her mouth opens… then closes.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "…Fine.", "single:Amaya_Sad", "MorningOffice.jpg"),
            SceneEntry.narrator("She walks away.", "MorningOffice.jpg"),
            SceneEntry.narrator("But she glances back at you and Rosario.", "MorningOffice.jpg"),
            SceneEntry.narrator("Her jaw tightens.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "That was kind of you.", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Someone had to say it.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "…Yeah.", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("As you take the papers, his fingers brush yours.", "MorningOffice.jpg"),
            SceneEntry.narrator("He doesn't pull away immediately.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Let's go over them together.", "single:Rosario_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "In my office.", "single:Rosario_Smile", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Let Amaya do this", 10, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "She's capable.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "If she wants to do it, let her.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Thank you, PC!", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "…If you say so.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.narrator("Amaya walks away, clearly pleased.", "MorningOffice.jpg"),
            SceneEntry.narrator("Rosario gently touches your arm", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "You know she's working sixty hours a week, right?", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "She seems fine.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "…Does she?", "single:Rosario_Sad", "MorningOffice.jpg"),
            SceneEntry.narrator("You look at Amaya.", "MorningOffice.jpg"),
            SceneEntry.narrator("She's already back at her desk.", "MorningOffice.jpg"),
            SceneEntry.narrator("Head down. Shoulders tense.", "MorningOffice.jpg"),
            SceneEntry.narrator("Her earlier smile is gone.", "MorningOffice.jpg")
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
        choiceMap.put("AMAYA_MORNING_CHOICE", CHOICES_MORNING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day4Amaya.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}