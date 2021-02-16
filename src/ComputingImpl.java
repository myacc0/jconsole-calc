public class ComputingImpl implements Computing {

    @Override
    public int doCalculation(String operator, int a, int b) {
        int res = switch (operator) {
            case "+" -> add(a, b);
            case "-" -> sub(a, b);
            case "*" -> mul(a, b);
            case "/" -> div(a, b);
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
