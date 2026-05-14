package GUI.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import GUI.panels.universalComponents.ImageButtonCreation;

public class CreditsPanel extends JPanel {

    private MainFrame      mainFrame;
    private JDialog        dialog;
    private WindowListener currentListener;
    private boolean        isHiding = false;

    private static final Color BG_WHITE     = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(210, 215, 230);
    private static final Color TEXT_PRIMARY = new Color(30,  40,  80);
    private static final Color TEXT_MUTED   = new Color(100, 110, 140);
    private static final Color TEXT_HINT    = new Color(160, 165, 185);
    private static final Color DIVIDER      = new Color(230, 233, 242);
    
    private Font gameTitleFont;  // small eyebrow above heading
    private Font titleFont;      // "Credits" heading
    private Font nameFont;       // credit name (bold)
    private Font roleFont;       // credit role (regular)

    private ImageButtonCreation btnClose;

    private static final String[][] CREDITS = {
        { "Back-End Developer",        "Mylyn Del Rosario"      },
        { "Front-End Developer",     "Anne Gwyn Amaya"        },
        { "UI Artist", "Ravenelle Celeres"      },
        { "Lead Writer",      "Nicole Andrea Cloma"    },
        { "Backgrounds",    "min-chii, ???"      },
        { "Audios",       "???"      },
    };

    public CreditsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        loadFonts();
        setPreferredSize(new Dimension(340, 520));
        setOpaque(false);
        buildUI();
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public void showAsPopup(WindowListener listener) {
        if (isHiding) return;
        this.currentListener = listener;
        if (dialog != null && dialog.isVisible()) dialog.dispose();

        dialog = new JDialog(mainFrame, "Credits", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.setContentPane(this);
        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setAlwaysOnTop(true);

        if (listener != null) dialog.addWindowListener(listener);

        dialog.addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e) { isHiding = false; }
        });
        dialog.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) hidePopup();
            }
        });
        dialog.setFocusable(true);
        dialog.setVisible(true);
    }

    public void showAsPopup() { showAsPopup(null); }

    public void hidePopup() {
        if (dialog != null && dialog.isVisible()) {
            if (currentListener != null) dialog.removeWindowListener(currentListener);
            dialog.dispose();
            dialog = null;
        }
    }

    // ── UI ────────────────────────────────────────────────────────────────────

    private void buildUI() {
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new BorderLayout(0, 0)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g2.dispose();
            }
        };
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(28, 32, 24, 32));

        // ── Header: eyebrow → title → subtitle → gap → separator ─────────────
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Credits", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        headerPanel.add(Box.createVerticalStrut(20));

        JSeparator topSep = new JSeparator(SwingConstants.HORIZONTAL);
        topSep.setForeground(DIVIDER);
        topSep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        headerPanel.add(topSep);

        wrapper.add(headerPanel, BorderLayout.NORTH);

        // ── Credits rows ──────────────────────────────────────────────────────
        JPanel listPanel = new JPanel();
        listPanel.setOpaque(false);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        for (int i = 0; i < CREDITS.length; i++) {
            listPanel.add(buildCreditRow(CREDITS[i][0], CREDITS[i][1], i < CREDITS.length - 1));
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(listPanel, new GridBagConstraints());
        wrapper.add(centerPanel, BorderLayout.CENTER);

        // ── Footer: copyright + close button ─────────────────────────────────
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));

        JSeparator botSep = new JSeparator(SwingConstants.HORIZONTAL);
        botSep.setForeground(DIVIDER);
        botSep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        footerPanel.add(botSep);

        footerPanel.add(Box.createVerticalStrut(12));
        btnClose = new ImageButtonCreation("Close");
        btnClose.setImage("btn.png");
        btnClose.setFont(roleFont);
        btnClose.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnClose.setPreferredSize(new Dimension(276, 40));
        btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClose.addActionListener(e -> hidePopup());
        footerPanel.add(btnClose);

        wrapper.add(footerPanel, BorderLayout.SOUTH);
        add(wrapper, BorderLayout.CENTER);
    }

    private JPanel buildCreditRow(String role, String name, boolean showDivider) {
        JPanel outer = new JPanel(new BorderLayout(0, 0));
        outer.setOpaque(false);
        outer.setMaximumSize(new Dimension(Integer.MAX_VALUE, showDivider ? 43 : 40));

        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);
        row.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel roleLabel = new JLabel(role);
        roleLabel.setFont(roleFont);
        roleLabel.setForeground(TEXT_MUTED);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameFont);
        nameLabel.setForeground(TEXT_PRIMARY);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        row.add(roleLabel, BorderLayout.WEST);
        row.add(nameLabel, BorderLayout.EAST);
        outer.add(row, BorderLayout.CENTER);

        if (showDivider) {
            JSeparator div = new JSeparator(SwingConstants.HORIZONTAL);
            div.setForeground(DIVIDER);
            outer.add(div, BorderLayout.SOUTH);
        }

        return outer;
    }


    private void loadFonts() {
        try {
            InputStream s = getClass().getResourceAsStream(
                "/GUI/resources/font/Mulish-VariableFont_wght.ttf");
            Font base = (s != null) ? Font.createFont(Font.TRUETYPE_FONT, s)
                                    : new Font("Georgia", Font.PLAIN, 12);
            java.util.Map<java.awt.font.TextAttribute, Object> bold = new java.util.HashMap<>();
            bold.put(java.awt.font.TextAttribute.WEIGHT, java.awt.font.TextAttribute.WEIGHT_BOLD);
            titleFont     = base.deriveFont(bold).deriveFont(22f);
            nameFont      = base.deriveFont(bold).deriveFont(13f);
            roleFont      = base.deriveFont(bold).deriveFont(13f);
        } catch (Exception ex) {
            titleFont     = new Font("Georgia", Font.BOLD,  22);
            nameFont      = new Font("Georgia", Font.BOLD,  13);
            roleFont      = new Font("Georgia", Font.BOLD, 13);
        }
    }
}