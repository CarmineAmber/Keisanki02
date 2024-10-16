package org.example;

public class Calculator {
    public double add(double num1, double num2){
        return num1 + num2;
    }

    public double subtract(double num1, double num2){
        return num1 - num2;
    }

    public double multiply(double num1, double num2){
        return num1 * num2;
    }

    public double divide(double num1, double num2){
        if(num2 !=0) {
            return num1 / num2;
        } else {
            System.out.println("0では割れません。");
            return -1;
        }
    }
}
/*doubleというのは小数を扱える基本データ型（Stringなど）の一つで、
*floatよりも多くの小数点を扱える。*/
