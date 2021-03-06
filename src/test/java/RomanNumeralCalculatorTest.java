import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralCalculatorTest {

    private RomanNumeralCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new RomanNumeralCalculator();
    }

    @Test
    public void add_willHandleTheMostSimpleCase() {
        assertThat(calculator.add("I", "I")).isEqualTo("II");
    }

    @Test
    public void add_willHandleTripleRepetitions() {
        checkAddIsCommutative("I", "II", "III");
    }

    @Test
    public void add_willHandleNonMultiplesOfTen() {
        assertThat(calculator.add("V", "V")).isEqualTo("X");
        assertThat(calculator.add("D", "D")).isEqualTo("M");
    }

    @Test
    public void add_willHandleMixedMultiplesOfTenWithNonMultiplesOfTen() {
        checkAddIsCommutative("V", "I", "VI");
        checkAddIsCommutative("V", "II", "VII");
        checkAddIsCommutative("V", "III", "VIII");
    }

    @Test
    public void add_willHandleIncreasedMultiplesOfTenWell() {
        checkAddIsCommutative("VII", "VII", "XIV");
        checkAddIsCommutative("VIII", "VII", "XV");
    }

    @Test
    public void add_canProduceReducedMultiplesOfTen() {
        checkAddIsCommutative("VII", "II", "IX");
        checkAddIsCommutative("V", "IV", "IX");

        checkAddIsCommutative("L", "XL", "XC");
        checkAddIsCommutative("XXI", "LXIX", "XC");
    }

    @Test
    public void add_canProduceComplexNumerals() {
        checkAddIsCommutative("LX", "IX", "LXIX");
        checkAddIsCommutative("MCDXLVII", "II", "MCDXLIX");
    }

    @Test
    public void add_ExampleFromKataInstructions() throws Exception {
        checkAddIsCommutative("XIV", "LX", "LXXIV");
    }

    @Test
    public void add_willHandleNumeralsThatReduceMultipleOfTen() {
        checkAddIsCommutative("IV", "I", "V");
    }

    private void checkAddIsCommutative(final String a, final String b, final String expectedValue) {
        assertThat(calculator.add(a, b)).isEqualTo(expectedValue);
        assertThat(calculator.add(b, a)).isEqualTo(expectedValue);
    }

    @Test
    public void add_willReturnInformativeStringWhenTheLeftValueIsNotAValidNumeral() {
        assertThat(calculator.add("Z", "II")).isEqualTo("Error: The left operand is not a valid numeral.");
    }

    @Test
    public void add_willReturnInformativeStringWhenTheRightValueIsNotAValidNumeral() {
        assertThat(calculator.add("III", "Z")).isEqualTo("Error: The right operand is not a valid numeral.");
    }

    @Test
    public void add_willReturnInformativeStringWhenTheNeitherValueIsNotAValidNumeral() {
        assertThat(calculator.add("ZZZ", "Z")).isEqualTo("Error: Both operands are not valid numerals.");
    }


    @Test
    public void subtract_willReturnInformativeStringWhenTheLeftValueIsNotAValidNumeral() {
        assertThat(calculator.subtract("Z", "II")).isEqualTo("Error: The left operand is not a valid numeral.");
    }

    @Test
    public void subtract_willReturnInformativeStringWhenTheRightValueIsNotAValidNumeral() {
        assertThat(calculator.subtract("III", "Z")).isEqualTo("Error: The right operand is not a valid numeral.");
    }

    @Test
    public void subtract_willReturnInformativeStringWhenTheNeitherValueIsNotAValidNumeral() {
        assertThat(calculator.subtract("ZZZ", "Z")).isEqualTo("Error: Both operands are not valid numerals.");
    }

    @Test
    public void subtract_simplestCase() {
        assertThat(calculator.subtract("II", "I")).isEqualTo("I");
    }

    @Test
    public void subtract_typicalSimpleExamples() {
        assertThat(calculator.subtract("X", "I")).isEqualTo("IX");
        assertThat(calculator.subtract("VI", "I")).isEqualTo("V");
        assertThat(calculator.subtract("VI", "II")).isEqualTo("IV");
    }

    @Test
    public void subtract_WillHandleParticularlyUglySubtractions() {
        assertThat(calculator.subtract("M", "I")).isEqualTo("CMXCIX");
        assertThat(calculator.subtract("CMXCIX", "C")).isEqualTo("DCCCXCIX");
        assertThat(calculator.subtract("DCCCXCIX", "DCCCXCVIII")).isEqualTo("I");
    }

    @Test
    public void subtract_WillErrorWhenResultIsNotAValidNumeral() throws Exception {
        assertThat(calculator.subtract("I", "I")).isEqualTo("Error: Result is not a valid numeral.");
        assertThat(calculator.subtract("I", "II")).isEqualTo("Error: Result is not a valid numeral.");
        assertThat(calculator.subtract("X", "C")).isEqualTo("Error: Result is not a valid numeral.");
    }

    @Test
    public void testSuggestionsFromOtherDevelopers() throws Exception {
        assertThat(calculator.add("MVI", "CX")).isEqualTo("MCXVI");
        assertThat(calculator.add("MCDXLVII", "CCCXXII")).isEqualTo("MDCCLXIX");
        assertThat(calculator.subtract("MMM", "I")).isEqualTo("MMCMXCIX");
        assertThat(calculator.subtract("CCLXXV", "LIV")).isEqualTo("CCXXI");
        assertThat(calculator.subtract("MCXV", "DLVI")).isEqualTo("DLIX");
    }
}
