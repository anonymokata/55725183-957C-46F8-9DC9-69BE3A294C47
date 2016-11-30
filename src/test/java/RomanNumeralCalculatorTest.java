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
    public void addWillHandleTheMostSimpleCase() {
        final String input1 = "I";
        final String input2 = "I";
        final String result = calculator.add(input1, input2);
        assertThat(result).isEqualTo("II");
    }

}
