# ISO/IEC 15939 Measurement Process Simulator

This project is a desktop application developed using Java Swing that simulates the 5 core steps of the ISO/IEC 15939 software measurement process standard. It was prepared as an individual assignment for the Software Project II course.

## 👨‍🎓 Student Information
* **Full Name:** Hasan Yağız Koşar
* **Student ID:** c2328040
* **Course:** Software Project II

## 🎯 Project Requirements & Features
The system is designed strictly adhering to the Model-View-Controller (MVC) architecture and Object-Oriented Programming (OOP) principles. The application features a 5-step wizard structure:

1. **Profile:** The entry screen where user (Username), school, and session information are collected with input validation.
2. **Define:** The screen to determine the scope of measurement, including Product/Process Quality, Mode, and Scenario selections.
3. **Plan:** A read-only table displaying the quality dimensions and metrics (coefficient, direction, range, unit) for the selected scenario.
4. **Collect:** The data entry screen for raw values. Scores between 1.0 and 5.0 are automatically calculated based on "Higher is better" or "Lower is better" formulas.
5. **Analyse:** The results screen displaying dimension-based weighted averages using progress bars (`JProgressBar`) and a Radar Chart (`Graphics2D`). The dimension with the lowest score is identified and presented as a "Gap Analysis".

**Technical Constraints:**
* Developed using only standard Java SE (17+) libraries. No external library dependencies are used.
* The graphical user interface (GUI) is built with Java Swing, primarily utilizing `CardLayout` for the wizard navigation.
* Data management and storage are handled via the Java Collections Framework (`ArrayList`, `HashMap`).

## 📐 System Design (UML Class Diagram)
The application strictly follows the MVC pattern. Below is the class diagram showing the relationships between the Model, View, and Controller layers:

```mermaid
classDiagram
    class UserProfile {
        -username: String
        -school: String
        -sessionName: String
        +UserProfile(username: String, school: String, sessionName: String)
        +isValid(): boolean
    }

    class Metric {
        -name: String
        -coefficient: int
        -direction: String
        -minRange: double
        -maxRange: double
        -unit: String
        -rawValue: double
        +Metric(...)
        +calculateScore(): double
    }

    class Dimension {
        -name: String
        -coefficient: int
        -metrics: List~Metric~
        +Dimension(name: String, coefficient: int)
        +addMetric(metric: Metric)
        +calculateWeightedAverage(): double
    }

    class Scenario {
        -name: String
        -mode: String
        -qualityType: String
        -dimensions: List~Dimension~
        +Scenario(name: String, mode: String, qualityType: String)
        +addDimension(dimension: Dimension)
    }

    class DataRepository {
        -database: HashMap~String, List~Scenario~~
        +getInstance()$ DataRepository
        +getScenariosByMode(mode: String): List~Scenario~
    }

    
    class WizardController {
        -view: MainFrame
        -currentUser: UserProfile
        -currentScenario: Scenario
        +startApplication()
        +nextStep()
        +previousStep()
        +saveProfileData(username: String, school: String, sessionName: String)
        +selectScenario(scenario: Scenario)
        +calculateFinalResults()
    }

    
    class MainFrame {
        -cardLayout: CardLayout
        -cardsPanel: JPanel
        -stepIndicator: StepIndicatorPanel
        +MainFrame()
        +showPanel(panelName: String)
        +updateStepIndicator(stepIndex: int)
    }

    class AbstractStepPanel {
        #controller: WizardController
        +AbstractStepPanel(controller: WizardController)
        +updateView()*
        +validateInput()*: boolean
    }

    class ProfilePanel {
        -txtUsername: JTextField
        -txtSchool: JTextField
        -txtSession: JTextField
        +validateInput(): boolean
    }

    class DefinePanel {
        -rbProduct: JRadioButton
        -rbProcess: JRadioButton
        -cmbMode: JComboBox
        -cmbScenario: JComboBox
        +updateScenarioList(scenarios: List~Scenario~)
    }

    class PlanPanel {
        -tblMetrics: JTable
        +updateView()
    }

    class CollectPanel {
        -tblDataValues: JTable
        +updateView()
    }

    class AnalysePanel {
        -progressBarsPanel: JPanel
        -radarChart: RadarChartPanel
        -lblGapAnalysis: JLabel
        +displayResults(dimensions: List~Dimension~)
    }

    class RadarChartPanel {
        -data: List~Dimension~
        +paintComponent(g: Graphics)
    }

    

    
    AbstractStepPanel <|-- ProfilePanel
    AbstractStepPanel <|-- DefinePanel
    AbstractStepPanel <|-- PlanPanel
    AbstractStepPanel <|-- CollectPanel
    AbstractStepPanel <|-- AnalysePanel

    
    Scenario "1" *-- "*" Dimension
    Dimension "1" *-- "*" Metric
    AnalysePanel "1" *-- "1" RadarChartPanel
    MainFrame "1" *-- "5" AbstractStepPanel

    
    DataRepository "1" --> "*" Scenario
    WizardController "1" --> "1" UserProfile
    WizardController "1" --> "1" Scenario
    WizardController "1" --> "1" MainFrame
    
    
    MainFrame "1" o-- "1" WizardController
    ```