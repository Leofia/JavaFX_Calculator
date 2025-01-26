/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx1;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author İrem Serra
 */
public class FXMLController {

    @FXML
    private Button button_eight;
    @FXML
    private Button button_nine;
    @FXML
    private Button button_four;
    @FXML
    private Button button_five;
    @FXML
    private Button button_six;
    @FXML
    private Button button_one;
    @FXML
    private Button button_two;
    @FXML
    private Button button_seven;
    @FXML
    private Button button_three;
    @FXML
    private Button button_zero;
    @FXML
    private Button button_dot;
    @FXML
    private Button button_equals;
    @FXML
    private Button button_sub;
    @FXML
    private Button button_multiply;
    @FXML
    private Button button_add;
    @FXML
    private Button button_divide;
    @FXML
    private TextField textField;
    private String currentInput = "";
    @FXML
    private Button button_ac;
    @FXML
    private Button button_delete;
    private String expression = "";
    private boolean flag = false;
    String str;
    char a;

    @FXML
    private void button_one(ActionEvent event) {
        appendNumber("1");
    }

    @FXML
    private void button_two(ActionEvent event) {
        appendNumber("2");
    }

    @FXML
    private void button_three(ActionEvent event) {
        appendNumber("3");
    }

    @FXML
    private void button_four(ActionEvent event) {
        appendNumber("4");
    }

    @FXML
    private void button_five(ActionEvent event) {
        appendNumber("5");
    }

    @FXML
    private void button_six(ActionEvent event) {
        appendNumber("6");
    }

    @FXML
    private void button_seven(ActionEvent event) {
        appendNumber("7");
    }

    @FXML
    private void button_eight(ActionEvent event) {
        appendNumber("8");
    }

    @FXML
    private void button_nine(ActionEvent event) {
        appendNumber("9");
    }

    @FXML
    private void button_zero(ActionEvent event) {
        appendNumber("0");
    }

    private void button_brackets1(ActionEvent event) {
        appendNumber("(");
    }

    private void button_brackets2(ActionEvent event) {
        appendNumber(")");
    }

    @FXML
    private void button_dot(ActionEvent event) {
        appendNumber(".");
    }

    @FXML
    private void button_add(ActionEvent event) throws Exception {
        appendOperator("+");
    }

    @FXML
    private void button_sub(ActionEvent event) throws Exception {
        appendOperator("-");
    }

    @FXML
    private void button_multiply(ActionEvent event) throws Exception {
        appendOperator("*");
    }

    @FXML
    private void button_divide(ActionEvent event) throws Exception {
        appendOperator("/");
    }

    @FXML
    private void button_equals() throws Exception {
        calculateResult();
    }

    @FXML
    private void button_delete(ActionEvent event) {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);

            textField.setText(currentInput);
        }
    }

    @FXML
    private void button_ac(ActionEvent event) {
        textField.clear();
        currentInput = "";
        expression = "";
    }

    private void appendNumber(String value) {
        if (flag) {
            currentInput = "";
            flag = false;
        }
        if (value.equals(".") && currentInput.contains(".")) {
            return;
        } else if (currentInput.length() > 1
                && currentInput.charAt(currentInput.length() - 1) != '.'
                && currentInput.charAt(currentInput.length() - 2) == '0'
                && (currentInput.length() < 3 || !Character.isDigit(currentInput.charAt(currentInput.length() - 3)))) {
            currentInput = currentInput.substring(0, currentInput.length() - 2) + value;

            textField.setText(currentInput);
        }
        currentInput += value;
        textField.setText(currentInput);
    }

    private void appendOperator(String operator) throws Exception {
        if (!currentInput.isEmpty()) {

            expression += currentInput;
            calculateIntermediateResult();
            expression = currentInput;
            expression += operator;
            currentInput = "";
            textField.setText(operator);
        } else if (operator.equals("-") && expression.isEmpty()) {
            currentInput = "-";
            textField.setText(currentInput);
        } else if (operator.equals("-") && !expression.isEmpty() && !isOperator(expression.charAt(expression.length() - 1))) {

            expression += operator;
            textField.setText(currentInput);
        } else if (!expression.isEmpty()) {

            if (isOperator(expression.charAt(expression.length() - 1))) {
                expression = expression.substring(0, expression.length() - 1) + operator;
            } else {
                expression += operator;
            }
            textField.setText(operator);
        }
    }

    private void calculateIntermediateResult() throws Exception {
        try {
            if (!expression.isEmpty()) {
                double result = evaluateExpression(expression);
                currentInput = String.valueOf(result);
            }
        } catch (NumberFormatException e) {
            textField.setText("Error");
            currentInput = "";
            expression = "";
        }
    }

    public void calculateResult() throws Exception {
        try {
            expression += currentInput;
            double result = evaluateExpression(expression);
            textField.setText(formatResult(result));
            currentInput = formatResult(result);
            expression = "";
            flag = true;
        } catch (NumberFormatException e) {
            textField.setText("Error");
            currentInput = "";
            expression = "";
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        expression = expression.replaceAll("\\s", "");

        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        StringBuilder numberBuffer = new StringBuilder();
        try {
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);

                if (Character.isDigit(currentChar) || currentChar == '.') {
                    numberBuffer.append(currentChar);
                } else if (isOperator(currentChar)) {

                    if (currentChar == '-' && (i == 0 || isOperator(expression.charAt(i - 1)))) {
                        numberBuffer.append(currentChar);
                    } else {

                        if (numberBuffer.length() > 0) {
                            numbers.add(Double.parseDouble(numberBuffer.toString()));
                            numberBuffer.setLength(0);
                        }

                        operators.add(currentChar);
                    }
                }
            }

            if (numberBuffer.length() > 0) {
                numbers.add(Double.parseDouble(numberBuffer.toString()));
            }

            for (int i = 0; i < operators.size(); i++) {
                char operator = operators.get(i);
                if (operator == '*' || operator == '/') {
                    double left = numbers.get(i);
                    double right = numbers.get(i + 1);
                    double result = 0;

                    if (operator == '*') {
                        result = left * right;
                    } else if (operator == '/') {
                        if (right == 0) {
                            button_ac(null);
                            textField.setText("Cannot divide by zero! Click AC to reset");
                            throw new ArithmeticException("Division error: Division by zero is not allowed!");
                        }
                        result = left / right;
                    }

                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }
            }

            double total = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                char operator = operators.get(i);
                double nextNumber = numbers.get(i + 1);

                if (operator == '+') {
                    total += nextNumber;
                } else if (operator == '-') {
                    total -= nextNumber;
                }
            }

            return total;

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format.");
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Arithmetic error.");
        } catch (Exception e) {
            throw new Exception("An error occurred while evaluating the expression.");
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf(result);
        }
    }
}
