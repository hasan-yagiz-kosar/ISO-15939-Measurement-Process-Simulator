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
        d1.addMetric(new Metric("SUS score",             50, "Higher", 0,   100, "points", 89));
        d1.addMetric(new Metric("Onboarding time",       50, "Lower",  0,   60,  "min",    5));

        Dimension d2 = new Dimension("Perf. Efficiency", 20);
        d2.addMetric(new Metric("Video start time",      50, "Lower",  0,   15,  "sec",    3));
        d2.addMetric(new Metric("Concurrent exams",      50, "Higher", 0,   600, "users",  480));

        Dimension d3 = new Dimension("Accessibility", 20);
        d3.addMetric(new Metric("WCAG compliance",       50, "Higher", 0,   100, "%",      85));
        d3.addMetric(new Metric("Screen reader score",   50, "Higher", 0,   100, "%",      78));

        Dimension d4 = new Dimension("Reliability", 20);
        d4.addMetric(new Metric("Uptime",                50, "Higher", 95,  100, "%",      99.5));
        d4.addMetric(new Metric("MTTR",                  50, "Lower",  0,   120, "min",    15));

        Dimension d5 = new Dimension("Func. Suitability", 15);
        d5.addMetric(new Metric("Feature completion",    50, "Higher", 0,   100, "%",      92));
        d5.addMetric(new Metric("Assignment submit rate",50, "Higher", 0,   100, "%",      88));

        s1.addDimension(d1);
        s1.addDimension(d2);
        s1.addDimension(d3);
        s1.addDimension(d4);
        s1.addDimension(d5);

        Scenario s2 = new Scenario("Scenario D - Team Beta", "Education", "Process Quality");

        Dimension d6 = new Dimension("Process Efficiency", 40);
        d6.addMetric(new Metric("Sprint velocity",       50, "Higher", 0,   50,  "points", 13));
        d6.addMetric(new Metric("Cycle time",            50, "Lower",  0,   14,  "days",   4));

        Dimension d6_2 = new Dimension("Code Quality", 30);
        d6_2.addMetric(new Metric("Bug count",           50, "Lower",  0,   100, "bugs",   73));
        d6_2.addMetric(new Metric("Code coverage",       50, "Higher", 0,   100, "%",      74));

        Dimension d6_3 = new Dimension("Team Collaboration", 30);
        d6_3.addMetric(new Metric("Peer reviews",        50, "Higher", 0,   20,  "reviews",15));
        d6_3.addMetric(new Metric("Stand-up rate",       50, "Higher", 0,   100, "%",      10));

        s2.addDimension(d6);
        s2.addDimension(d6_2);
        s2.addDimension(d6_3);

        eduScenarios.add(s1);
        eduScenarios.add(s2);
        database.put("Education", eduScenarios);

        List<Scenario> healthScenarios = new ArrayList<>();

        Scenario s3 = new Scenario("Scenario A - Core Health", "Health", "Product Quality");

        Dimension d7 = new Dimension("Security", 40);
        d7.addMetric(new Metric("Data Encryption",       50, "Higher", 0,   100, "%",      95));
        d7.addMetric(new Metric("Auth failures",         50, "Lower",  0,   50,  "events", 3));

        Dimension d7_2 = new Dimension("Compliance", 30);
        d7_2.addMetric(new Metric("HIPAA violations",    50, "Lower",  0,   10,  "issues", 1));
        d7_2.addMetric(new Metric("Audit pass rate",     50, "Higher", 0,   100, "%",      88));

        Dimension d7_3 = new Dimension("Reliability", 30);
        d7_3.addMetric(new Metric("System Uptime",       50, "Higher", 90,  100, "%",      99.2));
        d7_3.addMetric(new Metric("MTTR",                50, "Lower",  0,   60,  "min",    10));

        s3.addDimension(d7);
        s3.addDimension(d7_2);
        s3.addDimension(d7_3);

        Scenario s4 = new Scenario("Scenario B - Telehealth", "Health", "Product Quality");

        Dimension d8 = new Dimension("Latency", 40);
        d8.addMetric(new Metric("Ping",                  50, "Lower",  0,   200, "ms",     193));
        d8.addMetric(new Metric("Jitter",                50, "Lower",  0,   50,  "ms",     39));

        Dimension d8_2 = new Dimension("Video Quality", 30);
        d8_2.addMetric(new Metric("Resolution Drop",     50, "Lower",  0,   50,  "%",      5));
        d8_2.addMetric(new Metric("Frame rate",          50, "Higher", 15,  60,  "fps",    30));

        Dimension d8_3 = new Dimension("Audio Clarity", 30);
        d8_3.addMetric(new Metric("Noise Level",         50, "Lower",  0,   100, "dB",     20));
        d8_3.addMetric(new Metric("MOS score",           50, "Higher", 1,   5,   "points", 4.2));

        s4.addDimension(d8);
        s4.addDimension(d8_2);
        s4.addDimension(d8_3);

        healthScenarios.add(s3);
        healthScenarios.add(s4);
        database.put("Health", healthScenarios);

        List<Scenario> customScenarios = new ArrayList<>();

        Scenario s5 = new Scenario("My Custom Scenario", "Custom", "Product Quality");

        Dimension d9 = new Dimension("Custom Dim 1", 30);
        d9.addMetric(new Metric("Custom Metric A",       50, "Higher", 0,   100, "points", 75));
        d9.addMetric(new Metric("Custom Metric B",       50, "Higher", 0,   100, "points", 60));

        Dimension d10 = new Dimension("Custom Dim 2", 30);
        d10.addMetric(new Metric("Custom Metric C",      50, "Lower",  0,   50,  "errors", 10));
        d10.addMetric(new Metric("Custom Metric D",      50, "Higher", 0,   100, "points", 80));

        Dimension d11 = new Dimension("Custom Dim 3", 40);
        d11.addMetric(new Metric("Custom Metric E",      50, "Higher", 0,   100, "%",      55));
        d11.addMetric(new Metric("Custom Metric F",      50, "Lower",  0,   20,  "issues", 4));

        s5.addDimension(d9);
        s5.addDimension(d10);
        s5.addDimension(d11);

        customScenarios.add(s5);
        database.put("Custom", customScenarios);
    }
}