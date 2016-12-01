import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerToNumeralConverterTest {

    private IntegerToNumeralConverter converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new IntegerToNumeralConverter();
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

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_HalfPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(5)).isEqualTo("V");
        assertThat(this.converter.toNumeral(50)).isEqualTo("L");
        assertThat(this.converter.toNumeral(500)).isEqualTo("D");
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_ReducedHalfPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(4)).isEqualTo("IV");
        assertThat(this.converter.toNumeral(40)).isEqualTo("XL");
        assertThat(this.converter.toNumeral(400)).isEqualTo("CD");
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_ReducedPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(9)).isEqualTo("IX");
        assertThat(this.converter.toNumeral(90)).isEqualTo("XC");
        assertThat(this.converter.toNumeral(900)).isEqualTo("CM");
    }

    @Test
    public void toNumeral_WillConvertNumeral_MultipleSubtractors() {
        assertThat(this.converter.toNumeral(49)).isEqualTo("XLIX");
        assertThat(this.converter.toNumeral(994)).isEqualTo("CMXCIV");
    }

}