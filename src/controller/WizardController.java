package controller;

import model.Scenario;
import model.UserProfile;
import view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class WizardController {
    private MainFrame view;
    private UserProfile currentUser;
    private Scenario currentScenario;
    private int currentStepIndex = 0;
    private final String[] steps = {"Profile", "Define", "Plan", "Collect", "Analyse"};

    public void startApplication() {
        view = new MainFrame(this);
        view.setVisible(true);
        view.showPanel(steps[0]);
        view.updateStepIndicator(0);
    }

    public void nextStep() {
        if (view.getCurrentPanel().validateInput()) {
            if (currentStepIndex == 1 && currentScenario == null) {
                JOptionPane.showMessageDialog(view, "Please select a valid scenario to continue.");
                return;
            }
            if (currentStepIndex < steps.length - 1) {
                currentStepIndex++;
                view.showPanel(steps[currentStepIndex]);
                view.updateStepIndicator(currentStepIndex);
                if(currentStepIndex == 4) {
                    calculateFinalResults();
                }
            }
        }
    }

    public void previousStep() {
        if (currentStepIndex > 0) {
            currentStepIndex--;
            view.showPanel(steps[currentStepIndex]);
            view.updateStepIndicator(currentStepIndex);
        }
    }

    public void saveProfileData(String username, String school, String sessionName) {
        currentUser = new UserProfile(username, school, sessionName);
    }

    public void selectScenario(Scenario scenario) {
        this.currentScenario = scenario;
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }

    public void calculateFinalResults() {
        view.updateAnalysePanel();
    }
}