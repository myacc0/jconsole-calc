public class Calculating implements Calc {
    public int calculating(String operator, int operandA, int operandB) {
        int res = switch (operator) {
            case "+" -> add(operandA, operandB);
            case "-" -> sub(operandA, operandB);
            case "*" -> mul(operandA, operandB);
            case "/" -> div(operandA, operandB);
            default -> 0;
        };

        return res;
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
