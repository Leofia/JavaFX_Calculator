# JavaFX Calculator

This is a simple calculator application built using JavaFX. It allows users to perform basic arithmetic operations such as addition, subtraction, multiplication, and division.

## Features

- **Basic Arithmetic:** Supports addition, subtraction, multiplication, and division.
- **Decimal Numbers:** Handles decimal numbers correctly.
- **Error Handling:** Includes basic error handling (e.g., division by zero).
- **Clear and Delete:** Provides "AC" (All Clear) and "C" (Delete) buttons.
- **Real-time display:**  Displays current input in a text field.
- **Intermediate calculation:** Calculates the intermediate result when a new operator is appended.
- **Proper result formatting:** Removes the decimal point if it's an integer.


  **Prerequisites:**
    -   Java Development Kit (JDK) 11 or higher.
    -   A Java IDE like IntelliJ IDEA, Eclipse, or NetBeans (optional, but recommended).


## Project Structure
-   `src/fx1/FXMLController.java`: The controller class that handles all user interactions and calculation logic.
-   `src/fx1/calculatorFXML.fxml`: The FXML file which describes the GUI.
-  `src/proje/proje.java`: The Main class for Javafx application
- `resources`: folder where `calculatorFXML.fxml` located

## Code Explanation
- **FXMLController.java:**
    - `TextField textField`: Displays the input and results.
    - Buttons (`button_one`, `button_two`, etc.): Each button has an `onAction` method to handle clicks.
        - `appendNumber(String value)`: appends the numeric input
        - `appendOperator(String operator)`: appends the operator
        - `calculateIntermediateResult()`: calculates the intermediate result
        - `calculateResult()`: Calculates the result of the expression
        - `evaluateExpression(String expression)`: Evaluates the expression
        - `isOperator(char c)`: Checks if the character is an operator
        - `formatResult(double result)`: Formats the result
- **calculatorFXML.fxml:**
    - Defines the structure of the calculator's GUI using JavaFX elements (GridPane, Buttons, TextField).

## Functionality

-   **Number Input:** Click the number buttons to append numbers to the display.
-   **Decimal Input:** Click the "." button to input decimal numbers.
-   **Operator Input:** Click the "+", "-", "*", or "/" buttons to append operators.
-   **Calculate Result:** Click the "=" button to evaluate the expression and display the result.
-   **Clear Input:** Click the "AC" button to clear the input field and reset the calculator.
-   **Delete Last Character:** Click the "C" button to delete the last character of the input field.
  
## Screenshot

Here's a screenshot of the calculator application:

![Screenshot 2025-01-26 231131](https://github.com/user-attachments/assets/e29c4e4e-14e5-4bd4-a69a-16aecb40ce11)

## Notes

-   This is a basic calculator and does not include advanced features like parentheses or trigonometric functions.
-   Error handling is implemented to catch invalid number formats or division by zero.

## Future Improvements

-   Add support for parentheses.
-   Implement advanced math functions.
-   Improve the GUI with a more user-friendly design.
-   Add keyboard support.
-   More extensive error messages.
-   Unit tests to increase the code reliability.

