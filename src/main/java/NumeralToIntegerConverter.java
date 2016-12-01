import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class NumeralToIntegerConverter {

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

        return Optional.of(this.computeValueOfNumerals(numeralTuples));
    }

    private int computeValueOfNumerals(final List<NumeralValueTuple> numeralTuples) {
        int value = 0;
        NumeralValueTuple previous = NumeralValueTuple.NULL_TUPLE;
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
        return NumeralValueTuple.getTuple((char) character);
    }

}
