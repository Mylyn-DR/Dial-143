package Main;

import Entities.Item;
import Storyline.RouteManager;
import java.io.*;
import java.util.prefs.Preferences;

public class SaveManager {
    
    private static final String SAVE_DIR = "src/SavedFiles/";
    private Preferences prefs;
    private GameAPI gameAPI;  // Need this to get item templates
    
    public SaveManager(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        prefs = Preferences.userRoot().node("dial143");
        // Create saves directory
        new File(SAVE_DIR).mkdirs();
    }
    
    // ==================== SAVE ====================
    public void saveGame(GameState state, int slot) {
        String path = SAVE_DIR + "slot_" + slot + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println(state.getCurrentDay());
            writer.println(state.getPP());
            writer.println(state.getSalary());
            writer.println(state.getCallsCompletedToday());
            writer.println(state.getCurrentSegment());
            writer.println(state.getDialogueScene());      // ← Scene index
            writer.println(state.getCurrentDayScript());   // ← Which day script ("Day1", "Day2", etc.)
            writer.println(state.isDialogueDone());

            // ===== PLAYER IDENTITY =====
            writer.println(state.getPlayerName());
            writer.println(state.getPlayerGender());
            writer.println(state.getPlayerPronoun());

            // ===== ACTIVE ROUTE =====
            writer.println(state.getActiveCharacter() == null ? "null" : state.getActiveCharacter());

            // ===== LP PER CHARACTER =====
            writer.println(state.getLPForCharacter("Amaya"));
            writer.println(state.getLPForCharacter("Rosario"));
            writer.println(state.getLPForCharacter("Cloma"));
            writer.println(state.getLPForCharacter("Celeres"));

            // ===== INVENTORY =====
            writer.println(state.getInventory().size());
            for (Item item : state.getInventory()) {
                writer.println(item.getName());
                writer.println(item.getQuantity());
            }

            // ===== BONUSES =====
            writer.println(state.getPPMultiplierBonus());
            writer.println(state.getLPMultiplierDailyBonus());
            writer.println(state.isHintAvailable());

            // ===== METADATA for display =====
            prefs.put("slot_" + slot + "_info", 
                "Day " + state.getCurrentDay() + " | " + 
                state.getPlayerName() + " | PP " + state.getPP());
            prefs.putLong("slot_" + slot + "_time", System.currentTimeMillis());
            
            // ===== DEBUG: SAVE VERIFICATION =====
            System.out.println("═══════════════════════════════════════");
            System.out.println("💾 SAVE GAME - Slot " + slot);
            System.out.println("───────────────────────────────────────");
            System.out.println("  currentDay:        " + state.getCurrentDay());
            System.out.println("  pp:                " + state.getPP());
            System.out.println("  salary:            " + state.getSalary());
            System.out.println("  callsCompleted:    " + state.getCallsCompletedToday());
            System.out.println("  currentSegment:    " + state.getCurrentSegment());
            System.out.println("  dialogueScene:     " + state.getDialogueScene());
            System.out.println("  currentDayScript:  " + state.getCurrentDayScript());
            System.out.println("  dialogueDone:      " + state.isDialogueDone());
            System.out.println("  playerName:        " + state.getPlayerName());
            System.out.println("  activeCharacter:   " + state.getActiveCharacter());
            System.out.println("  lpAmaya:           " + state.getLPForCharacter("Amaya"));
            System.out.println("  lpRosario:         " + state.getLPForCharacter("Rosario"));
            System.out.println("  inventorySize:     " + state.getInventory().size());
            System.out.println("  ppBonus:           " + state.getPPMultiplierBonus());
            System.out.println("  lpBonus:           " + state.getLPMultiplierDailyBonus());
            System.out.println("═══════════════════════════════════════");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== LOAD ====================
    public boolean loadGame(GameState state, int slot) {
        String path = SAVE_DIR + "slot_" + slot + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            // ===== PROGRESS (MUST BE IN SAME ORDER AS SAVE) =====
            int currentDay = Integer.parseInt(reader.readLine());
            int pp = Integer.parseInt(reader.readLine());
            int salary = Integer.parseInt(reader.readLine());
            int callsCompleted = Integer.parseInt(reader.readLine());
            String segment = reader.readLine();
            int dialogueScene = Integer.parseInt(reader.readLine());
            String currentDayScript = reader.readLine();
            boolean dialogueDone = Boolean.parseBoolean(reader.readLine());

            // ===== PLAYER IDENTITY =====
            String playerName = reader.readLine();
            String playerGender = reader.readLine();
            String playerPronoun = reader.readLine();

            // ===== ACTIVE ROUTE =====
            String activeChar = reader.readLine();
            if ("null".equals(activeChar)) activeChar = null;

            // ===== LP PER CHARACTER =====
            int lpAmaya = Integer.parseInt(reader.readLine());
            int lpRosario = Integer.parseInt(reader.readLine());
            int lpCloma = Integer.parseInt(reader.readLine());
            int lpCeleres = Integer.parseInt(reader.readLine());

            // ===== INVENTORY =====
            state.clearInventory();
            int inventorySize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < inventorySize; i++) {
                String itemName = reader.readLine();
                int quantity = Integer.parseInt(reader.readLine());
                Item template = gameAPI.getItemCatalog().get(itemName);
                if (template != null) {
                    for (int q = 0; q < quantity; q++) {
                        state.addItem(template);
                    }
                }
            }

            // ===== BONUSES =====
            int ppBonus = Integer.parseInt(reader.readLine());
            int lpBonus = Integer.parseInt(reader.readLine());
            boolean hintAvail = Boolean.parseBoolean(reader.readLine());

            // ===== APPLY EVERYTHING TO GAMESTATE =====
            state.setCurrentDay(currentDay);
            state.setPP(pp);
            state.setSalary(salary);
            state.setCallsCompletedToday(callsCompleted);
            state.setCurrentSegment(segment);
            state.setDialogueScene(dialogueScene);
            state.setCurrentDayScript(currentDayScript);
            state.setDialogueDone(dialogueDone);
            state.setPlayerIdentity(playerName, playerGender, playerPronoun);

            // Handle active route
            if (activeChar != null && !activeChar.isEmpty()) {
                RouteManager route = gameAPI.getAllRoutes().get(activeChar);
                if (route != null) {
                    state.setActiveCharacter(activeChar);
                    state.setActiveRoute(route);
                } else {
                    state.setActiveCharacter(null);
                    state.setActiveRoute(null);
                }
            } else {
                state.setActiveCharacter(null);
                state.setActiveRoute(null);
            }

            // Set LP values
            state.setLPForCharacter("Amaya", lpAmaya);
            state.setLPForCharacter("Rosario", lpRosario);
            state.setLPForCharacter("Cloma", lpCloma);
            state.setLPForCharacter("Celeres", lpCeleres);

            // Set bonuses
            state.addPPMultiplierBonus(ppBonus);
            state.addLPMultiplierDailyBonus(lpBonus);
            state.setHintAvailable(hintAvail);

            
            // ===== DEBUG: LOAD VERIFICATION =====
            System.out.println("═══════════════════════════════════════");
            System.out.println("📂 LOAD GAME - Slot " + slot);
            System.out.println("───────────────────────────────────────");
            System.out.println("  currentDay:        " + currentDay);
            System.out.println("  pp:                " + pp);
            System.out.println("  salary:            " + salary);
            System.out.println("  callsCompleted:    " + callsCompleted);
            System.out.println("  segment:           " + segment);
            System.out.println("  dialogueScene:     " + dialogueScene);
            System.out.println("  currentDayScript:  " + currentDayScript);
            System.out.println("  dialogueDone:      " + dialogueDone);
            System.out.println("  playerName:        " + playerName);
            System.out.println("  activeChar:        " + activeChar);
            System.out.println("  lpAmaya:           " + lpAmaya);
            System.out.println("  lpRosario:         " + lpRosario);
            System.out.println("  inventorySize:     " + inventorySize);
            System.out.println("  ppBonus:           " + ppBonus);
            System.out.println("  lpBonus:           " + lpBonus);
            System.out.println("───────────────────────────────────────");
            System.out.println("  AFTER applying to GameState:");
            System.out.println("  state.getCurrentDay():      " + state.getCurrentDay());
            System.out.println("  state.getDialogueScene():   " + state.getDialogueScene());
            System.out.println("  state.getCurrentDayScript(): " + state.getCurrentDayScript());
            System.out.println("═══════════════════════════════════════");
            System.out.println("LOAD COMPLETE - Day: " + currentDay + ", Scene: " + dialogueScene);
            return true;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ==================== UTILITY ====================
    public boolean saveExists(int slot) {
        return new File(SAVE_DIR + "slot_" + slot + ".txt").exists();
    }
    
    public void deleteSave(int slot) {
        File file = new File(SAVE_DIR + "slot_" + slot + ".txt");
        if (file.exists()) file.delete();
        prefs.remove("slot_" + slot + "_info");
        prefs.remove("slot_" + slot + "_time");
    }
    
    public String getSaveInfo(int slot) {
        return prefs.get("slot_" + slot + "_info", null);
    }
    
    public long getSaveTime(int slot) {
        return prefs.getLong("slot_" + slot + "_time", 0);
    }
}