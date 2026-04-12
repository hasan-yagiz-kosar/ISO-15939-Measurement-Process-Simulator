package view;

import controller.WizardController;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends AbstractStepPanel {
    private JTextField txtUsername;
    private JTextField txtSchool;
    private JTextField txtSession;

    public ProfilePanel(WizardController controller) {
        super(controller);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("School:"), gbc);
        gbc.gridx = 1;
        txtSchool = new JTextField(20);
        add(txtSchool, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Session Name:"), gbc);
        gbc.gridx = 1;
        txtSession = new JTextField(20);
        add(txtSession, gbc);
    }

    @Override
    public void updateView() {}

    @Override
    public boolean validateInput() {
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username to continue.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtSchool.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your school name to continue.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtSession.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a session name to continue.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        controller.saveProfileData(txtUsername.getText(), txtSchool.getText(), txtSession.getText());
        return true;
    }
}