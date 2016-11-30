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
    public void toInteger_WillConvertTwoRepeatedValues_PowersOfTen() throws Exception {
        assertThat(this.converter.toInteger("II")).isEqualTo(2);
        assertThat(this.converter.toInteger("XX")).isEqualTo(20);
        assertThat(this.converter.toInteger("CC")).isEqualTo(200);
    }

    @Test
    public void toInteger_WillConvertThreeRepeatedValues_PowersOfTen() throws Exception {
        assertThat(this.converter.toInteger("III")).isEqualTo(3);
        assertThat(this.converter.toInteger("XXX")).isEqualTo(30);
        assertThat(this.converter.toInteger("CCC")).isEqualTo(300);
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_PowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(1)).isEqualTo("I");
        assertThat(this.converter.toNumeral(10)).isEqualTo("X");
        assertThat(this.converter.toNumeral(100)).isEqualTo("C");
    }

    @Test
    public void toNumeral_WillConvertToTwoColumnNumeral_PowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(2)).isEqualTo("II");
        assertThat(this.converter.toNumeral(20)).isEqualTo("XX");
        assertThat(this.converter.toNumeral(200)).isEqualTo("CC");
    }
}