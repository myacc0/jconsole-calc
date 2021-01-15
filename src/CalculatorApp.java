import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorApp {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine().trim();
            calculator.calculate(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalMathExpression e) {
            System.out.println(e.getMessage());
        }
    }
}
