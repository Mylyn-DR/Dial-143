package Storyline.RosarioRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day6Rosario implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("It's the morning of the company celebration. Everyone is busy preparing decorations for the event.", "MorningElevator.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Hmmmm… That's weird. Sir Rosario is usually here early.", "none", "MorningElevator.jpg"),
        SceneEntry.narrator("You accidentally overhear your co-workers talking about Sir Rosario.", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker A", "Hey, have you seen Sir Rosario today?", "single:CoworkerA", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker B", "Not yet. Maybe he's preparing something for the event.", "single:CoworkerB", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker C", "Oh… I hear that it's true that he prepares something special.", "single:CoworkerC", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker A", "Actually… have you noticed Sir Rosario lately?", "single:CoworkerA", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker C", "Why? What does Sir Rosario do?", "single:CoworkerC", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker B", "Well… he's been smiling a lot these days.", "single:CoworkerB", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker A", "And sometimes he just stares at his phone with his heart-eye look.", "single:CoworkerA", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker B", "I noticed that too!", "single:CoworkerB", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker C", "Really??? I think someone has already made him happy… Hmmmm… who's that lucky person?", "single:CoworkerC", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker A", "He's not like that before… I wonder who made him like that.", "single:CoworkerA", "MorningElevator.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Heart-eyes??? Someone made him happy???? Wait… are they talking about…?", "none", "MorningElevator.jpg"),
        SceneEntry.dialogue("CoWorker B", "Whoever it is, that person must be really special to him.", "single:CoworkerB", "MorningElevator.jpg"),
        SceneEntry.narrator("With Sir Rosario still missing, the preparations continue… but the small mystery makes you curious.", "MorningElevator.jpg"),
    };
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("The celebration is finally over. After a long and busy day, everyone slowly leaves the office.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Today is really tiring. But also fun and memorable.", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("You see Sir Rosario near the entrance, talking with Amaya.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Sir Rosario, do you want me to walk with you to the parking lot?", "single:Amaya_Casual", "EveningOffice.jpg"),
        SceneEntry.narrator("You hesitate… then gather your courage.", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Sir Rosario… if it's okay with you… can you go home with me instead? Just us two?", "none", "EveningOffice.jpg"),
        SceneEntry.narrator("Rosario and Amaya look surprised. Then Amaya quietly excuses herself and walks away.", "EveningOffice.jpg"),
        SceneEntry.narrator("Now the two of you are alone, free to talk without anyone listening.", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Just the two of us?", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "Actually… I'm glad you asked.", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "There's something I've been wanting to tell you for a while now.", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Wait… what is he going to say?", "none", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "These past few days… spending time with you made me really happy.", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "I realized I like being with you… more than just a co-worker.", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "I think… I've started to like you.", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.dialogue("Rosario", "What about you?", "single:Rosario_Smile", "EveningOffice.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY6_EVENING_CHOICE"),
        SceneEntry.narrator("As you both walk home together, the night suddenly feels warmer than before.", "StreetEvening.jpg"),
    };

    private static final SceneEntry[] AFTERNOON = {};
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Accept", 100,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "I feel the same way… I like you too.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Really?", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "That makes me really happy.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.narrator("Suddenly, Rosario hugs you. You didn't expect it, but you hugged him back.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) This… might be the best day of my life.", "none", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Reject", -50,0, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "Rosario… I'm really sorry. I don't think I feel the same way.", "none", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I see… thank you for being honest.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "I hope we can still stay good co-workers.", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Why do I suddenly feel a little guilty?", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Even after the confession, the two of you still walk home together, quietly.", "EveningOffice.jpg")
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
        choiceMap.put("ROSARIO_DAY6_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day6Rosario.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}