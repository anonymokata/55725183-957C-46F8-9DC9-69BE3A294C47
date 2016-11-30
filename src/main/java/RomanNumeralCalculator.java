class RomanNumeralCalculator {

    private final RomanNumeralConverter converter;

    RomanNumeralCalculator() {
        this.converter = new RomanNumeralConverter();
    }

    String add(final String input1, final String input2) {

        final int value1 = converter.toInteger(input1);
        final int value2 = converter.toInteger(input2);

        return converter.toNumeral(value1 + value2);
    }

}
