package org.example;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Swingのスレッドセーフな初期化
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}