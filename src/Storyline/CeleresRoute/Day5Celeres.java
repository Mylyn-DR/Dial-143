package Storyline.CeleresRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day5Celeres implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("It's morning.", "MorningOffice.jpg"),
        SceneEntry.narrator("The office is busy and loud.", "MorningOffice.jpg"),
        SceneEntry.narrator("Phones ring nonstop as you juggle calls and tasks.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Hey… you've got a lot on your plate this morning.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Don't forget to breathe, okay?", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Thanks… it's just a lot of work.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "I know the feeling.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "If you need help, just ask.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.narrator("Across the room, Celeres sits quietly at his desk.", "MorningOffice.jpg"),
        SceneEntry.narrator("Headphones on. Focused. Calm.", "MorningOffice.jpg"),
        SceneEntry.narrator("Somehow… his presence makes the chaos feel lighter.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Celeres…", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) When everyone else is loud… he's calm.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) And lately… I want to be near that calm.", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("Amaya notices where you're looking.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Who are you staring at like that?", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Uh… no one!", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Sure… keep telling yourself that.", "single:Amaya_Playful", "MorningOffice.jpg"),
        SceneEntry.narrator("Celeres looks up briefly.", "MorningOffice.jpg"),
        SceneEntry.narrator("He gives you a small nod.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY5_MORNING_CHOICE")
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The office grows quiet.", "EveningOffice.jpg"),
        SceneEntry.narrator("Most people have already left.", "EveningOffice.jpg"),
        SceneEntry.narrator("Only the soft hum of computers remains.", "EveningOffice.jpg"),
        SceneEntry.narrator("You're still working overtime.", "EveningOffice.jpg"),
        SceneEntry.narrator("As you prepare to leave—", "EveningOffice.jpg"),
        SceneEntry.narrator("You notice Celeres sitting near the servers.", "EveningOffice.jpg"),
        SceneEntry.narrator("Earbuds in. Eyes half-closed.", "EveningOffice.jpg"),
        SceneEntry.narrator("Peaceful.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) He's always like this…", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Calm… even when everything else isn't.", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("You walk closer.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Mind if I sit beside you?", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("He removes one earbud and nods.", "EveningOffice.jpg"),
        SceneEntry.narrator("Then offers you the other.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) This is… nice.", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Just the two of us. Quiet.", "none", "EveningOffice.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY5_EVENING_CHOICE"),
        SceneEntry.narrator("The night air feels cooler as you step outside.", "StreetEvening.jpg"),
        SceneEntry.narrator("The world is quieter now.", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Even just sitting there with him…", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) I felt calm.", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "(mind) I want more moments like that.", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("You walk home with that feeling lingering in your chest.", "StreetEvening.jpg"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Walk over and greet him", 5, 2, Character.CELERES,
            SceneEntry.dialogue("{name}", "Morning, Celeres. How's it going?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Morning… all good. You?", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Busy, but manageable.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He gives a faint smile before returning to work.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Even a small hello from him… it feels nice.", "none", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Wave at him from your desk", 2, 3, Character.CELERES,
            SceneEntry.narrator("(waves quietly)", "MorningOffice.jpg"),
            SceneEntry.narrator("Celeres nods slightly. A small smile forms.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) It's not much… but it feels like something.", "none", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Ignore him and focus on tasks", 0, 5,
            SceneEntry.narrator("(pretends not to notice)", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Really? You're just ignoring him?", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) I'll just focus on work… for now.", "none", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Lean your head on his shoulder", 10, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "Mind if I… lean here for a bit?", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("He pauses. Then gives a small nod.", "EveningOffice.jpg"),
            SceneEntry.narrator("You gently rest your head on his shoulder.", "EveningOffice.jpg"),
            SceneEntry.narrator("He freezes for a second— Then slowly relaxes.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…You can stay.", "single:Celeres_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) He feels warm… Calm. Safe.", "none", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Say the music is boring", -5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "This music is too boring… can we play something louder?", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Celeres slowly removes the earbud. He stands up.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I think it's time for me to go.", "single:Celeres_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Oh… I didn't mean that… Sorry…", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("He pauses. Then sits back down.", "EveningOffice.jpg"),
            SceneEntry.narrator("He hands you the earbud again.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…It's fine. Just… don't interrupt the music next time.", "single:Celeres_Smile", "EveningOffice.jpg")
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
        choiceMap.put("CELERES_DAY5_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("CELERES_DAY5_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day5Celeres.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}