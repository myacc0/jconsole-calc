import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements CalcInputStringParser {
    private NumberType inputNumberType;
    private int calcOperandA;
    private int calcOperandB;
    private int calcResult;
    private String operandA;
    private String operandB;
    private String operator;
    ConverterImpl converter = new ConverterImpl();
    Calculating calc = new Calculating();
    ValidatorImpl validator = new ValidatorImpl();

    public void calculate(String input) throws IllegalMathExpression {
        parseInputString(input);
    }

    public void setInputNumberType(NumberType inputNumberType) {
        this.inputNumberType = inputNumberType;
    }

    @Override
    public void parseInputString(String input) throws IllegalMathExpression {
        Pattern p = Pattern.compile("([IVX0-9]{1,4})(\\s*[\\+,\\-,\\*,\\/]\\s*)([IVX0-9]{1,4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        boolean b = matcher.matches();
        if (b) {
            operandA = matcher.group(1).toUpperCase();
            operator = matcher.group(2).trim();
            operandB = matcher.group(3).toUpperCase();
            System.out.println("-----------------------------");
            System.out.println("operandA: " + operandA);
            System.out.println("operator: " + operator);
            System.out.println("operandB: " + operandB);

            // detect input numeral system
            setInputNumberType( detectNumeralSystem(operandA, operandB) );
            System.out.println("-----------------------------");
            System.out.println(inputNumberType);
            System.out.println("-----------------------------");
            System.out.println("Result:");

            // convert
            if (inputNumberType.equals(NumberType.ROMAN)) {
                calcOperandA = converter.romanToArabic(operandA);
                calcOperandB = converter.romanToArabic(operandB);

                // validate operands
                if (validator.checkNumberRange(calcOperandA) && validator.checkNumberRange(calcOperandB)) {
                    // do calculation
                    calcResult = calc.calculating(operator, calcOperandA, calcOperandB);
                    System.out.println(converter.arabicToRoman(calcResult));
                } else {
                    System.out.println("Ошибка! Допустимы числа в диапозоне [1, 10]");
                }

            } else {
                calcOperandA = Integer.parseInt(operandA);
                calcOperandB = Integer.parseInt(operandB);

                // validate operands
                if (validator.checkNumberRange(calcOperandA) && validator.checkNumberRange(calcOperandB)) {
                    // do calculation
                    calcResult = calc.calculating(operator, calcOperandA, calcOperandB);
                    System.out.println(converter.arabicToRoman(calcResult));
                } else {
                    System.out.println("Ошибка! Допустимы числа в диапозоне [1, 10]");
                }
            }
        } else {
            throw new IllegalMathExpression();
        }
    }

    @Override
    public NumberType detectNumeralSystem(String operandA, String operandB) throws IllegalMathExpression {
        Pattern arabicNSPattern = Pattern.compile("[0-9]+");
        Pattern romeNSPattern = Pattern.compile("[I,II,III,IV,V,VI,VII,VIII,IX,X]+", Pattern.CASE_INSENSITIVE);
        if (arabicNSPattern.matcher(operandA).matches() && arabicNSPattern.matcher(operandB).matches()) {
            return NumberType.ARABIC;
        } else if (romeNSPattern.matcher(operandA).matches() && romeNSPattern.matcher(operandB).matches()) {
            return NumberType.ROMAN;
        }

        throw new IllegalMathExpression();
    }
}
