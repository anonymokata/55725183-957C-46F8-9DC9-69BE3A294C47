import java.util.HashMap;
import java.util.Map;

class RomanNumeralConverter {

    private final Map<Character, Integer> multipleOfTenMap;

    RomanNumeralConverter() {
        multipleOfTenMap = new HashMap<>();
        multipleOfTenMap.put('C', 100);
        multipleOfTenMap.put('X', 10);
        multipleOfTenMap.put('I', 1);
    }

    int toInteger(final String numeral) {
        return numeral.length() * multipleOfTenMap.get(numeral.charAt(0));
    }

    String toNumeral(final int value) {
        final StringBuilder builder = new StringBuilder();

        int mutableValue = value;

        for (final Map.Entry<Character, Integer> entry : multipleOfTenMap.entrySet()) {
            final Character numeral = entry.getKey();
            final Integer numeralValue = entry.getValue();

            final int quotient = mutableValue / numeralValue;
            repeatValue(builder, numeral, quotient);
            mutableValue -= numeralValue * quotient;
        }
        return builder.toString();
    }

    private void repeatValue(final StringBuilder builder, final Character numeral, final int quotient) {
        for (int i = 0; i < quotient; i++) {
            builder.append(numeral);
        }
    }
}
