package view;

import controller.WizardController;
import model.Dimension;
import model.Metric;
import model.Scenario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends AbstractStepPanel {
    private JTable tblMetrics;
    private DefaultTableModel tableModel;

    public PlanPanel(WizardController controller) {
        super(controller);
        setLayout(new BorderLayout());

        String[] columns = {"Dimension", "Metric", "Coeff", "Direction", "Range", "Unit"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblMetrics = new JTable(tableModel);
        add(new JScrollPane(tblMetrics), BorderLayout.CENTER);
    }

    @Override
    public void updateView() {
        tableModel.setRowCount(0);
        Scenario s = controller.getCurrentScenario();
        if (s != null) {
            for (Dimension d : s.getDimensions()) {
                String dimStr = d.getName() + " (" + d.getCoefficient() + ")";
                for (Metric m : d.getMetrics()) {
                    tableModel.addRow(new Object[]{
                            dimStr,
                            m.getName(),
                            m.getCoefficient(),
                            m.getDirection(),
                            m.getMinRange() + "-" + m.getMaxRange(),
                            m.getUnit()
                    });
                    dimStr = "";
                }
            }
        }
    }

    @Override
    public boolean validateInput() {
        return true;
    }
}