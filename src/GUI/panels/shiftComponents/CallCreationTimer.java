package GUI.panels.shiftComponents;

import Main.CallLoader;
import Entities.Call;
import Main.GameAPI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CallCreationTimer extends JPanel {

    private String   callerName          = "";
    private int      callNumber          = 0;
    private String[] testChoiceLabels    = {};

    private CallLoader callLoader;
    private Call[] currentShiftCalls;
    private int currentCallIndex;
    private Call currentCall;

    private java.util.function.BiConsumer<Integer, Integer> onPointsAwarded;
    private Runnable onCallComplete;

    // ── Timer ─────────────────────────────────────────────────────────────────
    private static final int BASE_TIMER = 5;

    private int     timerMax          = BASE_TIMER;
    private int     timerLeft         = BASE_TIMER;
    private int     timerBoostPending = 0;
    private boolean timerRunning      = false;
    private boolean timerPaused       = false;
    private Timer   countdownTimer, typewriterTimer, responseTimer, delayTimer;

    // ── Timer Boost Display ───────────────────────────────────────────────────
    private int timerBoostDisplay = 0;

    // ── Typewriter ────────────────────────────────────────────────────────────
    private String fullDesc      = "", displayedDesc = "";
    private int    typeIndex     = 0;

    // ── Response ──────────────────────────────────────────────────────────────
    private String  fullResponse      = "", displayedResponse = "";
    private int     responseIndex     = 0;
    private boolean showingResponse   = false;

    // ── State ─────────────────────────────────────────────────────────────────
    private boolean isLoaded    = false;
    private int     chosenIndex = -1;

    // ── Sticky Note hint ──────────────────────────────────────────────────────
    private int hintIndex = -1;

    private final Rectangle[] choiceRects = new Rectangle[3];
    private int hoveredChoice = -1;

    private List<Call> remainingCalls = new ArrayList<>();
    private int totalCalls = 3;

    private static final int RESPONSE_DELAY_MS = 800;

    private Rectangle skipButtonRect;
    private int       hoveredSkip = -1;
    
    private GameAPI gameAPI;
    
    public CallCreationTimer(GameAPI gameAPI) {
        setOpaque(false);
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
       
        this.gameAPI = gameAPI;
        callLoader = gameAPI.getCallLoader();
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e) { updateHover(e.getPoint()); }
        });
        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { handleClick(e.getPoint()); }
        });
        resetRemainingCalls();
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public void onPointsAwarded(java.util.function.BiConsumer<Integer, Integer> cb) {
        this.onPointsAwarded = cb;
    }

    public void onCallComplete(Runnable cb) {
        this.onCallComplete = cb;
    }

    public void resetRemainingCalls() {
        int randomShiftIndex = (int) (Math.random() * 5); // Assuming 5 shifts
        currentShiftCalls = callLoader.getCallShift(randomShiftIndex);
        remainingCalls.clear();
        for (Call call : currentShiftCalls) {
            remainingCalls.add(call);
        }
        currentCallIndex = 0;
        callNumber = 0;
    }
    
    public void applyTimerBoostToCurrentCall(int extraSeconds) {
        if (extraSeconds <= 0) return;
        if (!isLoaded) {
            timerBoostPending += extraSeconds;
            return;
        }
        if (chosenIndex == -1 && !showingResponse) {
            timerMax += extraSeconds;
            timerLeft += extraSeconds;
            timerBoostDisplay = extraSeconds;
            
            Timer fadeTimer = new Timer(2000, e -> {
                timerBoostDisplay = 0;
                repaint();
            });
            fadeTimer.setRepeats(false);
            fadeTimer.start();
            repaint();
        } else {
            timerBoostPending += extraSeconds;
        }
    }

    public void applyTimerBoostForNextCall(int extraSeconds) {
        timerBoostPending += extraSeconds;
    }

    public void activateHint() {
        if (currentCall == null) {
            hintIndex = -1;
            return;
        }
        // Get the option with highest PP reward
        currentCall.selectAnswer("BEST");
        int bestPP = currentCall.getPerformancePoints();
        int bestIndex = 0;
        
        currentCall.selectAnswer("GOOD");
        int goodPP = currentCall.getPerformancePoints();
        if (goodPP > bestPP) {
            bestPP = goodPP;
            bestIndex = 1;
        }
        
        currentCall.selectAnswer("BAD");
        int badPP = currentCall.getPerformancePoints();
        if (badPP > bestPP) {
            bestIndex = 2;
        }
        
        currentCall.selectAnswer(null); // Reset
        hintIndex = bestIndex;
        repaint();
    }
    
    public Call getCurrentCall() {
        return currentCall;
    }

    public boolean hasMoreCalls() { 
        return !remainingCalls.isEmpty(); 
    }
    
    public int getRemainingCallCount() { 
        return remainingCalls.size(); 
    }
    
    public int getCompletedCallCount() { 
        return totalCalls - remainingCalls.size(); 
    }
    
    public boolean isLoaded() { 
        return isLoaded; 
    }
    
    public int getTimerLeft() { 
        return timerLeft; 
    }
    
    public int getChosenIndex() { 
        return chosenIndex; 
    }

    // ── Load next call ────────────────────────────────────────────────────────
    public void loadTest() {
        stopAll();
        if (delayTimer != null && delayTimer.isRunning()) { 
            delayTimer.stop(); 
            delayTimer = null; 
        }

        // Check if we have more calls
        if (remainingCalls.isEmpty()) {
            if (onCallComplete != null) SwingUtilities.invokeLater(() -> onCallComplete.run());
            return;
        }

        // Get current call from remaining list
        currentCall = remainingCalls.remove(0);
        callNumber = totalCalls - remainingCalls.size();

        // Set call data from Call object
        callerName = currentCall.getCallerName();
        fullDesc = callerName + ":\n" + currentCall.getCallDescription();

        // Get choice labels from Call object
        testChoiceLabels = currentCall.getAllCallOptions();

        // Reset to no selection
        currentCall.selectAnswer(null);

        // ── Apply any pending timer boost ────────────────────────────────────
        timerMax = BASE_TIMER + timerBoostPending;
        timerLeft = timerMax;

        if (timerBoostPending > 0) {
            timerBoostDisplay = timerBoostPending;
            Timer fadeTimer = new Timer(2000, e -> {
                timerBoostDisplay = 0;
                repaint();
            });
            fadeTimer.setRepeats(false);
            fadeTimer.start();
        }

        timerBoostPending = 0;

        // Reset per-call state
        chosenIndex       = -1;
        hintIndex         = -1;
        showingResponse   = false;
        timerRunning      = false;
        timerPaused       = false;
        displayedDesc     = "";
        typeIndex         = 0;
        fullResponse      = "";
        displayedResponse = "";
        isLoaded          = true;

        startTypewriter();
        repaint();
    }

    // ── Timer controls ────────────────────────────────────────────────────────

    public void pauseTimer() {
        if (timerRunning && !timerPaused) {
            timerPaused = true;
            if (countdownTimer != null && countdownTimer.isRunning()) {
                countdownTimer.stop();
            }
            if (typewriterTimer != null && typewriterTimer.isRunning()) {
                typewriterTimer.stop();
            }
            repaint();
        }
    }

    public void resumeTimer() {
        if (timerPaused) {
            timerPaused = false;
            
            if (typeIndex < fullDesc.length()) {
                startTypewriter();
            } else if (timerLeft > 0 && chosenIndex == -1 && !showingResponse) {
                startTimer();
            } else if (timerLeft <= 0 && chosenIndex == -1 && !showingResponse) {
                int randomChoice = (int) (Math.random() * testChoiceLabels.length);
                selectChoice(randomChoice);
            }
            repaint();
        }
    }

    public boolean isTimerPaused() { return timerPaused; }

    // ── Internal ──────────────────────────────────────────────────────────────

    private void skipAllCalls() {
        stopAll();
        remainingCalls.clear();
        if (chosenIndex == -1 && onPointsAwarded != null) {
            onPointsAwarded.accept(0, 0);
        }
        if (onCallComplete != null) SwingUtilities.invokeLater(() -> onCallComplete.run());
    }

    private void startTypewriter() {
        if (typewriterTimer != null && typewriterTimer.isRunning()) typewriterTimer.stop();
        typewriterTimer = new Timer(1, e -> {
            if (timerPaused) return;
            if (typeIndex < fullDesc.length()) {
                displayedDesc = fullDesc.substring(0, ++typeIndex);
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
                startTimer();
            }
        });
        typewriterTimer.start();
    }

    private void startTimer() {
        if (chosenIndex != -1 || showingResponse) return;
        if (countdownTimer != null && countdownTimer.isRunning()) countdownTimer.stop();

        timerRunning = true;
        timerPaused = false;

        countdownTimer = new Timer(1000, e -> {
            if (timerPaused) return;
            if (timerLeft > 0 && chosenIndex == -1 && !showingResponse) {
                timerLeft--;
                repaint();
                if (timerLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    timerRunning = false;
                    if (chosenIndex == -1 && testChoiceLabels.length > 0) {
                        // Select NOANSWER instead of random
                        selectNoAnswer();
                    }
                }
            } else if (chosenIndex != -1 || showingResponse) {
                ((Timer) e.getSource()).stop();
                timerRunning = false;
            }
        });
        countdownTimer.start();
    }

    // Add this new method
    private void selectNoAnswer() {
        if (chosenIndex != -1) return;
        stopAll();
        chosenIndex = -2;  // Special index for NOANSWER
        hintIndex = -1;

        // Select NOANSWER in the current call
        currentCall.selectAnswer("NOANSWER");

        // Award points (NOANSWER gives 0)
        if (onPointsAwarded != null) {
            onPointsAwarded.accept(currentCall.getPerformancePoints(), currentCall.getSalary());
        }

        // Build response (NOANSWER has no player reply)
        String callerReply = currentCall.getCallerReply();
        int pp = currentCall.getPerformancePoints();
        int salary = currentCall.getSalary();

        fullResponse = callerName + ": " + callerReply
                     + "\n\n+Performance Points: " + pp
                     + "   +Salary: ₱" + salary;

        displayedResponse = "";
        responseIndex = 0;
        showingResponse = true;

        responseTimer = new Timer(1, e -> {
            if (responseIndex < fullResponse.length()) {
                displayedResponse = fullResponse.substring(0, ++responseIndex);
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
                if (delayTimer != null && delayTimer.isRunning()) delayTimer.stop();
                delayTimer = new Timer(RESPONSE_DELAY_MS, de -> {
                    if (onCallComplete != null) SwingUtilities.invokeLater(() -> onCallComplete.run());
                    delayTimer = null;
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
        responseTimer.start();
        repaint();
    }

    private void stopAll() {
        if (typewriterTimer != null && typewriterTimer.isRunning()) typewriterTimer.stop();
        if (countdownTimer  != null && countdownTimer.isRunning())  countdownTimer.stop();
        if (responseTimer   != null && responseTimer.isRunning())   responseTimer.stop();
        if (delayTimer      != null && delayTimer.isRunning())      { delayTimer.stop(); delayTimer = null; }
        timerRunning = false;
        timerPaused  = false;
    }

    private void selectChoice(int index) {
        if (chosenIndex != -1) return;
        stopAll();
        chosenIndex = index;
        hintIndex   = -1;

        // Map index to option type
        String optionType;
        switch(index) {
            case 0: optionType = "BEST"; break;
            case 1: optionType = "GOOD"; break;
            default: optionType = "BAD"; break;
        }

        // Select answer in the current call
        currentCall.selectAnswer(optionType);

        // Award points
        if (onPointsAwarded != null) {
            onPointsAwarded.accept(currentCall.getPerformancePoints(), currentCall.getSalary());
        }

        // Build response using currentCall
        String playerReply = currentCall.getPlayerReply();
        String callerReply = currentCall.getCallerReply();
        int pp = currentCall.getPerformancePoints();
        int salary = currentCall.getSalary();
        
        fullResponse = "You: " + playerReply
                     + "\n\n" + callerName + ": " + callerReply
                     + "\n\n+Performance Points: " + pp
                     + "   +Salary: ₱" + salary;

        displayedResponse = "";
        responseIndex     = 0;
        showingResponse   = true;

        responseTimer = new Timer(1, e -> {
            if (responseIndex < fullResponse.length()) {
                displayedResponse = fullResponse.substring(0, ++responseIndex);
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
                if (delayTimer != null && delayTimer.isRunning()) delayTimer.stop();
                delayTimer = new Timer(RESPONSE_DELAY_MS, de -> {
                    if (onCallComplete != null) SwingUtilities.invokeLater(() -> onCallComplete.run());
                    delayTimer = null;
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
        responseTimer.start();
        repaint();
    }

    private void updateHover(Point p) {
        int prev     = hoveredChoice;
        int prevSkip = hoveredSkip;
        hoveredChoice = -1;
        hoveredSkip   = -1;
        if (skipButtonRect != null && skipButtonRect.contains(p) && chosenIndex == -1 && !showingResponse)
            hoveredSkip = 0;
        for (int i = 0; i < choiceRects.length; i++) {
            if (choiceRects[i] != null && choiceRects[i].contains(p)
                    && chosenIndex == -1 && !timerPaused && !showingResponse) {
                hoveredChoice = i;
                break;
            }
        }
        if (hoveredChoice != prev || hoveredSkip != prevSkip) repaint();
    }

    private void handleClick(Point p) {
        if (skipButtonRect != null && skipButtonRect.contains(p) && chosenIndex == -1 && !showingResponse) {
            skipAllCalls();
            return;
        }
        if (chosenIndex != -1 || timerPaused || showingResponse) return;
        for (int i = 0; i < choiceRects.length; i++) {
            if (choiceRects[i] != null && choiceRects[i].contains(p)) {
                selectChoice(i);
                return;
            }
        }
    }

    // ── Paint ─────────────────────────────────────────────────────────────────

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isLoaded) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,      RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        CallCreationGUI.drawBox(g2);
        int y = CallCreationGUI.drawTitle(g2, callNumber, totalCalls);
        y = CallCreationGUI.drawDivider(g2, y);
        y = CallCreationGUI.drawDescription(g2, callerName, displayedDesc, y);

        if (!showingResponse && chosenIndex == -1)
            skipButtonRect = CallCreationGUI.drawSkipButton(g2, hoveredSkip == 0);

        if (timerPaused) {
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRoundRect(CallCreationGUI.BOX_X + 20, CallCreationGUI.BOX_Y + 20,
                             CallCreationGUI.BOX_W - 40, CallCreationGUI.BOX_H - 40, 20, 20);
            g2.setColor(new Color(100, 100, 100, 200));
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            String pauseText = "\u23F8 PAUSED";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(pauseText,
                CallCreationGUI.BOX_X + (CallCreationGUI.BOX_W - fm.stringWidth(pauseText)) / 2,
                CallCreationGUI.BOX_Y + CallCreationGUI.BOX_H / 2);
        }

        if (showingResponse)
            y = CallCreationGUI.drawResponse(g2, callerName, displayedResponse, y);

        // Draw timer bar with boost display
        CallCreationGUI.drawTimerBar(g2, timerLeft, timerMax, timerBoostDisplay);

        if (timerRunning && chosenIndex == -1 && !showingResponse)
            CallCreationGUI.drawChoices(g2, testChoiceLabels, chosenIndex,
                                     hoveredChoice, choiceRects, hintIndex);

        g2.dispose();
    }
}