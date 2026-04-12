import controller.WizardController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WizardController app = new WizardController();
            app.startApplication();
        });
    }
}

