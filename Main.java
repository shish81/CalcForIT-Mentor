package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input:");
            String input = scanner.nextLine();
            try {
                int result = calcStart(input);
                System.out.println("Output: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.close();
                return;
            }
        }

    }

    public static int calcStart(String input) {
        input = input.trim();

        String[] arrayForSplit = SplitStringUtils.splitInput(input);
        int operatorCount = countOperators(input);
        if (operatorCount > 1) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию" +
                    " - два операнда и один оператор (+, -, /, *)");
        }
        validateStringConsole(arrayForSplit);
        int operand1 = SplitStringUtils.getOperand(arrayForSplit[0]);
        String operator = SplitStringUtils.getOperator(arrayForSplit[1]);
        int operand2 = SplitStringUtils.getOperand(arrayForSplit[2]);
        validateOperands(operand1, operand2);
        return Calculation.countCalc(operand1, operator, operand2);
    }

    public static void validateStringConsole(String[] stringFromConsole) {
        if (stringFromConsole.length != 3) {
            throw new IllegalArgumentException("Строка не является математической операцией");
        }
    }

    public static void validateOperands(int operand1, int operand2) {
        if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }
    }

    public static int countOperators(String expression) {
        int count = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                count++;
            }
        }
        return count;
    }
}

class SplitStringUtils {
    public static String[] splitInput(String input) {
        return input.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    }

    public static String getOperator(String symbol) {
        return symbol;
    }

    public static int getOperand(String token) {
        return Integer.parseInt(token);
    }
}

class Calculation {
    public static int countCalc(int operand1, String operator, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Строка не является математической операцией - Неверный оператор");
        }
    }
}
