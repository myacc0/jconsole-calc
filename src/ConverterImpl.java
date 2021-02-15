import java.util.List;

public class ConverterImpl implements Converter {

    @Override
    public String arabicToRoman(int value) {
        if ((value <= 0) || (value > 4000)) {
            throw new IllegalArgumentException(value + " не входит в диапозон (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((value > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= value) {
                sb.append(currentSymbol.name());
                value -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    @Override
    public int romanToArabic(String value) {
        String romanNumeral = value.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(value + " - невозможно конвертировать в Римскиу цифру");
        }

        return result;
    }
}
