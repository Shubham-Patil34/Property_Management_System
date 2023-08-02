package com.mycompany.propertymanagement.controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController cc = new CalculatorController();
        Double result = cc.add(4.4, 564.3);
        System.out.println(result);
    }
}
