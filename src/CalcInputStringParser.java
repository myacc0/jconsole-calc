public interface CalcInputStringParser {
    void parseInputString(String input) throws IllegalMathExpression;

    NumberType detectNumeralSystem(String operandA, String operandB) throws IllegalMathExpression;
}
