package convertions;

import java.util.Optional;

class IntegerToNumeralConverter {

    private static final int MAX_NUMERAL_VALUE = 4000;
    private static final int MIN_NUMERAL_VALUE = 0;

    Optional<String> toNumeral(final int value) {
        if (value <= MIN_NUMERAL_VALUE || value >= MAX_NUMERAL_VALUE) {
            return Optional.empty();
        }

        return Optional.of(reduceValueUntilRenderedAsNumeral(value));
    }

    private String reduceValueUntilRenderedAsNumeral(int remainingValue) {
        final StringBuilder builder = new StringBuilder();

        for (final NumeralValueTuple numeralTuple : NumeralValueTuple.SORTED_NUMERALS) {
            final int valueConsumed = applyNumeralToString(builder, remainingValue, numeralTuple);
            remainingValue -= valueConsumed;
        }
        return builder.toString();
    }

    private int applyNumeralToString(final StringBuilder builder,
                                     final int mutableValue,
                                     final NumeralValueTuple numeralTuple) {
        final int quotient = mutableValue / numeralTuple.getValue();

        if (quotient <= numeralTuple.getType().getMaxNumberOfRepetitions()) {
            repeatValue(builder, numeralTuple.getNumeral(), quotient);
        } else {
            useReducedNumeral(builder, numeralTuple);
        }

        return numeralTuple.getValue() * quotient;
    }

    private void repeatValue(final StringBuilder builder, final Character numeral, final int quotient) {
        for (int i = 0; i < quotient; i++) {
            builder.append(numeral);
        }
    }

    private void useReducedNumeral(final StringBuilder builder, final NumeralValueTuple numeralTuple) {
        final NumeralValueTuple nextHigherValueNumeral = getNextHigherValueNumeral(numeralTuple);

        if (nextHigherValueNumeralWasPreviouslyUsed(builder, nextHigherValueNumeral)) {
            overwritePreviousCharacter(builder);

            final NumeralValueTuple nextNextHigherValueNumeral = getNextHigherValueNumeral(nextHigherValueNumeral);
            builder.append(numeralTuple.getNumeral());
            builder.append(nextNextHigherValueNumeral.getNumeral());
        } else {
            builder.append(numeralTuple.getNumeral());
            builder.append(nextHigherValueNumeral.getNumeral());
        }
    }

    private void overwritePreviousCharacter(final StringBuilder builder) {
        builder.deleteCharAt(builder.length() - 1);
    }

    private boolean nextHigherValueNumeralWasPreviouslyUsed(
            final StringBuilder builder, final NumeralValueTuple nextHigherValueNumeral
    ) {
        final String numeralSoFar = builder.toString();

        if (numeralSoFar.isEmpty()) {
            return false;
        }
        final char previousNumeralChar = numeralSoFar.charAt(numeralSoFar.length() - 1);

        return previousNumeralChar == nextHigherValueNumeral.getNumeral();
    }

    private NumeralValueTuple getNextHigherValueNumeral(final NumeralValueTuple numeralTuple) {
        final int numeralTupleIndex = NumeralValueTuple.SORTED_NUMERALS.indexOf(numeralTuple);
        return NumeralValueTuple.SORTED_NUMERALS.get(numeralTupleIndex - 1);
    }
}
