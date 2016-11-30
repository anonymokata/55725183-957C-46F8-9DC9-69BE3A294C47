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
    public void toInteger_WillConvertSingleValue_PowersOfTen() throws Exception {
        assertThat(this.converter.toInteger("I")).isEqualTo(1);
        assertThat(this.converter.toInteger("X")).isEqualTo(10);
        assertThat(this.converter.toInteger("C")).isEqualTo(100);
    }

    @Test
    public void toIntegerWillConvertTwoRepeatedValues_PowersOfTen() throws Exception {
        assertThat(this.converter.toInteger("II")).isEqualTo(2);
        assertThat(this.converter.toInteger("XX")).isEqualTo(20);
        assertThat(this.converter.toInteger("CC")).isEqualTo(200);
    }

    @Test
    public void toIntegerWillConvertThreeRepeatedValues_PowersOfTen() throws Exception {
        assertThat(this.converter.toInteger("III")).isEqualTo(3);
        assertThat(this.converter.toInteger("XXX")).isEqualTo(30);
        assertThat(this.converter.toInteger("CCC")).isEqualTo(300);
    }
}