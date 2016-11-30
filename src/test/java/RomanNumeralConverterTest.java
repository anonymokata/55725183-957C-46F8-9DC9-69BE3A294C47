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
    public void toInteger_WillConvertSingleValue_I() throws Exception {
        assertThat(this.converter.toInteger("I")).isEqualTo(1);
    }

    @Test
    public void toInteger_WillConvertSingleValue_X() throws Exception {
        assertThat(this.converter.toInteger("X")).isEqualTo(10);
    }

    @Test
    public void toInteger_WillConvertSingleValue_C() throws Exception {
        assertThat(this.converter.toInteger("C")).isEqualTo(100);
    }

    @Test
    public void toIntegerWillConvertRepeatedValues_II() throws Exception {
        assertThat(this.converter.toInteger("II")).isEqualTo(2);
    }

    @Test
    public void toIntegerWillConvertRepeatedValues_III() throws Exception {
        assertThat(this.converter.toInteger("III")).isEqualTo(3);
    }
}