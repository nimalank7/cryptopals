package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedHexXorCombinerTest {

    private FixedHexXorCombiner combiner = new FixedHexXorCombiner();

    @Test
    void returnCorrectxorHexCombinationForStrings() {
        String hex1 = "1c0111001f010100061a024b53535009181c";
        String hex2 = "686974207468652062756c6c277320657965";
        String expectedXorCombination = "746865206b696420646f6e277420706c6179";
        String actualXorCombination = combiner.xorHexCombination(hex1, hex2);
        assertEquals(expectedXorCombination, actualXorCombination);
    }
}