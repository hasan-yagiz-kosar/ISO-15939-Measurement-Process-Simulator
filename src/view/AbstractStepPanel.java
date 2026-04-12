package view;

import controller.WizardController;
import javax.swing.JPanel;

public abstract class AbstractStepPanel extends JPanel {
    protected WizardController controller;

    public AbstractStepPanel(WizardController controller) {
        this.controller = controller;
    }

    public abstract void updateView();
    public abstract boolean validateInput();
}