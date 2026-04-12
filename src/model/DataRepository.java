package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataRepository {
    private static DataRepository instance;
    private HashMap<String, List<Scenario>> database;

    private DataRepository() {
        database = new HashMap<>();
        loadMockData();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public List<Scenario> getScenariosByMode(String mode) {
        return database.getOrDefault(mode, new ArrayList<>());
    }

    private void loadMockData() {

        List<Scenario> eduScenarios = new ArrayList<>();

        Scenario s1 = new Scenario("Scenario C - Team Alpha", "Education", "Product Quality");
        Dimension d1 = new Dimension("Usability", 25);
        d1.addMetric(new Metric("SUS score", 50, "Higher", 0, 100, "points"));
        d1.addMetric(new Metric("Onboarding time", 50, "Lower", 0, 60, "min"));
        Dimension d2 = new Dimension("Perf. Efficiency", 20);
        d2.addMetric(new Metric("Video start time", 50, "Lower", 0, 15, "sec"));
        Dimension d3 = new Dimension("Accessibility", 20);
        d3.addMetric(new Metric("WCAG compliance", 50, "Higher", 0, 100, "%"));
        s1.addDimension(d1); s1.addDimension(d2); s1.addDimension(d3);

        Scenario s2 = new Scenario("Scenario D - Team Beta", "Education", "Process Quality");
        Dimension d6 = new Dimension("Process Efficiency", 40);
        d6.addMetric(new Metric("Sprint velocity", 100, "Higher", 0, 50, "points"));
        Dimension d6_2 = new Dimension("Code Quality", 30);
        d6_2.addMetric(new Metric("Bug count", 100, "Lower", 0, 100, "bugs"));
        Dimension d6_3 = new Dimension("Team Collaboration", 30);
        d6_3.addMetric(new Metric("Peer reviews", 100, "Higher", 0, 20, "reviews"));
        s2.addDimension(d6); s2.addDimension(d6_2); s2.addDimension(d6_3);

        eduScenarios.add(s1);
        eduScenarios.add(s2);
        database.put("Education", eduScenarios);


        List<Scenario> healthScenarios = new ArrayList<>();
        Scenario s3 = new Scenario("Scenario A - Core Health", "Health", "Product Quality");
        Dimension d7 = new Dimension("Security", 40);
        d7.addMetric(new Metric("Data Encryption", 100, "Higher", 0, 100, "%"));
        Dimension d7_2 = new Dimension("Compliance", 30);
        d7_2.addMetric(new Metric("HIPAA violations", 100, "Lower", 0, 10, "issues"));
        Dimension d7_3 = new Dimension("Reliability", 30);
        d7_3.addMetric(new Metric("System Uptime", 100, "Higher", 90, 100, "%"));
        s3.addDimension(d7); s3.addDimension(d7_2); s3.addDimension(d7_3);

        Scenario s4 = new Scenario("Scenario B - Telehealth", "Health", "Product Quality");
        Dimension d8 = new Dimension("Latency", 40);
        d8.addMetric(new Metric("Ping", 100, "Lower", 0, 200, "ms"));
        Dimension d8_2 = new Dimension("Video Quality", 30);
        d8_2.addMetric(new Metric("Resolution Drop", 100, "Lower", 0, 50, "%"));
        Dimension d8_3 = new Dimension("Audio Clarity", 30);
        d8_3.addMetric(new Metric("Noise Level", 100, "Lower", 0, 100, "dB"));
        s4.addDimension(d8); s4.addDimension(d8_2); s4.addDimension(d8_3);

        healthScenarios.add(s3);
        healthScenarios.add(s4);
        database.put("Health", healthScenarios);


        List<Scenario> customScenarios = new ArrayList<>();
        Scenario s5 = new Scenario("My Custom Scenario", "Custom", "Product Quality");
        Dimension d9 = new Dimension("Custom Dim 1", 30);
        d9.addMetric(new Metric("Custom Metric A", 100, "Higher", 0, 100, "points"));
        Dimension d10 = new Dimension("Custom Dim 2", 30);
        d10.addMetric(new Metric("Custom Metric B", 100, "Higher", 0, 100, "points"));
        Dimension d11 = new Dimension("Custom Dim 3", 40);
        d11.addMetric(new Metric("Custom Metric C", 100, "Lower", 0, 50, "errors"));
        s5.addDimension(d9); s5.addDimension(d10); s5.addDimension(d11);

        customScenarios.add(s5);
        database.put("Custom", customScenarios);
    }
}