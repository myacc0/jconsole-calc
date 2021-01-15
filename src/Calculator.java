import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements Calc, CalcInputStringParser, Converter {
    private NumberType calcNumberType;
    private String operandA;
    private String operandB;
    private String operator;

    public void calculate(String input) throws IllegalMathExpression {
        parseInputString(input);
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

    @Override
    public void parseInputString(String input) throws IllegalMathExpression {
        Pattern p = Pattern.compile("([IVX0-9]+)(\\s*[\\+,\\-,\\*,\\/]\\s*)([IVX0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        boolean b = matcher.matches();
        if (b) {
            operandA = matcher.group(1).toUpperCase();
            operator = matcher.group(2).trim();
            operandB = matcher.group(3).toUpperCase();
        } else {
            throw new IllegalMathExpression();
        }
    }

    @Override
    public String arabicToRome(String value) {
        return null;
    }

    @Override
    public String romeToArabic(String value) {
        return null;
    }
}
