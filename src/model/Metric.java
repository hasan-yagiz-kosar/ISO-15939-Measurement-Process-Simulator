package model;

public class Metric {
    private String name;
    private int coefficient;
    private String direction;
    private double minRange;
    private double maxRange;
    private String unit;
    private double rawValue;

    public Metric(String name, int coefficient, String direction, double minRange, double maxRange, String unit) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.unit = unit;
        this.rawValue = minRange;
    }

    public Metric(String name, int coefficient, String direction, double minRange, double maxRange, String unit, double rawValue) {
        this(name, coefficient, direction, minRange, maxRange, unit);
        this.rawValue = rawValue;
    }

    public double calculateScore() {
        double score;
        if (direction.contains("Higher")) {
            score = 1 + ((rawValue - minRange) / (maxRange - minRange)) * 4;
        } else {
            score = 5 - ((rawValue - minRange) / (maxRange - minRange)) * 4;
        }
        score = Math.max(1.0, Math.min(5.0, score));
        return Math.round(score * 2.0) / 2.0;
    }

    public String getName() { return name; }
    public int getCoefficient() { return coefficient; }
    public String getDirection() { return direction; }

    public String getFormattedDirection() {
        return direction.equals("Higher") ? "Higher \u2191" : "Lower \u2193";
    }

    public double getMinRange() { return minRange; }
    public double getMaxRange() { return maxRange; }
    public String getUnit() { return unit; }
    public double getRawValue() { return rawValue; }
    public void setRawValue(double rawValue) { this.rawValue = rawValue; }
}