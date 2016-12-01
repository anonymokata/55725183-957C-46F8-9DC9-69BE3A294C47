import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class RomanNumeralConverter {

    private final List<NumeralValueTuple> sortedNumerals;
    private final NumeralValueTuple nullTuple;

    RomanNumeralConverter() {
        this.sortedNumerals = Arrays.asList(
                new NumeralValueTuple('M', 1000),
                new NumeralValueTuple('D', 500),
                new NumeralValueTuple('C', 100),
                new NumeralValueTuple('L', 50),
                new NumeralValueTuple('X', 10),
                new NumeralValueTuple('V', 5),
                new NumeralValueTuple('I', 1)
        );

        nullTuple = new NumeralValueTuple(' ', 0);
    }

    Optional<Integer> toInteger(final String numeral) {
        final List<Optional<NumeralValueTuple>> numeralAsOptionalTuples = numeral.chars()
                .mapToObj(this::mapCharacter)
                .collect(Collectors.toList());

        if (numeralAsOptionalTuples.stream().anyMatch(optional -> !optional.isPresent())) {
            return Optional.empty();
        }

        final List<NumeralValueTuple> numeralTuples = numeralAsOptionalTuples.stream()
                .map(this::unpackOptional)
                .collect(Collectors.toList());

        return Optional.of(computeValueOfNumerals(numeralTuples));
    }

    private int computeValueOfNumerals(final List<NumeralValueTuple> numeralTuples) {
        int value = 0;
        NumeralValueTuple previous = nullTuple;
        for (final NumeralValueTuple tuple : numeralTuples) {
            if (previous.getValue() < tuple.getValue()) {
                value -= previous.getValue();
                value += tuple.getValue() - previous.getValue();
            } else {
                value += tuple.getValue();
            }
            previous = tuple;
        }
        return value;
    }

    private NumeralValueTuple unpackOptional(final Optional<NumeralValueTuple> numeralValueTuple) {
        //noinspection OptionalGetWithoutIsPresent
        return numeralValueTuple.get();
    }

    private Optional<NumeralValueTuple> mapCharacter(final int character) {
        return getTuple((char) character, this.sortedNumerals);
    }

    private Optional<NumeralValueTuple> getTuple(final char numeral, final List<NumeralValueTuple> tupleList) {
        return tupleList.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst();
    }

    String toNumeral(final int value) {
        return reduceValueUntilRenderedAsNumeral(value);
    }

    private String reduceValueUntilRenderedAsNumeral(int remainingValue) {
        final StringBuilder builder = new StringBuilder();

        for (final NumeralValueTuple numeralTuple : this.sortedNumerals) {
            final int valueConsumed = applyNumeralToString(builder, remainingValue, numeralTuple);
            remainingValue -= valueConsumed;
        }
        return builder.toString();
    }

    private int applyNumeralToString(final StringBuilder builder,
                                     final int mutableValue,
                                     final NumeralValueTuple numeralTuple) {
        final int quotient = mutableValue / numeralTuple.getValue();

        if (quotient < 4) {
            repeatValue(builder, numeralTuple.getNumeral(), quotient);
        } else {
            useReducedNumeral(builder, numeralTuple);
        }

        return numeralTuple.getValue() * quotient;
    }

    private void useReducedNumeral(final StringBuilder builder, final NumeralValueTuple numeralTuple) {
        final NumeralValueTuple nextHigherValueNumeral = getNextHigherValueNumeral(numeralTuple);

        builder.append(numeralTuple.getNumeral());
        builder.append(nextHigherValueNumeral.getNumeral());
    }

    private NumeralValueTuple getNextHigherValueNumeral(final NumeralValueTuple numeralTuple) {
        final int numeralTupleIndex = this.sortedNumerals.indexOf(numeralTuple);
        return this.sortedNumerals.get(numeralTupleIndex - 1);
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
