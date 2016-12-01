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

}
