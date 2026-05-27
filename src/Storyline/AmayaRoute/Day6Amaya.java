package Storyline.AmayaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day6Amaya implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("The office feels different today. Decorations are set up, music plays softly and everyone seems more relaxed than usual.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Cloma", "Free food and no calls? This is my kind of event.", "single:Cloma_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("Celeres", "Don't get too comfortable. They'll probably make us do something later.", "single:Celeres_Casual", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Come on, just enjoy it for now.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.narrator("She smiles… but it feels a little forced.", "MorningOffice.jpg"),
        SceneEntry.narrator("You notice it again. The same tired look behind her smile.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Everyone, make sure to participate. This is for the team, after all.", "single:Rosario_Casual", "MorningOffice.jpg"),
        SceneEntry.narrator("The events start: games, small activities, laughter filling the room. Amaya tries to keep up, but you can tell… She's not fully there.", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("AMAYA_MORNING_CHOICE6"),
    };
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("As you went to the circle of the group, you noticed Amaya saw something on her phone and tense up.", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "I have to go.", "single:Amaya_Sad", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "What?", "single:Celeres_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Rosario", "Is there something wrong?", "single:Rosario_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Cloma", "Is it your mother?", "single:Cloma_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "What? You! How did you know! Did everyone know?", "single:Amaya_Mad", "EventStage.jpg"),
        SceneEntry.narrator("Everyone tensed up, not saying a word. The silent confirmation was all she needed.", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "…", "single:Amaya_Sad", "EventStage.jpg"),
        SceneEntry.narrator("Her eyes felt betrayed, but she simply runs away without a single word out.", "EventStage.jpg"),
        SceneEntry.narrator("You then follow her without thinking.", "EventStage.jpg"),
        SceneEntry.narrator("Past the desks. Past the break room. Past Rosario, who calls your name.", "EveningOffice.jpg"),
        SceneEntry.narrator("Out the back door. Into the alley behind the building.", "Alley.jpg"),
        SceneEntry.narrator("And then you see her.", "Alley.jpg"),
        SceneEntry.dialogue("Amaya", "(voice breaking) I can't do this anymore.", "single:Amaya_Crying", "Alley.jpg"),
        SceneEntry.narrator("You sit down next to her. Not touching. Just present.", "Alley.jpg"),
        SceneEntry.narrator("She doesn't say anything else.", "Alley.jpg"),
        SceneEntry.narrator("But she doesn't tell you to leave either.", "Alley.jpg"),
        SceneEntry.narrator("You see her phone notification.", "Alley.jpg"),
        SceneEntry.narrator("(Brother: Mom's results came back. She has stage 3 cancer.)", "Alley.jpg"),
        SceneEntry.narrator("You didn't say a word about what you saw.", "Alley.jpg"),
        SceneEntry.narrator("The two of you sit in the alley, the flickering light above, the distant sound of the party still going on inside. A great divide between their joys and her pains.", "Alley.jpg"),
        SceneEntry.narrator("Amaya cries.", "Alley.jpg"),
        SceneEntry.narrator("And for the first time, she doesn't hide it.", "Alley.jpg"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final ChoiceEntry[] CHOICES_M8 = {
        new ChoiceEntry("Stay with Amaya", 15,0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Hey… you don't have to force yourself.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Huh? I'm not forcing anything.", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "You don't look okay.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("She pauses, then sighs softly.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "You really notice everything, don't you?", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Only when it comes to you.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("She looks away, slightly flustered.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "I'm fine… Really.", "single:Amaya_Blushing", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "You don't always have to be.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("She doesn't respond, but she stayed beside you.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Focus on the event", -10,0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "You should try to enjoy this. It's a break from work.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Yeah… I know.", "single:Amaya_Sad", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Come on, let's join them.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("She forces a smile.", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Sure.", "single:Amaya_Smile", "MorningOffice.jpg")
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
        choiceMap.put("AMAYA_MORNING_CHOICE6", CHOICES_M8);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day6Amaya.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}