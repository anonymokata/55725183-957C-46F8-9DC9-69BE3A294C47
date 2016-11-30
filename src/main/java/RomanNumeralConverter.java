import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class RomanNumeralConverter {

    private final List<NumeralValueTuple> sortedPowersOfTen;
    private final List<NumeralValueTuple> sortedHalfPowersOfTen;

    RomanNumeralConverter() {
        sortedPowersOfTen = Arrays.asList(
                new NumeralValueTuple('M', 1000),
                new NumeralValueTuple('C', 100),
                new NumeralValueTuple('X', 10),
                new NumeralValueTuple('I', 1)
        );

        sortedHalfPowersOfTen = Arrays.asList(
                new NumeralValueTuple('D', 500),
                new NumeralValueTuple('L', 50),
                new NumeralValueTuple('V', 5)
        );
    }

    Optional<Integer> toInteger(final String numeral) {
        final char firstCharacter = numeral.charAt(0);
        final Optional<Integer> powerOfTenResult = getPowerOfTenTupleFor(firstCharacter)
                .map(tuple -> tuple.getValue() * numeral.length());

        if (!powerOfTenResult.isPresent()) {
            return getHalfPowerOfTenTupleFor(firstCharacter)
                    .map(NumeralValueTuple::getValue);
        }

        return powerOfTenResult;
    }

    private Optional<NumeralValueTuple> getPowerOfTenTupleFor(final char numeral) {
        return sortedPowersOfTen.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst();
    }

    private Optional<NumeralValueTuple> getHalfPowerOfTenTupleFor(final char numeral) {
        return sortedHalfPowersOfTen.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst();
    }

    String toNumeral(final int value) {
        return reduceValueUntilRenderedAsNumeral(value);
    }

    private String reduceValueUntilRenderedAsNumeral(int remainingValue) {
        final StringBuilder builder = new StringBuilder();

        for (final NumeralValueTuple numeralTuple : sortedPowersOfTen) {
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

}
