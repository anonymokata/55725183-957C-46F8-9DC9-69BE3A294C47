package convertions;

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
        assertThat(this.converter.toNumeral(1)).hasValue("I");
        assertThat(this.converter.toNumeral(10)).hasValue("X");
        assertThat(this.converter.toNumeral(100)).hasValue("C");
    }

    @Test
    public void toNumeral_WillConvertToTwoColumnNumeral_PowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(2)).hasValue("II");
        assertThat(this.converter.toNumeral(20)).hasValue("XX");
        assertThat(this.converter.toNumeral(200)).hasValue("CC");
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_HalfPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(5)).hasValue("V");
        assertThat(this.converter.toNumeral(50)).hasValue("L");
        assertThat(this.converter.toNumeral(500)).hasValue("D");
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_ReducedHalfPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(4)).hasValue("IV");
        assertThat(this.converter.toNumeral(40)).hasValue("XL");
        assertThat(this.converter.toNumeral(400)).hasValue("CD");
    }

    @Test
    public void toNumeral_WillConvertToSingleColumnNumeral_ReducedPowersOfTen() throws Exception {
        assertThat(this.converter.toNumeral(9)).hasValue("IX");
        assertThat(this.converter.toNumeral(90)).hasValue("XC");
        assertThat(this.converter.toNumeral(900)).hasValue("CM");
    }

    @Test
    public void toNumeral_WillConvertNumeral_MultipleSubtractors() {
        assertThat(this.converter.toNumeral(49)).hasValue("XLIX");
        assertThat(this.converter.toNumeral(994)).hasValue("CMXCIV");
    }

    @Test
    public void toNumeral_WillReturnNoNumeralWhenValueIsZero() throws Exception {
        assertThat(this.converter.toNumeral(0)).isEmpty();
    }

    @Test
    public void toNumeral_WillReturnNoNumeralWhenValueLessThanZero() throws Exception {
        assertThat(this.converter.toNumeral(-1)).isEmpty();
        assertThat(this.converter.toNumeral(-10)).isEmpty();
        assertThat(this.converter.toNumeral(Integer.MIN_VALUE)).isEmpty();
    }

    @Test
    public void toNumeral_WillHandleMaximumSupportedNumeralCorrectly() throws Exception {
        assertThat(this.converter.toNumeral(3999)).hasValue("MMMCMXCIX");
    }

    @Test
    public void toNumeral_WillReturnErrorWhenBeyondMaximumSupportedNumeral() throws Exception {
        assertThat(this.converter.toNumeral(4000)).isEmpty();
        assertThat(this.converter.toNumeral(5000)).isEmpty();
        assertThat(this.converter.toNumeral(10000)).isEmpty();
    }
}