package view;

import controller.WizardController;
import model.Dimension;
import model.Metric;
import model.Scenario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CollectPanel extends AbstractStepPanel {
    private JTable tblDataValues;
    private DefaultTableModel tableModel;
    private java.util.List<Metric> activeMetrics;

    public CollectPanel(WizardController controller) {
        super(controller);
        setLayout(new BorderLayout());

        String[] columns = {"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        tblDataValues = new JTable(tableModel);
        tblDataValues.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        tableModel.addTableModelListener(e -> {
            if (e.getColumn() == 3) {
                int row = e.getFirstRow();
                try {
                    double val = Double.parseDouble(tableModel.getValueAt(row, 3).toString());
                    Metric m = activeMetrics.get(row);
                    if(val >= m.getMinRange() && val <= m.getMaxRange()) {
                        m.setRawValue(val);
                        tableModel.setValueAt(m.calculateScore(), row, 4);
                    } else {
                        JOptionPane.showMessageDialog(this, "Value out of range!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number!");
                }
            }
        });

        add(new JScrollPane(tblDataValues), BorderLayout.CENTER);
    }

    @Override
    public void updateView() {
        tableModel.setRowCount(0);
        activeMetrics = new java.util.ArrayList<>();
        Scenario s = controller.getCurrentScenario();
        if (s != null) {
            for (Dimension d : s.getDimensions()) {
                for (Metric m : d.getMetrics()) {
                    activeMetrics.add(m);
                    tableModel.addRow(new Object[]{
                            m.getName(),
                            m.getDirection(),
                            m.getMinRange() + "-" + m.getMaxRange(),
                            m.getRawValue(),
                            m.calculateScore(),
                            m.getCoefficient() + "/" + m.getUnit()
                    });
                }
            }
        }
    }

    @Override
    public boolean validateInput() {
        if (tblDataValues.isEditing()) {
            tblDataValues.getCellEditor().stopCellEditing();
        }
        return true;
    }
}