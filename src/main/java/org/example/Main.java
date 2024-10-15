package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.println("最初の数値を入力して下さい：");
            double num1 = scanner.nextDouble();

            System.out.println("次の数値を入力して下さい：");
            double num2 = scanner.nextDouble();

            System.out.println("演算子を入力して下さい（+,-.*,/,%,^）：");
            char operator = scanner.next().charAt(0);

            double result;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '%':
                    result = num1 % num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
                default:
                    throw new IllegalArgumentException("無効な演算子です。");
            } System.out.println("結果：" + result);
        }catch(InputMismatchException e){
            System.out.println("無効な入力です。数値を入力して下さい");
        } catch (Exception e){
            System.out.println((e.getMessage()));
        }
    }
}