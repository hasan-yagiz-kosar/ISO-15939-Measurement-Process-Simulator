package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String name;
    private String mode;
    private String qualityType;
    private List<Dimension> dimensions;

    public Scenario(String name, String mode, String qualityType) {
        this.name = name;
        this.mode = mode;
        this.qualityType = qualityType;
        this.dimensions = new ArrayList<>();
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public String getName() { return name; }
    public String getMode() { return mode; }
    public String getQualityType() { return qualityType; }
    public List<Dimension> getDimensions() { return dimensions; }

    @Override
    public String toString() {
        return name;
    }
}