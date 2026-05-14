package Entities;

import GUI.panels.shiftComponents.CallCreationTimer;
import GUI.panels.universalComponents.TopBarComponents;
import Main.GameAPI;

public class ItemUse {
    
    private final GameAPI gameAPI;
    private final TopBarComponents topBar;
    private final CallCreationTimer callBox;
   
    private int ppMultiplierBonus = 0;    
    private int lpMultiplierDailyBonus = 0;   
    private boolean hintAvailable = false;     
    
    public ItemUse(GameAPI gameAPI, TopBarComponents topBar, CallCreationTimer callBox) {
        this.gameAPI = gameAPI;
        this.topBar = topBar;
        this.callBox = callBox;
    }
    
    public void handleItemEffect(Item.EffectType effectType, int effectValue) {
        
        switch (effectType) {
            case LP_FLAT:
                if (gameAPI.hasActiveRoute()) {
                    gameAPI.addLP(effectValue);
                    topBar.addLpPoints(effectValue);
                } 
                break;
                
            case PP_MULTIPLIER:
                ppMultiplierBonus += effectValue;
                break;
                
            case LP_MULTIPLIER_DAILY:
                lpMultiplierDailyBonus += effectValue;
                break;
                
            case TIMER_BOOST:
                if (callBox != null) {
                    callBox.applyTimerBoostToCurrentCall(effectValue);
                }
                break;
                
            case HINT_PER_CALL:
                hintAvailable = true;
                if (callBox != null) {
                    callBox.activateHint();
                }
                break;
                
            default:
                break;
        }
    }
    
    public int applyPPMultiplier(int rawPP) {
        if (ppMultiplierBonus <= 0) return rawPP;
        int bonus = Math.round(rawPP * ppMultiplierBonus / 100f);
        return rawPP + bonus;
    }
    
    public int applyLPMultiplier(int rawLP) {
        if (lpMultiplierDailyBonus <= 0) return rawLP;
        int bonus = Math.round(rawLP * lpMultiplierDailyBonus / 100f);
        return rawLP + bonus;
    }
    
    public boolean hasPPMultiplier() {
        return ppMultiplierBonus > 0;
    }

    public int getPPMultiplierBonus() {
        return ppMultiplierBonus;
    }

    public boolean hasLPMultiplier() {
        return lpMultiplierDailyBonus > 0;
    }

    public int getLPMultiplierBonus() {
        return lpMultiplierDailyBonus;
    }

    public boolean consumeHint() {
        if (!hintAvailable) return false;
        hintAvailable = false;
        return true;
    }
    
    public boolean isHintAvailable() { return hintAvailable; }
    
    public void resetPerCall() {
        hintAvailable = false;
    }
    
    public void resetPerDay() {
        ppMultiplierBonus = 0;
        lpMultiplierDailyBonus = 0;
        hintAvailable = false;
    }
}