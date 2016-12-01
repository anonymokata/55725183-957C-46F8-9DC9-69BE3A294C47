package convertions;

import java.util.Optional;

public class RomanNumeralConverter {

    private final NumeralToIntegerConverter numeralToIntegerConverter;
    private final IntegerToNumeralConverter integerToNumeralConverter;

    public RomanNumeralConverter() {
        this.numeralToIntegerConverter = new NumeralToIntegerConverter();
        this.integerToNumeralConverter = new IntegerToNumeralConverter();
    }

    public Optional<Integer> toInteger(final String numeral) {
        return this.numeralToIntegerConverter.toInteger(numeral);
    }

    public Optional<String> toNumeral(final int value) {
        return this.integerToNumeralConverter.toNumeral(value);
    }
}
