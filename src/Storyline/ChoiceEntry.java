package Storyline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChoiceEntry {
    public final String                 label;
    public final int                    ppReward;
    public final Map<String, Integer>   lpRewards;   // character → LP gain
    public final SceneEntry[]           subScenes;

    // ── Backwards-compatible: single character LP ──────────────────────────

    public ChoiceEntry(String label, int lpReward, int ppReward) {
        this(label, Collections.emptyMap(), ppReward,  new SceneEntry[0]);
    }

    public ChoiceEntry(String label, int lpReward, int ppReward, String lpCharacter) {
        this(label, singleLP(lpCharacter, lpReward), ppReward, new SceneEntry[0]);
    }

    public ChoiceEntry(String label, int lpReward, int ppReward, SceneEntry... subScenes) {
        this(label, Collections.emptyMap(), ppReward, subScenes);
    }

    public ChoiceEntry(String label, int lpReward, int ppReward,
                       String lpCharacter, SceneEntry... subScenes) {
        this(label, singleLP(lpCharacter, lpReward), ppReward, subScenes);
    }

    // ── New: multi-character LP ────────────────────────────────────────────

    public ChoiceEntry(String label,
                       Map<String, Integer> lpRewards, int ppReward, SceneEntry... subScenes) {
        this.label     = label;
        this.lpRewards = Collections.unmodifiableMap(lpRewards);
        this.ppReward  = ppReward;
        this.subScenes = subScenes != null ? subScenes : new SceneEntry[0];
    }

    // ── Helper ─────────────────────────────────────────────────────────────

    private static Map<String, Integer> singleLP(String character, int amount) {
        if (character == null) return Collections.emptyMap();
        Map<String, Integer> m = new HashMap<>();
        m.put(character, amount);
        return m;
    }

    /** Convenience builder for inline multi-LP declarations. */
    public static Map<String, Integer> lp(Object... pairs) {
        if (pairs.length % 2 != 0) throw new IllegalArgumentException("lp() needs even args");
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            m.put((String) pairs[i], (Integer) pairs[i + 1]);
        }
        return m;
    }
}