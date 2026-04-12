package view;

import model.Dimension;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RadarChartPanel extends JPanel {
    private List<Dimension> data;

    public void setData(List<Dimension> data) {
        this.data = data;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.size() < 3) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 30;
        int n = data.size();

        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 1; i <= 5; i++) {
            int r = radius * i / 5;
            drawPolygon(g2, centerX, centerY, r, n);
        }

        g2.setColor(Color.GRAY);
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            g2.drawLine(centerX, centerY, x, y);

            g2.setColor(Color.BLACK);
            g2.drawString(data.get(i).getName(), x - 20, y > centerY ? y + 15 : y - 5);
            g2.setColor(Color.GRAY);
        }

        Polygon scorePoly = new Polygon();
        for (int i = 0; i < n; i++) {
            double score = data.get(i).calculateWeightedAverage();
            double r = radius * (score / 5.0);
            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            int x = (int) (centerX + r * Math.cos(angle));
            int y = (int) (centerY + r * Math.sin(angle));
            scorePoly.addPoint(x, y);
        }

        g2.setColor(new Color(0, 0, 255, 100));
        g2.fillPolygon(scorePoly);
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(scorePoly);
    }

    private void drawPolygon(Graphics2D g2, int cx, int cy, int r, int n) {
        Polygon p = new Polygon();
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            p.addPoint((int) (cx + r * Math.cos(angle)), (int) (cy + r * Math.sin(angle)));
        }
        g2.drawPolygon(p);
    }
}