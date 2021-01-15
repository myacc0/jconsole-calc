public class IllegalMathExpression extends Exception {
    @Override
    public String getMessage() {
        return "Некорректное арифметическое выражение!";
    }
}
