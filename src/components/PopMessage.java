package components;

import javax.swing.*;

public class PopMessage {

    public static void showError(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void  showSuccess(JFrame frame, String username) {
        JOptionPane.showMessageDialog(frame,
                "Welcome, " + username + "!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarning() {

    }

}
