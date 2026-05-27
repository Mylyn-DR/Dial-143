package Storyline.RosarioRoute;

import Storyline.*;
import Entities.Character;
import java.util.HashMap;

public class Day4Rosario implements DayInterface {
    
    private static final SceneEntry[] MORNING = {
        SceneEntry.narrator("The morning felt normal, but your mind kept drifting back to Rosario.", "MorningOffice.jpg"),
        SceneEntry.narrator("While you were working, Amaya suddenly walked over to your desk.", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "Hi! I have something to tell you.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Oh, hello Amaya! It's so early for gossip, huh? (laughs)", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "It's quick, I promise. I just heard something.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "What is it? I hope it won't ruin my good mood.", "none", "MorningOffice.jpg"),
        SceneEntry.dialogue("Amaya", "It won't, trust me. (winks) You know Rosario? He's really strict… but he's fair.", "single:Amaya_Smile", "MorningOffice.jpg"),
        SceneEntry.dialogue("{name}", "Yeah, he is strict. But I kind of like that about him.", "none", "MorningOffice.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY4_MORNING_CHOICE"),
    };
    
    private static final SceneEntry[] AFTERNOON = {};
    
    private static final SceneEntry[] EVENING = {
        SceneEntry.narrator("It was late at night.", "EveningOffice.jpg"),
        SceneEntry.narrator("Footsteps echo through the building as each floor empties minute by minute until the entire building is eerily quiet.", "EveningOffice.jpg"),
        SceneEntry.narrator("The halls are dimly lit, just enough to guide you towards the exit.", "EveningOffice.jpg"),
        SceneEntry.narrator("Walking towards the elevator, you catch a glimpse of Rosario's office. Its lights are still on, but the curtains prevent them from escaping through the glass walls.", "EveningOffice.jpg"),
        SceneEntry.narrator("It's been a while since you last saw him.", "EveningOffice.jpg"),
        SceneEntry.choiceMarker("ROSARIO_DAY4_EVENING_CHOICE"),
    };
    
    private static final ChoiceEntry[] CHOICES_MORNING = {
        new ChoiceEntry("Continue Listening To Amaya", 10,0, Character.ROSARIO,
            SceneEntry.dialogue("Amaya", "Right? He's very hands-on too.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "And you know what? He always stays late in the office. I saw him working last night.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Another thing… he actually likes helping people, but he does it quietly.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Oh… I'm slowly learning more about him. (nods)", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Anyway, I should go back to my workstation now. (laughs)", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "You should… before Rosario catches us talking.", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("Amaya laughs and walks back to her workstation.", "MorningOffice.jpg"),
            SceneEntry.narrator("You return to your work, but a small smile appears on your face.", "MorningOffice.jpg"),
            SceneEntry.narrator("Somehow, you feel even more curious about Rosario.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Ignore Amaya Completely", -10,0, Character.ROSARIO,
            SceneEntry.narrator("You pretend to focus on your work and keep typing.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Sorry Amaya, I need to finish this first.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Oh… okay then.", "single:Amaya_Casual", "MorningOffice.jpg"),
            SceneEntry.narrator("Amaya shrugs and walks back to her workstation.", "MorningOffice.jpg"),
            SceneEntry.narrator("You continue working, but a small part of you wonders what she was about to say.", "MorningOffice.jpg")
        ),
        new ChoiceEntry("Go Back To Work Quickly", -5,5, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "Amaya, we should get back to work. Rosario might see us.", "none", "MorningOffice.jpg"),
            SceneEntry.dialogue("Amaya", "Alright, alright. No need to be so serious.", "single:Amaya_Smile", "MorningOffice.jpg"),
            SceneEntry.narrator("She laughs and walks away.", "MorningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) Oh… I'm slowly learning more about him. (nods)", "none", "MorningOffice.jpg"),
            SceneEntry.narrator("You return to your work, but a small smile forms on your face.", "MorningOffice.jpg"),
            SceneEntry.narrator("Somehow, you feel even more curious about Rosario.", "MorningOffice.jpg")
        ),
    };
    
    private static final ChoiceEntry[] CHOICES_EVENING = {
        new ChoiceEntry("Go Check On Rosario", 5,5, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "(mind) Maybe he's still working… I should check on him.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Your feet slowly carry you towards his office door.", "EveningOffice.jpg"),
            SceneEntry.narrator("You hesitate for a moment… then knock. (KNOCK KNOCK)", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Come in.", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.narrator("You open the door slightly and peek inside.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Sir Rosario…. you're still here?", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Rosario looks up from the documents on his desk. He seems surprised to see you.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "PC? Why are you still here?", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "I was about to leave… but I saw your lights on… I just wanted to check if you were okay.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Rosario pauses for a moment.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "That's… thoughtful of you.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "But you should head home. It's already late.", "single:Rosario_Smile", "EveningOffice.jpg"),
            SceneEntry.narrator("Rosario gives you a small smile before looking back at his work.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) It's that look again… But I shouldn't get my hopes up yet. Maybe it's just my imagination.", "none", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Leave Message For Rosario", 3,0, Character.ROSARIO,
            SceneEntry.narrator("You take out your phone and open Rosario's contact. You stare at the message box for a moment before typing.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "Sir, I noticed you're still in the office. Don't forget to rest.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You hesitate… then press send. A moment later, your phone vibrates.", "EveningOffice.jpg"),
            SceneEntry.dialogue("Rosario", "Thank you for the concern. I'll finish soon. Please go home safely.", "single:Rosario_Casual", "EveningOffice.jpg"),
            SceneEntry.narrator("You stare at the message for a moment.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) He replied quickly… Maybe he appreciates it… Or maybe he just replies like this to everyone…", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("Even so, your heart feels a little lighter.", "EveningOffice.jpg")
        ),
        new ChoiceEntry("Leave The Office Now", 0,2, Character.ROSARIO,
            SceneEntry.dialogue("{name}", "(mind) He might be busy… I shouldn't interrupt him.", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You glance at Rosario's office one last time. The lights are still on. You wonder how long he has been working like this.", "EveningOffice.jpg"),
            SceneEntry.narrator("But you shake your head and walk toward the exit. The cool night air greets you outside the building.", "EveningOffice.jpg"),
            SceneEntry.dialogue("{name}", "(mind) He really works hard…", "none", "EveningOffice.jpg"),
            SceneEntry.narrator("You pause for a moment, watching the lights of the building behind you.", "EveningOffice.jpg"),
            SceneEntry.narrator("Somehow, it feels like you glimpsed a small part of his dedication… and it makes you respect him even more.", "EveningOffice.jpg")
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
        choiceMap.put("ROSARIO_DAY4_MORNING_CHOICE", CHOICES_MORNING);
        choiceMap.put("ROSARIO_DAY4_EVENING_CHOICE", CHOICES_EVENING);
    }

    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        System.out.println("Day4Rosario.getChoicesForMarker(" + choiceId + ") called");
        return choiceMap.get(choiceId);
    }

    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
}