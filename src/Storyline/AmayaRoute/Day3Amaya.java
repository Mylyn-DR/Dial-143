package Storyline.AmayaRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day3Amaya implements DayInterface {

    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] AFTERNOON = {}; 
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("Conflicted with these feelings, PC slaps their face and tries to look up ways to deal with their emotions. However, a familiar face blocks their view on their phone.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Heya! What are you searching for?", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Ahh! You scared me!", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Sorry! You just looked extremely distracted! Reminds me of myself when I forgot where I placed my keys.", "single:Amaya_Casual", "StreetEvening.jpg"),
        SceneEntry.dialogue("{name}", "Well, don't startle me like that!", "none", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "I'm sorry! Hey, let's grab some snacks at 6/70. My treat, like usual.", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.choiceMarker("AMAYA_EVENING_CHOICE"),
        SceneEntry.narrator("Both take their time lounging around the store. New and old customers pass by, and the quiet static of the lights make the ambience feel nostalgic for the both of you.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "So, how's life after college?", "single:Amaya_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "I don't even want to talk about the pain of job searching.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "Ha! Same.", "single:Amaya_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "I guess life's been not easy for both of us huh?", "single:Amaya_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Wow, our overachiever has it hard too?", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "Just because I work hard, doesn't mean the end results have all been pretty for me.", "single:Amaya_Sad", "ConvenienceStore.jpg"),
        SceneEntry.narrator("She stirs her drink with a small, plastic straw. The ice clinks against the cup.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "You used to say you'd never end up in a place like this.", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "(laughing, but hollow) A call center? Yeah. I know.", "single:Amaya_Sad", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "What happened to the girl who wanted to be a big time animator?", "none", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "…", "single:Amaya_Sad", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "Life happened, PC.", "single:Amaya_Sad", "ConvenienceStore.jpg"),
        SceneEntry.narrator("She doesn't elaborate. You remember high school. Her eyes were bright, as her laughs grew louder and louder every time she talked about her interest. Her arm chair table was always stacked with notebooks you knew were filled with drawings she sketches from time to time.", "ConvenienceStore.jpg"),
        SceneEntry.narrator("That girl feels far away now.", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("Amaya", "(standing up) We should head back. Break's almost over.", "single:Amaya_Casual", "ConvenienceStore.jpg"),
        SceneEntry.dialogue("{name}", "Yeah.", "none", "ConvenienceStore.jpg"),
        SceneEntry.narrator("She walks ahead of you. For a moment, you swear you see her shoulders shake – but then she turns back, smiling.", "StreetEvening.jpg"),
        SceneEntry.dialogue("Amaya", "Coming?", "single:Amaya_Smile", "StreetEvening.jpg"),
        SceneEntry.narrator("You follow. But something about her smile feels different tonight.", "StreetEvening.jpg"),
    };
    private static final ChoiceEntry[] CHOICES_E6 = {
        new ChoiceEntry("Accept it", 5, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Alright, fine. I could use a break.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Good! I was already planning to go anyway.", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "So I didn't really have a choice?", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Nope.", "single:Amaya_Smile", "StreetEvening.jpg"),
            SceneEntry.narrator("Later at 6/70, Amaya pays for both drinks like she always does.", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Amaya", "Don't worry, it's my treat. You look like you need it.", "single:Amaya_Smile", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("{name}", "You're still the same, huh?", "none", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Amaya", "Of course. Someone has to take care of you.", "single:Amaya_Smile", "ConvenienceStore.jpg")
        ),
        new ChoiceEntry("Reject it", -5, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "No need, I'll buy my own drink.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Seriously? You're passing up a chance like this?", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Maybe another time.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Fine, I won't push you.", "single:Amaya_Sad", "StreetEvening.jpg"),
            SceneEntry.narrator("Later, you both bought your own drinks of choice.", "ConvenienceStore.jpg")
        ),
        new ChoiceEntry("Pay for hers", 10, 0, Character.AMAYA,
            SceneEntry.dialogue("{name}", "Actually… I'll go. But this time, I'm paying.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Wait, what?", "single:Amaya_Surprised", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "You always treat me. It's my turn.", "none", "StreetEvening.jpg"),
            SceneEntry.dialogue("Amaya", "Wow… who are you and what did you do to my childhood friend?", "single:Amaya_Casual", "StreetEvening.jpg"),
            SceneEntry.dialogue("{name}", "Very funny.", "none", "StreetEvening.jpg"),
            SceneEntry.narrator("Later at 6/70, you pay for both drinks.", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Amaya", "Thanks. I guess you finally grew up.", "single:Amaya_Smile", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("{name}", "Don't get used to it.", "none", "ConvenienceStore.jpg"),
            SceneEntry.dialogue("Amaya", "Too late. I already am.", "single:Amaya_Smile", "ConvenienceStore.jpg")
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
        choiceMap.put("AMAYA_EVENING_CHOICE", CHOICES_E6);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day3Amaya.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }
    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}