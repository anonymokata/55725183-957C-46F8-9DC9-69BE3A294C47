import java.util.Arrays;
import java.util.List;

class RomanNumeralConverter {

    private class NumeralValueTuple {

        private final Character numeral;
        private final int value;

        NumeralValueTuple(final Character numeral, final int value) {
            this.numeral = numeral;
            this.value = value;
        }

        int getValue() {
            return value;
        }

        Character getNumeral() {
            return numeral;
        }
    }

    private final List<NumeralValueTuple> multiplesOfTen;

    RomanNumeralConverter() {
        multiplesOfTen = Arrays.asList(
                new NumeralValueTuple('C', 100),
                new NumeralValueTuple('X', 10),
                new NumeralValueTuple('I', 1)
        );
    }

    int toInteger(final String numeral) {
        return numeral.length() * getValueFor(numeral.charAt(0));
    }

    private Integer getValueFor(final char numeral) {
        return multiplesOfTen.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst()
                .get()
                .getValue();
    }

    String toNumeral(final int value) {
        return reduceValueUntilRenderedAsNumeral(value);
    }

    private String reduceValueUntilRenderedAsNumeral(int remainingValue) {
        final StringBuilder builder = new StringBuilder();

        for (final NumeralValueTuple numeralTuple : multiplesOfTen) {
            final int valueConsumed = applyNumeralToString(builder, remainingValue, numeralTuple);
            remainingValue -= valueConsumed;
        }
        return builder.toString();
    }

    private int applyNumeralToString(final StringBuilder builder, final int mutableValue, final NumeralValueTuple numeralTuple) {
        final int quotient = mutableValue / numeralTuple.getValue();
        repeatValue(builder, numeralTuple.getNumeral(), quotient);
        return numeralTuple.getValue() * quotient;
    }

    private void repeatValue(final StringBuilder builder, final Character numeral, final int quotient) {
        for (int i = 0; i < quotient; i++) {
            builder.append(numeral);
        }
    }
}
