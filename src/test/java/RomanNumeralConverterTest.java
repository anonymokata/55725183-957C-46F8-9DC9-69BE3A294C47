import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RomanNumeralConverterTest {

    private RomanNumeralConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new RomanNumeralConverter();
    }

    @Test
    public void toIntegerWillConvertSimpleCase() throws Exception {
        assertThat(this.converter.toInteger("I")).isEqualTo(1);
    }
}