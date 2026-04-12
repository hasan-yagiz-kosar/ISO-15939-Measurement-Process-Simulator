package view;

import controller.WizardController;
import model.DataRepository;
import model.Scenario;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefinePanel extends AbstractStepPanel {
    private JRadioButton rbProduct;
    private JRadioButton rbProcess;
    private JComboBox<String> cmbMode;
    private JComboBox<Scenario> cmbScenario;

    public DefinePanel(WizardController controller) {
        super(controller);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        rbProduct = new JRadioButton("Product Quality");
        rbProcess = new JRadioButton("Process Quality");
        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(rbProduct);
        qualityGroup.add(rbProcess);
        rbProduct.setSelected(true);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Quality Type:"), gbc);
        gbc.gridx = 1;
        JPanel rbPanel = new JPanel();
        rbPanel.add(rbProduct);
        rbPanel.add(rbProcess);
        add(rbPanel, gbc);

        String[] modes = {"Education", "Health", "Custom"};
        cmbMode = new JComboBox<>(modes);
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Mode:"), gbc);
        gbc.gridx = 1;
        add(cmbMode, gbc);

        cmbScenario = new JComboBox<>();
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Scenario:"), gbc);
        gbc.gridx = 1;
        add(cmbScenario, gbc);

        cmbMode.addActionListener(e -> updateScenarioList());
        updateScenarioList();
    }

    private void updateScenarioList() {
        cmbScenario.removeAllItems();
        String selectedMode = (String) cmbMode.getSelectedItem();
        List<Scenario> scenarios = DataRepository.getInstance().getScenariosByMode(selectedMode);
        for (Scenario s : scenarios) {
            cmbScenario.addItem(s);
        }
    }

    @Override
    public void updateView() {}

    @Override
    public boolean validateInput() {
        Scenario selected = (Scenario) cmbScenario.getSelectedItem();
        controller.selectScenario(selected);
        return selected != null;
    }
}