package vn.techzen.BaseAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @GetMapping("/calculate")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> calculate(
            @RequestParam String firstNumber,
            @RequestParam String secondNumber,
            @RequestParam String operator) {

        // Kiểm tra đầu vào
        String error = validateInputs(firstNumber, secondNumber, operator);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        try {
            double num1 = Double.parseDouble(firstNumber);
            double num2 = Double.parseDouble(secondNumber);

            double result;
            if (operator.equals("+")) {
                result = num1 + num2;
            } else if (operator.equals("-")) {
                result = num1 - num2;
            } else if (operator.equals("*")) {
                result = num1 * num2;
            } else if (operator.equals("/")) {
                if (num2 == 0) {
                    return ResponseEntity.badRequest().body("Division by zero is not allowed");
                }
                result = num1 / num2;
            } else {
                return ResponseEntity.badRequest().body("Invalid operator. Use +, -, *, /");
            }

            return ResponseEntity.ok("Result: " + result);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Both numbers must be numeric");
        }
    }

    private String validateInputs(String firstNumber, String secondNumber, String operator) {
        if (firstNumber == null || firstNumber.isEmpty()) {
            return "First Number cannot be empty";
        }
        if (secondNumber == null || secondNumber.isEmpty()) {
            return "Second Number cannot be empty";
        }
        if (!isNumeric(firstNumber)) {
            return "First Number must be numeric";
        }
        if (!isNumeric(secondNumber)) {
            return "Second Number must be numeric";
        }
        if (operator == null || !"+-*/".contains(operator)) {
            return "Invalid operator. Supported operators are +, -, *, /";
        }
        return null;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
