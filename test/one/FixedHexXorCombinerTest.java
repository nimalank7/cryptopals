package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedHexXorCombinerTest {

    private FixedHexXorCombiner combiner = new FixedHexXorCombiner();

    @Test
    void returnCorrectXorHexCombinationForStrings() {
        String hex1 = "1c0111001f010100061a024b53535009181c";
        String hex2 = "686974207468652062756c6c277320657965";
        String expectedXorCombination = "746865206b696420646f6e277420706c6179";
        String actualXorCombination = combiner.xorHexCombination(hex1, hex2);
        assertEquals(expectedXorCombination, actualXorCombination);
    }

    @Test
    void returnCorrectXorStringOneForBinaryDigitsOneAndZero() {
        assertEquals("1", combiner.xorOfBinaryDigit("1", "0"));
    }

    @Test
    void returnCompleteXorStringForBinaryOfHexDigits() {
        String partialBinaryStringOne = "0101";
        String partialBinaryStringTwo = "1010";
        String expectedXorBinaryString = "1111";
        assertEquals(expectedXorBinaryString, combiner.xorOfBinaryOfHexDigit(partialBinaryStringOne, partialBinaryStringTwo));
    }
}