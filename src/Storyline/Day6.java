/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Storyline;


public class Day6 implements DayInterface {
    
    private static int cachedPP = 0;
    
    private static final SceneEntry[] MORNING = {};
    private static final SceneEntry[] EVENING = {};
    
    private static final SceneEntry[] AFTERNOON = {
        SceneEntry.narrator("Employees slowly arrive, chatting and enjoying the relaxed atmosphere.", "EventStage.jpg"),
        SceneEntry.narrator("You walk inside and immediately spot Amaya waving at you.", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "Hey! Over here!", "single:Amaya_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "Wow… the office looks so different without everyone stressing over work.", "none", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "I know, right? I almost forgot what a normal day feels like.", "single:Amaya_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Cloma", "I'm glad you came! HR worked really hard organizing this event.", "single:Cloma_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "It looks great, Cloma.", "none", "EventStage.jpg"),
        SceneEntry.dialogue("Cloma", "Thank you! Just relax and enjoy today.", "single:Cloma_Smile", "EventStage.jpg"),
        SceneEntry.narrator("Nearby, you see Rosario standing with his arms crossed, observing everything.", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "(whispers) Even at a party, he looks like he's supervising us.", "single:Amaya_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Rosario", "I heard that.", "single:Rosario_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "Eep—!", "single:Amaya_Surprised", "EventStage.jpg"),
        SceneEntry.dialogue("Rosario", "This is still a company event. Try not to cause trouble.", "single:Rosario_Casual", "EventStage.jpg"),
        SceneEntry.narrator("Suddenly, someone walks past slowly with headphones around his neck. It's Celeres.", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "Celeres? I didn't expect to see you here.", "none", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "HR said attendance was important.", "single:Celeres_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Cloma", "(laughs softly) I might have mentioned that.", "single:Cloma_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("Rosario", "Mophead actually showed up.", "single:Rosario_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "I'm only here for the free food.", "single:Celeres_Casual", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "Honestly… same.", "single:Amaya_Smile", "EventStage.jpg"),
        SceneEntry.narrator("After a while, everyone gathers near the stage.", "EventStage.jpg"),
        SceneEntry.narrator("Sir Khai, the CEO of the company, walks forward with a microphone.", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "Good afternoon, everyone.", "single:Khai_EyesClosed", "EventStage.jpg"),
        SceneEntry.narrator("Everyone responds.", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "Today isn't about work. Today is about appreciating the people who make this company run successfully.", "single:Khai_EyesClosed", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "But before we enjoy the rest of the event, we will recognize employees who performed exceptionally well this week.", "single:Khai_EyesOpen", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "The award for Outstanding Technical Support goes to… Celeres!", "single:Khai_EyesOpen", "EventStage.jpg"),
        SceneEntry.dialogue("Celeres", "(sleepy smile) Thanks… I guess.", "single:Celeres_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "Next, the Most Reliable Staff award goes to… Amaya!", "single:Khai_EyesOpen", "EventStage.jpg"),
        SceneEntry.dialogue("Amaya", "Wow! Thank you, Sir!", "single:Amaya_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "Next, the Exemplary HR Employee award goes to… Miss Cloma!", "single:Khai_EyesOpen", "EventStage.jpg"),
        SceneEntry.dialogue("Cloma", "I'm honored. Thank you.", "single:Cloma_Smile", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "And the Leadership Excellence award goes to… Rosario!", "single:Khai_EyesOpen", "EventStage.jpg"),
        SceneEntry.dialogue("Rosario", "(slightly flustered) Thank you… I appreciate it.", "single:Rosario_Blushing", "EventStage.jpg"),
        SceneEntry.narrator("Then the room was quiet. It was the announcement of another award.", "EventStage.jpg"),
        SceneEntry.dialogue("Sir Khai", "And this week's 'Outstanding Employee' award goes to…", "single:Khai_EyesOpen", "EventStage.jpg"),
         SceneEntry.narrator("Drum rolls.", "EventStage.jpg"),
        SceneEntry.choiceMarker("AWARD_CHOICE"),
        SceneEntry.narrator("The afternoon slowly turns into evening.", "EventStage.jpg"),
        SceneEntry.narrator("Music plays softly while coworkers laugh, eat, and talk.", "EventStage.jpg"),
        SceneEntry.narrator("For once, the office feels peaceful.", "EventStage.jpg"),
        SceneEntry.narrator("You glance at your coworkers—Amaya joking around, Cloma chatting happily, Rosario trying to look serious, and Celeres quietly listening to music.", "EventStage.jpg"),
        SceneEntry.dialogue("{name}", "(mind) Maybe days like this are what make the long work weeks worth it.", "none", "EventStage.jpg"),
    };
    
    // Called before loading Day 6 afternoon
    public static void setPlayerPP(int pp) {
        cachedPP = pp;
    }
    
    @Override
    public SceneEntry[] getMorningScenes() { return MORNING; }
    @Override
    public SceneEntry[] getAfternoonScenes() { return AFTERNOON; }
    @Override
    public SceneEntry[] getEveningScenes() { return EVENING; }
    
    @Override
    public ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment) {
        return null;
    }
    
    @Override
    public ChoiceEntry[] getChoicesForMarker(String choiceId) {
        if ("AWARD_CHOICE".equals(choiceId)) {
            if (cachedPP >= 60) {  // High performance threshold
                return getHighAwardChoices();
            } else {
                return getLowAwardChoices();
            }
        }
        return null;
    }
    
    private ChoiceEntry[] getHighAwardChoices() {
        return new ChoiceEntry[]{
            new ChoiceEntry("Accept Award", 0,0,
                SceneEntry.dialogue("Sir Khai", "{name}!", "single:Khai_EyesOpen", "EventStage.jpg"),
                SceneEntry.narrator("The room fills with applause.", "EventStage.jpg"),
                SceneEntry.dialogue("Amaya", "Wait—that's you!", "single:Amaya_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Cloma", "Congratulations!", "single:Cloma_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Rosario", "(nods slightly) Good work. I expected nothing less.", "single:Rosario_Smile", "EventStage.jpg"),
                SceneEntry.narrator("You walk to the stage.", "EventStage.jpg"),
                SceneEntry.dialogue("Sir Khai", "Your hard work and dedication did not go unnoticed. Keep it up.", "single:Khai_EyesClosed", "EventStage.jpg"),
                SceneEntry.dialogue("{name}", "Thank you, Sir Khai.", "none", "EventStage.jpg"),
                SceneEntry.narrator("When you return—", "EventStage.jpg"),
                SceneEntry.dialogue("Amaya", "Drinks are on you tonight!", "single:Amaya_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Cloma", "You deserve it.", "single:Cloma_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Celeres", "…Nice work.", "single:Celeres_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Rosario", "Don't let the award get to your head.", "single:Rosario_Casual", "EventStage.jpg"),
                SceneEntry.dialogue("Amaya", "He's just jealous.", "single:Amaya_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Rosario", "I am not.", "single:Rosario_Casual", "EventStage.jpg"),
                SceneEntry.narrator("Everyone laughs.", "EventStage.jpg")
            )
        };
    }
    
    private ChoiceEntry[] getLowAwardChoices() {
        return new ChoiceEntry[]{
            new ChoiceEntry("No Award", 0, 0,
                SceneEntry.narrator("Sir Khai finishes announcing the award winners.", "EventStage.jpg"),
                SceneEntry.narrator("Your name wasn't called.", "EventStage.jpg"),
                SceneEntry.dialogue("Amaya", "Hey… don't feel bad.", "single:Amaya_Casual", "EventStage.jpg"),
                SceneEntry.dialogue("Cloma", "There's always next week.", "single:Cloma_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Rosario", "Improvement takes time. Keep working hard.", "single:Rosario_Smile", "EventStage.jpg"),
                SceneEntry.dialogue("Celeres", "Awards are loud anyway.", "single:Celeres_Casual", "EventStage.jpg"),
                SceneEntry.dialogue("{name}", "Thank you, guys! I should do my very best next time.", "none", "EventStage.jpg"),
                SceneEntry.narrator("They all smile.", "EventStage.jpg")
            )
        };
    }
}