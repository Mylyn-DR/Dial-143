package Storyline;

public class SceneEntry {
    public static final String TYPE_DIALOGUE = "DIALOGUE";
    public static final String TYPE_CHOICE   = "CHOICE";
    public static final String TYPE_IDENTITY = "IDENTITY_CREATION";
    public static final String TYPE_NARRATOR = "NARRATOR";
    public static final String TYPE_ROUTE    = "ROUTE";
    public static final String TYPE_CHOICE_MARKER = "CHOICE_MARKER";  // ← ADD THIS

    public final String type;
    public final String speaker;
    public final String text;
    public final String spriteSpec;
    public final String background;
    public final String choiceId;  // ← For CHOICE_MARKER

    // Regular constructor
    private SceneEntry(String type, String speaker, String text,
                       String spriteSpec, String background) {
        this(type, speaker, text, spriteSpec, background, null);
    }
    
    // New constructor with choiceId
    private SceneEntry(String type, String speaker, String text,
                       String spriteSpec, String background, String choiceId) {
        this.type = type;
        this.speaker = speaker;
        this.text = text;
        this.spriteSpec = spriteSpec;
        this.background = background;
        this.choiceId = choiceId;
    }

    // Existing factory methods...
    public static SceneEntry dialogue(String speaker, String text,
                                      String spriteSpec, String background) {
        return new SceneEntry(TYPE_DIALOGUE, speaker, text, spriteSpec, background);
    }
    
    public static SceneEntry narrator(String text, String background) {
        return new SceneEntry(TYPE_NARRATOR, "Narrator", text, "none", background);
    }

    public static SceneEntry choice() {
        return new SceneEntry(TYPE_CHOICE, "", "", "", "");
    }

    public static SceneEntry identityCreation() {
        return new SceneEntry(TYPE_IDENTITY, "", "", "", "");
    }
    
    public static SceneEntry routeSelection() {
        return new SceneEntry(TYPE_ROUTE, "", "", "", "");
    }
    
    // NEW: Choice marker with ID
    public static SceneEntry choiceMarker(String choiceId) {
        return new SceneEntry(TYPE_CHOICE_MARKER, "", "", "", "", choiceId);
    }
}