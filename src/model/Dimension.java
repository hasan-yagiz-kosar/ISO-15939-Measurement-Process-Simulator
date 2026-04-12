package model;

import java.util.ArrayList;
import java.util.List;

public class Dimension {
    private String name;
    private int coefficient;
    private List<Metric> metrics;

    public Dimension(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = new ArrayList<>();
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    public double calculateWeightedAverage() {
        double totalScore = 0;
        int totalWeight = 0;
        for (Metric m : metrics) {
            totalScore += m.calculateScore() * m.getCoefficient();
            totalWeight += m.getCoefficient();
        }
        return totalWeight == 0 ? 0 : totalScore / totalWeight;
    }

    public String getName() { return name; }
    public int getCoefficient() { return coefficient; }
    public List<Metric> getMetrics() { return metrics; }
}