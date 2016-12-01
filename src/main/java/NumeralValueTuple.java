import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class NumeralValueTuple {

    static final List<NumeralValueTuple> SORTED_NUMERALS = Arrays.asList(
            new NumeralValueTuple('M', 1000),
            new NumeralValueTuple('D', 500),
            new NumeralValueTuple('C', 100),
            new NumeralValueTuple('L', 50),
            new NumeralValueTuple('X', 10),
            new NumeralValueTuple('V', 5),
            new NumeralValueTuple('I', 1)
    );

    static Optional<NumeralValueTuple> getTuple(final char numeral) {
        return SORTED_NUMERALS.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst();
    }

    static final NumeralValueTuple NULL_TUPLE = new NumeralValueTuple(' ', 0);

    private final Character numeral;
    private final int value;

    private NumeralValueTuple(final Character numeral, final int value) {
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
