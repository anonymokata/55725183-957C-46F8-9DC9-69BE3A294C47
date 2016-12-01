import java.util.Optional;

class RomanNumeralConverter {


    private final NumeralToIntegerConverter numeralToIntegerConverter;
    private final IntegerToNumeralConverter integerToNumeralConverter;

    RomanNumeralConverter() {
        this.numeralToIntegerConverter = new NumeralToIntegerConverter();
        this.integerToNumeralConverter = new IntegerToNumeralConverter();
    }

    Optional<Integer> toInteger(final String numeral) {
        return this.numeralToIntegerConverter.toInteger(numeral);
    }

    String toNumeral(final int value) {
        return this.integerToNumeralConverter.toNumeral(value);
    }
}
