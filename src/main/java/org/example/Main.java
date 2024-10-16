package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.println("１番目の数値を入力して下さい：");
            BigDecimal num1 = scanner.nextBigDecimal();

            System.out.println("２番目の数値を入力して下さい：");
            BigDecimal num2 = scanner.nextBigDecimal();

            System.out.println("演算子を入力して下さい（+,-.*,/,%,^）：");
            char operator = scanner.next().charAt(0);

            BigDecimal result;

            switch (operator) {
                case '+':
                    result = num1.add(num2);
                    break;
                case '-':
                    result = num1.subtract(num2);
                    break;
                case '*':
                    result = num1.multiply(num2);
                    break;
                case '/':
                    result = num1.divide(num2,4, RoundingMode.HALF_UP);
                    break;
                case '%':
                    result = num1.remainder(num2);
                    break;
                case '^':
                    result = num1.pow(num2.intValueExact());
                    break;
                default:
                    throw new IllegalArgumentException("演算子が無効です!");
            } System.out.println("計算結果：" + result);
        }catch(InputMismatchException e){
            System.out.println("数値を入力して下さい!");
        } catch (Exception e){
            System.out.println((e.getMessage()));
        }
    }
}