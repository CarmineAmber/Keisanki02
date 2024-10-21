package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleCalculator extends JFrame{
    private JTextField textField;
    private BigDecimal lastOperand;
    private String lastOperator;

    public SimpleCalculator() {
        // ウィンドウのタイトルとサイズを設定
        setTitle("電卓");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // テキストフィールドの作成
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField,BorderLayout.NORTH);

        // ボタンパネルの作成
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        /*setLayoutでレイアウトを指定し、部品を配置する。つまりここでは
        * パネルを5行4列で配置することを指示している。*/
        add(panel, BorderLayout.CENTER);
        /*BorderLayoutはコンポーネント（ここではパネル）を何処に
        * 配置するかを指定する。ここでは中央を絶対指定している。*/
        createButtons(panel);
        add(panel,BorderLayout.CENTER);
    }

    private void createButtons(JPanel panel){
        String[] buttonLabels = {"7", "8", "9", "/",
                                "4", "5", "6", "*",
                                "1", "2", "3", "-",
                                "0", ".", "=", "+",
                                "√", "C"};
        for(String label : buttonLabels){
            JButton button = new JButton(label);
            button.addActionListener(e->handleButtonClick(label));
            panel.add(button);
        }
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "=":
                calculateResult();
                break;
            case ".":
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + ".");
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(label);
                break;
            case "√":
                calculateSquareRoot();
                break;
            case "C":
                clear();
                break;
            default:
                textField.setText(textField.getText() + label);
                break;
        }
    }

    private void handleOperator(String operator) {
        if (!textField.getText().isEmpty()) {
            lastOperand = new BigDecimal(textField.getText());
            lastOperator = operator;
            textField.setText("");
        }
    }


    // 計算を実行するメソッド
    private void calculateResult() {
        if (lastOperand == null || lastOperator == null) return;

        BigDecimal currentResult = lastOperand;
        BigDecimal newOperand = new BigDecimal(textField.getText());

        switch (lastOperator) {
            case "+":
                currentResult = currentResult.add(newOperand);
                break;
            case "-":
                currentResult = currentResult.subtract(newOperand);
                break;
            case "*":
                currentResult = currentResult.multiply(newOperand);
                break;
            case "/":
                if (newOperand.compareTo(BigDecimal.ZERO) == 0) {
                    textField.setText("0で割ることはできません。");
                    return;
                }
                currentResult = currentResult.divide(newOperand, 15, RoundingMode.HALF_UP);
                break;
        }

        textField.setText(currentResult.stripTrailingZeros().toPlainString());
        lastOperand = null;
        lastOperator = null;
    }

    private void calculateSquareRoot() {
        try {
            BigDecimal input = new BigDecimal(textField.getText());
            BigDecimal result = sqrt(input, 12);
            textField.setText(result.stripTrailingZeros().toPlainString());
        } catch (NumberFormatException ex) {
            textField.setText("エラー");
        }
    }

    // ニュートン法による平方根の計算
    private BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal x0 = new BigDecimal(0);
        BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));
        BigDecimal two = new BigDecimal(2);
        BigDecimal tolerance = new BigDecimal("1e-" + scale);

        while (x1.subtract(x0).abs().compareTo(tolerance) > 0) {
            x0 = x1;
            x1 = value.divide(x0, scale, RoundingMode.HALF_UP);
            x1 = x1.add(x0).divide(two, scale, RoundingMode.HALF_UP);
        }

        return x1;
    }

    // クリア機能
    private void clear() {
        textField.setText("");
        lastOperand = null;
        lastOperator = null;
    }
}
