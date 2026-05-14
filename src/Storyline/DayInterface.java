package Storyline;

public interface DayInterface {
    SceneEntry[] getMorningScenes();
    SceneEntry[] getAfternoonScenes();
    SceneEntry[] getEveningScenes();
    ChoiceEntry[] getChoicesForScene(int sceneIndex, String segment);
    ChoiceEntry[] getChoicesForMarker(String choiceId);
}