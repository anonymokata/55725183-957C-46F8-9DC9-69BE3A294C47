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
        final String result = calculator.add("I", "I");
        assertThat(result).isEqualTo("II");
    }

    @Test
    public void add_willHandleTripleRepetitions() {
        assertThat(calculator.add("I", "II")).isEqualTo("III");
        assertThat(calculator.add("II", "I")).isEqualTo("III");
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
