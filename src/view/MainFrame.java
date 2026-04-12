package view;

import controller.WizardController;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JPanel stepIndicatorPanel;
    private JLabel[] stepLabels;
    private HashMap<String, AbstractStepPanel> panels;
    private WizardController controller;
    private String currentPanelName;

    public MainFrame(WizardController controller) {
        this.controller = controller;
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setupStepIndicator();

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        panels = new HashMap<>();

        panels.put("Profile", new ProfilePanel(controller));
        panels.put("Define", new DefinePanel(controller));
        panels.put("Plan", new PlanPanel(controller));
        panels.put("Collect", new CollectPanel(controller));
        panels.put("Analyse", new AnalysePanel(controller));

        for (String key : panels.keySet()) {
            cardsPanel.add(panels.get(key), key);
        }

        add(stepIndicatorPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
        setupNavigation();
    }

    private void setupStepIndicator() {
        stepIndicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        String[] steps = {"Profile", "Define", "Plan", "Collect", "Analyse"};
        stepLabels = new JLabel[steps.length];

        for (int i = 0; i < steps.length; i++) {
            stepLabels[i] = new JLabel(steps[i]);
            stepLabels[i].setFont(new Font("Arial", Font.PLAIN, 14));
            stepIndicatorPanel.add(stepLabels[i]);
            if (i < steps.length - 1) {
                stepIndicatorPanel.add(new JLabel("➡"));
            }
        }
    }

    private void setupNavigation() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnPrev = new JButton("Back");
        JButton btnNext = new JButton("Next");

        btnPrev.addActionListener(e -> controller.previousStep());
        btnNext.addActionListener(e -> controller.nextStep());

        navPanel.add(btnPrev);
        navPanel.add(btnNext);
        add(navPanel, BorderLayout.SOUTH);
    }

    public void showPanel(String panelName) {
        this.currentPanelName = panelName;
        panels.get(panelName).updateView();
        cardLayout.show(cardsPanel, panelName);
    }

    public void updateStepIndicator(int stepIndex) {
        for (int i = 0; i < stepLabels.length; i++) {
            if (i < stepIndex) {
                stepLabels[i].setText("✓ " + stepLabels[i].getText().replace("✓ ", ""));
                stepLabels[i].setFont(new Font("Arial", Font.PLAIN, 14));
                stepLabels[i].setForeground(Color.GREEN.darker());
            } else if (i == stepIndex) {
                stepLabels[i].setText(stepLabels[i].getText().replace("✓ ", ""));
                stepLabels[i].setFont(new Font("Arial", Font.BOLD, 16));
                stepLabels[i].setForeground(Color.BLUE);
            } else {
                stepLabels[i].setText(stepLabels[i].getText().replace("✓ ", ""));
                stepLabels[i].setFont(new Font("Arial", Font.PLAIN, 14));
                stepLabels[i].setForeground(Color.GRAY);
            }
        }
    }

    public AbstractStepPanel getCurrentPanel() {
        return panels.get(currentPanelName);
    }

    public void updateAnalysePanel() {
        panels.get("Analyse").updateView();
    }
}