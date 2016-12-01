package convertions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static convertions.NumeralType.HALF_POWER_OF_TEN;
import static convertions.NumeralType.POWER_OF_TEN;

class NumeralValueTuple {

    static final List<NumeralValueTuple> SORTED_NUMERALS = Arrays.asList(
            new NumeralValueTuple('M', 1000, POWER_OF_TEN),
            new NumeralValueTuple('D', 500, HALF_POWER_OF_TEN),
            new NumeralValueTuple('C', 100, POWER_OF_TEN),
            new NumeralValueTuple('L', 50, HALF_POWER_OF_TEN),
            new NumeralValueTuple('X', 10, POWER_OF_TEN),
            new NumeralValueTuple('V', 5, HALF_POWER_OF_TEN),
            new NumeralValueTuple('I', 1, POWER_OF_TEN)
    );

    static Optional<NumeralValueTuple> getTuple(final char numeral) {
        return SORTED_NUMERALS.stream()
                .filter(tuple -> tuple.getNumeral().equals(numeral))
                .findFirst();
    }

    static final NumeralValueTuple NULL_TUPLE = new NumeralValueTuple(' ', 0, POWER_OF_TEN);

    private final Character numeral;
    private final int value;
    private final NumeralType type;

    private NumeralValueTuple(final Character numeral, final int value, final NumeralType type) {
        this.numeral = numeral;
        this.value = value;
        this.type = type;
    }


    int getValue() {
        return value;
    }

    Character getNumeral() {
        return numeral;
    }

    NumeralType getType() {
        return type;
    }

}
