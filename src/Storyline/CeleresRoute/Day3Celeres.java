package Storyline.CeleresRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day3Celeres implements DayInterface {
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {}; 
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("It's already afternoon. The office is quieter now.", "MorningOffice.jpg"),
        SceneEntry.narrator("Your PC suddenly freezes mid-task.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Not again…)", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("You rub your temples and stand up.", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(Guess I have to go to IT again.)", "none", "MorningOffice.jpg"),
        SceneEntry.narrator("Compared to the busy office floor, the IT room feels calm.", "ITDepartment.jpg"),
        SceneEntry.narrator("Inside, Celeres leans back in his chair, a book covering his face.", "ITDepartment.jpg"),
        SceneEntry.dialogue("{name}", "(Is he sleeping…?)", "none", "ITDepartment.jpg"),
        SceneEntry.narrator("Before you speak, he lowers the book slightly.", "ITDepartment.jpg"),
        SceneEntry.dialogue("Celeres", "Your PC is acting up again?", "single:Celeres_Casual", "ITDepartment.jpg"),
        SceneEntry.dialogue("Celeres", "Or did you just come here to hang out?", "single:Celeres_Casual", "ITDepartment.jpg"),
        SceneEntry.dialogue("{name}", "(He noticed me…)", "none", "ITDepartment.jpg"),
        SceneEntry.dialogue("Celeres", "…Never mind. Why are you here?", "single:Celeres_Casual", "ITDepartment.jpg"),
        SceneEntry.choiceMarker("CELERES_DAY3_CHOICE"),
        SceneEntry.narrator("The shift ends. Celeres walks with you.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Celeres", "…This was nice.", "single:Celeres_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Yeah. It was.", "none", "StreetEvening.jpg"),
        SceneEntry.narrator("You walk home with that feeling lingering in your chest.", "StreetEvening.jpg"),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Ask help", 5, 0, Character.CELERES,
            SceneEntry.dialogue("{name}", "My PC froze again. Can you check it?", "none", "ITDepartment.jpg"),
            SceneEntry.dialogue("Celeres", "Your workstation's been doing that all week.", "single:Celeres_Casual", "ITDepartment.jpg"),
            SceneEntry.narrator("He stands up slowly and walks past you.", "ITDepartment.jpg"),
            SceneEntry.narrator("You follow him back to your desk.", "MorningOffice.jpg"),
            SceneEntry.narrator("He fixes your computer within minutes.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Try it.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.narrator("The screen comes back to life.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Oh—it's working. Thanks.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("Before leaving, he places a cup on your desk.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Coffee?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Afternoon shifts are rough.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "You looked like you needed it.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(He remembered…)", "none", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Ask + Hangout", 10,0, Character.CELERES,
            SceneEntry.dialogue("{name}", "My PC froze again… can you check it?", "none", "ITDepartment.jpg"),
            SceneEntry.dialogue("Celeres", "Okay.", "single:Celeres_Casual", "ITDepartment.jpg"),
            SceneEntry.narrator("He walks over and fixes it quickly.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Fixed.", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Thanks… um…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Yeah?", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Are you busy after work?", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("He pauses.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "Not really. Why?", "single:Celeres_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Maybe… we could hang out?", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "…Okay.", "single:Celeres_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("He places a drink on your desk.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "My favorite…", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Celeres", "I saw you buy it before.", "single:Celeres_Smile", "MorningOffice.jpg")
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
        choiceMap.put("CELERES_DAY3_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day3Celeres.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}