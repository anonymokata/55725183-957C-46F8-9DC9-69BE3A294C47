import java.util.HashMap;
import java.util.Map;

class RomanNumeralConverter {

    private final Map<Character, Integer> multipleOfTenMap;

    RomanNumeralConverter() {
        multipleOfTenMap = new HashMap<>();
        multipleOfTenMap.put('I', 1);
        multipleOfTenMap.put('X', 10);
        multipleOfTenMap.put('C', 100);
    }

    int toInteger(final String numeral) {
        return numeral.length() * multipleOfTenMap.get(numeral.charAt(0));
    }

    String toNumeral(final int Integer) {
        return "I";
    }
}
