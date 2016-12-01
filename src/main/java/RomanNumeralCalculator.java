import convertions.RomanNumeralConverter;

import java.util.Optional;

class RomanNumeralCalculator {

    private static final String INVALID_RIGHT_INPUT_ERROR = "Error: The right operand is not a valid numeral.";
    private static final String INVALID_LEFT_INPUT_ERROR = "Error: The left operand is not a valid numeral.";
    private static final String INVALID_BOTH_INPUTS_ERROR = "Error: Both operands are not valid numerals.";
    private static final String INVALID_RESULT_ERROR = "Error: Result is not a valid numeral.";

    private final RomanNumeralConverter converter;

    RomanNumeralCalculator() {
        this.converter = new RomanNumeralConverter();
    }

    String add(final String input1, final String input2) {
        return validateInputsAndPerformOperation(input1, input2, this::addInputs);
    }

    private Optional<String> addInputs(final int value1, final int value2) {
        return converter.toNumeral(value1 + value2);
    }

    String subtract(final String input1, final String input2) {
        return validateInputsAndPerformOperation(input1, input2, this::subtractInputs);
    }

    private Optional<String> subtractInputs(final int value, final int subtractor) {
        return converter.toNumeral(value - subtractor);
    }

    private String validateInputsAndPerformOperation(final String input1, final String input2, final Operation operation) {
        final Optional<Integer> leftInteger = converter.toInteger(input1);
        final Optional<Integer> rightInteger = converter.toInteger(input2);

        if (leftInteger.isPresent() && rightInteger.isPresent()) {
            return performOperationAndCheckResult(leftInteger.get(), rightInteger.get(), operation);
        } else {
            return chooseCorrectInputError(leftInteger, rightInteger);
        }
    }

    private String performOperationAndCheckResult(final int value1, final int value2, final Operation operation) {
        final Optional<String> result = operation.perform(value1, value2);
        if (result.isPresent()) {
            return result.get();
        } else {
            return INVALID_RESULT_ERROR;
        }
    }

    private String chooseCorrectInputError(final Optional<Integer> leftInteger, final Optional<Integer> rightInteger) {
        if (!leftInteger.isPresent() && !rightInteger.isPresent()) {
            return INVALID_BOTH_INPUTS_ERROR;
        } else if (!rightInteger.isPresent()) {
            return INVALID_RIGHT_INPUT_ERROR;
        } else {
            return INVALID_LEFT_INPUT_ERROR;
        }
    }

    @FunctionalInterface
    interface Operation {
        Optional<String> perform(int input1, int input2);
    }
}
