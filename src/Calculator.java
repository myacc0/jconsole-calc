import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private NumeralSystem inputNumeralSystem;
    private String operandA;
    private String operandB;
    private String operator;
    ConverterImpl converter = new ConverterImpl();
    ComputingImpl computing = new ComputingImpl();

    public void calculate(String input) throws IllegalMathExpression, ValueOutOfBoundException {
        // match by template and parse
        parseInputString(input);

        // detect input numeral system and set
        setInputNumeralSystem( detectNumeralSystem(operandA, operandB) );

        // convert
        int a = 0;
        int b = 0;
        int result = 0;
        if (inputNumeralSystem.equals(NumeralSystem.ROMAN)) {
            a = converter.romanToArabic(operandA);
            b = converter.romanToArabic(operandB);
        } else {
            try {
                a = Integer.parseInt(operandA);
                b = Integer.parseInt(operandB);
            } catch (NumberFormatException e) {
                throw new ValueOutOfBoundException();
            }
        }

        // validate operands
        if (validateValue(a) && validateValue(b)) {
            result = computing.doCalculation(operator, a, b);
        } else {
            throw new ValueOutOfBoundException();
        }

        // print result
        if (inputNumeralSystem.equals(NumeralSystem.ROMAN)) {
            System.out.println(converter.arabicToRoman(result));
        } else {
            System.out.println(result);
        }
    }

    public void parseInputString(String input) throws IllegalMathExpression {
        Pattern p = Pattern.compile("([IVX]+|[0-9]+)(\\s*[\\+,\\-,\\*,\\/]\\s*)([IVX]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        if (matcher.matches()) {
            operandA = matcher.group(1).toUpperCase();
            operator = matcher.group(2).trim();
            operandB = matcher.group(3).toUpperCase();
        } else {
            throw new IllegalMathExpression();
        }
    }

    public void setInputNumeralSystem(NumeralSystem inputNumeralSystem) {
        this.inputNumeralSystem = inputNumeralSystem;
    }

    public NumeralSystem detectNumeralSystem(String operandA, String operandB) throws IllegalMathExpression {
        Pattern arabicNSPattern = Pattern.compile("[0-9]+");
        Pattern romeNSPattern = Pattern.compile("[I,II,III,IV,V,VI,VII,VIII,IX,X]+", Pattern.CASE_INSENSITIVE);
        if (arabicNSPattern.matcher(operandA).matches() && arabicNSPattern.matcher(operandB).matches()) {
            return NumeralSystem.ARABIC;
        } else if (romeNSPattern.matcher(operandA).matches() && romeNSPattern.matcher(operandB).matches()) {
            return NumeralSystem.ROMAN;
        }

        throw new IllegalMathExpression();
    }

    public boolean validateValue(int value) {
        return value > 0 && value < 11;
    }
}
