package view;

import controller.WizardController;
import model.Dimension;
import model.Scenario;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalysePanel extends AbstractStepPanel {
    private JPanel progressBarsPanel;
    private RadarChartPanel radarChart;
    private JLabel lblGapAnalysis;

    public AnalysePanel(WizardController controller) {
        super(controller);
        setLayout(new BorderLayout());

        progressBarsPanel = new JPanel();
        progressBarsPanel.setLayout(new BoxLayout(progressBarsPanel, BoxLayout.Y_AXIS));

        radarChart = new RadarChartPanel();
        radarChart.setPreferredSize(new java.awt.Dimension(300, 300));

        lblGapAnalysis = new JLabel();
        lblGapAnalysis.setFont(new Font("Arial", Font.BOLD, 14));
        lblGapAnalysis.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(new JScrollPane(progressBarsPanel));
        topPanel.add(radarChart);

        add(topPanel, BorderLayout.CENTER);
        add(lblGapAnalysis, BorderLayout.SOUTH);
    }

    @Override
    public void updateView() {
        Scenario s = controller.getCurrentScenario();
        if (s != null) {
            displayResults(s.getDimensions());
        }
    }

    public void displayResults(List<Dimension> dimensions) {
        progressBarsPanel.removeAll();
        radarChart.setData(dimensions);

        Dimension lowestDim = null;
        double lowestScore = Double.MAX_VALUE;

        for (Dimension d : dimensions) {
            double score = d.calculateWeightedAverage();
            if (score < lowestScore) {
                lowestScore = score;
                lowestDim = d;
            }

            JPanel dimPanel = new JPanel(new BorderLayout());
            dimPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            dimPanel.add(new JLabel(d.getName() + " - Score: " + String.format("%.2f", score)), BorderLayout.NORTH);

            JProgressBar pb = new JProgressBar(0, 500);
            pb.setValue((int)(score * 100));
            pb.setStringPainted(true);
            pb.setString(String.format("%.2f / 5.0", score));
            if (score >= 4.0) pb.setForeground(Color.GREEN);
            else if (score >= 2.5) pb.setForeground(Color.ORANGE);
            else pb.setForeground(Color.RED);

            dimPanel.add(pb, BorderLayout.CENTER);
            progressBarsPanel.add(dimPanel);
        }

        if (lowestDim != null) {
            double gap = 5.0 - lowestScore;

            String level;
            if (lowestScore >= 4.5) level = "Excellent";
            else if (lowestScore >= 3.5) level = "Good";
            else if (lowestScore >= 2.0) level = "Needs Improvement";
            else level = "Poor";

            String adviceText;
            if (lowestScore >= 4.0) {
                adviceText = "<i>All dimensions are performing excellently. Keep up the great work!</i>";
            } else if (lowestScore >= 3.0) {
                adviceText = "<i>This dimension has the low score but is performing good overall.</i>";
            } else if (lowestScore >= 2.0) {
                adviceText = "<i>This dimension has the very low score and needs improvement.</i>";
            } else {
                adviceText = "<i>This dimension has the poor score and requires the most improvement.</i>";
            }

            lblGapAnalysis.setText("<html>Gap Analysis: <br>Dimension: <b>" + lowestDim.getName() +
                    "</b><br>Score: " + String.format("%.2f", lowestScore) +
                    "<br>Gap Value: " + String.format("%.2f", gap) +
                    "<br>Quality Level: " + level +
                    "<br>" + adviceText + "</html>");
        }

        progressBarsPanel.revalidate();
        progressBarsPanel.repaint();
    }

    @Override
    public boolean validateInput() {
        return true;
    }
}