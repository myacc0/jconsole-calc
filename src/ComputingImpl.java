public class ComputingImpl implements Computing {

    @Override
    public int doCalculation(String operator, int a, int b) {
        int res = 0;
        switch (operator) {
            case "+": res = add(a, b);
                break;
            case "-": res = sub(a, b);
                break;
            case "*": res = mul(a, b);
                break;
            case "/": res = div(a, b);
                break;
        }
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
