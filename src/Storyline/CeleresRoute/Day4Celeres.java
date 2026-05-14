package Storyline.CeleresRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day4Celeres implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("You're having a stressful morning.", "MorningOffice.jpg"),
        SceneEntry.narrator("The office is loud, and everyone is buried in work.", "MorningOffice.jpg"),
        SceneEntry.narrator("You find a sticky note on your desk.", "MorningOffice.jpg"),
        SceneEntry.narrator("\"Take a breath. You're doing fine. You can do it!\"", "MorningOffice.jpg"),
        SceneEntry.narrator("Below the text is a tiny drawing of a sleeping cat.", "MorningOffice.jpg"),
        SceneEntry.narrator("It immediately reminds you of Celeres.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(He's always so quiet… but he notices the small things…)", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Maybe that's why I like him.)", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("Just then, Celeres walks in and catches your eye.", "MorningOffice.jpg"),
        SceneEntry.narrator("At the same time, Rosario appears near your desk, scanning the office.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Is everything alright here?", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Why are you distracted?", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Uh-oh…)", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Nothing.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Good.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Focus on your work.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.narrator("Rosario moves on, checking the rest of the office.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY4_MORNING_CHOICE"),
        SceneEntry.narrator("As the shift ends, the office slowly quiets down.", "EveningOffice.jpg"),
        SceneEntry.narrator("Coworkers leave, lights dim, and the noise fades.", "EveningOffice.jpg"),
        SceneEntry.narrator("You glance toward the IT department.", "EveningOffice.jpg"),
        SceneEntry.narrator("Celeres is in his chair, a book covering his face.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Is he sleeping again…?)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("After a moment, he lowers the book and walks toward you.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "Heading home?", "single:Celeres_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Yeah.", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "…Get some rest.", "single:Celeres_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Even the smallest words from him feel warm.)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("With that quiet moment lingering, you leave the office.", "EveningOffice.jpg"),
        SceneEntry.narrator("Somehow… today doesn't feel as stressful anymore.", "EveningOffice.jpg"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The afternoon fades into evening.", "EveningOffice.jpg"),
        SceneEntry.narrator("Most of the office is still busy finishing tasks.", "EveningOffice.jpg"),
        SceneEntry.narrator("You stretch after staring at your screen for hours.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(My brain feels fried…)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("You notice Celeres walking past your desk with a cup of coffee.", "EveningOffice.jpg"),
        SceneEntry.narrator("Rosario stands nearby, reviewing reports.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Everyone should finish their tasks before leaving.", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Of course he's watching again…)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("Celeres suddenly places a cup of coffee on your desk.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Huh?", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "You look tired.", "single:Celeres_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Wait… is this for me?", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "Yeah.", "single:Celeres_Casual", "EveningOffice.jpg"),
        SceneEntry.narrator("Rosario raises an eyebrow.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Is this a café now?", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Or are we still working?", "single:Rosario_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "…Both can happen.", "single:Celeres_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(He noticed again…)", "none", "EveningOffice.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY4_EVENING_CHOICE"),
        SceneEntry.narrator("As the shift ends, the office slowly quiets down.", "EveningOffice.jpg"),
        SceneEntry.narrator("Coworkers leave, lights dim, and the noise fades.", "EveningOffice.jpg"),
        SceneEntry.narrator("You glance toward the IT department.", "EveningOffice.jpg"),
        SceneEntry.narrator("Celeres is in his chair, a book covering his face.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Is he sleeping again…?)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("After a moment, he lowers the book and walks toward you.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "Heading home?", "single:Celeres_Casual", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Yeah.", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "…Get some rest.", "single:Celeres_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Even the smallest words from him feel warm.)", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("With that quiet moment lingering, you leave the office.", "EveningOffice.jpg"),
        SceneEntry.narrator("Somehow… today doesn't feel as stressful anymore.", "EveningOffice.jpg"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Smile and show the note", 5, 0, Character.CELERES,
            SceneEntry.narrator("(holds up the note and smiles)", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Morning, Celeres. Thanks for this…", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He blushes faintly, but doesn't look away.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…You're welcome.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Don't let the morning stress get to you.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(I feel calmer… he always makes me feel like this.)", "none", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Ignore and throw the note", -10, 0, Character.CELERES,
            SceneEntry.narrator("(shrugs and tosses the note)", "MorningOffice.jpg"),
            SceneEntry.narrator("Celeres pauses.", "MorningOffice.jpg"),
            SceneEntry.narrator("A flicker of disappointment crosses his face.", "MorningOffice.jpg"),
            SceneEntry.narrator("He quietly puts on his headphones.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…Figures.", "single:Celeres_Sad", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Are you seriously ignoring your coworkers while they're trying to help?", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Stay focused.", "single:Rosario_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(Ugh… I shouldn't have done that…)", "none", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Apologize", 5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "(softly) Sorry, Celeres…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I didn't mean to ignore it.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He slowly lowers his headphones.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…It's fine.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Just don't toss notes like that next time.", "single:Celeres_Smile", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Accept and thank him", 5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "(smiles softly) Thanks, Celeres.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "You always show up when I'm about to collapse.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…You looked like you needed it.", "single:Celeres_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Just make sure that doesn't slow your productivity.", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Don't worry. It actually helps.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Celeres leans against your desk for a moment.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Try not to overwork yourself.", "single:Celeres_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(He's quiet… but he always knows.)", "none", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Tease him", 8, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "(smirks) Are you secretly my personal coffee delivery?", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…Only when you look half-dead from work.", "single:Celeres_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Wow. That honest?", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I'm just observant.", "single:Celeres_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Observant… or distracted?", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Sorry, Rosario.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Celeres gives a small, sleepy smile.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(That smile… I wish I could see it more often.)", "none", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Refuse the coffee", -5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "I'm fine. You can keep it.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Celeres pauses.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…Alright.", "single:Celeres_Casual", "EveningOffice.jpg"),
            SceneEntry.narrator("He quietly takes the cup back.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(Why do I suddenly feel bad…?)", "none", "EveningOffice.jpg")
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
        choiceMap.put("CELERES_DAY4_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CELERES_DAY4_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day4Celeres.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}