import java.util.Optional;

class RomanNumeralCalculator {

    private final RomanNumeralConverter converter;

    RomanNumeralCalculator() {
        this.converter = new RomanNumeralConverter();
    }

    String add(final String input1, final String input2) {

        final Optional<Integer> leftInteger = converter.toInteger(input1);
        final Optional<Integer> rightInteger = converter.toInteger(input2);

        if (leftInteger.isPresent() && rightInteger.isPresent()) {
            final int value1 = leftInteger.get();
            final int value2 = rightInteger.get();

            return converter.toNumeral(value1 + value2);
        } else {
            if (!leftInteger.isPresent() && !rightInteger.isPresent()) {
                return "Error: Both operands are not valid numerals.";
            } else if (!rightInteger.isPresent()) {
                return "Error: The right operand is not a valid numeral.";
            } else {
                return "Error: The left operand is not a valid numeral.";
            }
        }
    }

}
