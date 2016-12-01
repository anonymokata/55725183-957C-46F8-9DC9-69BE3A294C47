import convertions.RomanNumeralConverter;

import java.util.Optional;

class RomanNumeralCalculator {

    private final RomanNumeralConverter converter;

    RomanNumeralCalculator() {
        this.converter = new RomanNumeralConverter();
    }

    String add(final String input1, final String input2) {
        return validateInputsAndPerformOperation(input1, input2, this::addInputs);
    }

    String subtract(final String input1, final String input2) {
        return validateInputsAndPerformOperation(input1, input2, this::subtractInputs);
    }

    private Optional<String> addInputs(final int value1, final int value2) {
        return converter.toNumeral(value1 + value2);
    }

    private Optional<String> subtractInputs(final int value, final int subtractor) {
        return converter.toNumeral(value - subtractor);
    }

    private String validateInputsAndPerformOperation(final String input1, final String input2, final Operation operation) {
        final Optional<Integer> leftInteger = converter.toInteger(input1);
        final Optional<Integer> rightInteger = converter.toInteger(input2);

        if (leftInteger.isPresent() && rightInteger.isPresent()) {
            final int value1 = leftInteger.get();
            final int value2 = rightInteger.get();

            final Optional<String> result = operation.perform(value1, value2);

            if (result.isPresent()) {
                return result.get();
            } else {
                return "Error: Result is not a valid numeral.";
            }
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

    @FunctionalInterface
    interface Operation {
        Optional<String> perform(int input1, int input2);
    }
}
