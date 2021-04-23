package one;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void returnHexStringForAsciiString() {
        assertEquals("48656c6c6f", Utilities.completeHexStringOfAsciiText("Hello"));
    }

    @Test
    void returnDecimal3ForBinaryEquivalent() {
        assertEquals(3, Utilities.binaryToDecimal("11"));
    }

    @Test
    void returnDecimal0ForBinaryEquivalent() {
        assertEquals(0, Utilities.binaryToDecimal("0000"));
    }

    @Test
    void returnHex7aForAsciiLetterz() {
        assertEquals("7a", Utilities.hexOfAscii("z"));

    }

    @Test
    void returnDecimal10ForHexA() {
        assertEquals(10, Utilities.hexToDecimal("a"));
    }

    @Test
    void generateBinaryStringFromHexInt0() {
        assertEquals("0000", Utilities.convertDecimalOfHexDigitToFourBitBinaryString(0));
    }

    @Test
    void generateBinaryStringFromHexInt4() {
        assertEquals("0100", Utilities.convertDecimalOfHexDigitToFourBitBinaryString(4));
    }

    @Test
    void generateBinaryStringFromHexInt10() {
        assertEquals("1010", Utilities.convertDecimalOfHexDigitToFourBitBinaryString(10));
    }

    @Test
    void generateByteBinaryStringFromDecimalInt15() {
        assertEquals("00001111", Utilities.convertDecimalToByteBinaryString(15));
    }

    @Test
    void generateByteBinaryStringFromDecimalInt122() {
        assertEquals("01111010", Utilities.convertDecimalToByteBinaryString(122));
    }

    @Test
    void returnDecimalIndex10ForHexDigitA(){
        assertEquals(10, Utilities.hexToDecimal("a"));
    }

    @Test
    void returnDecimalIndex1ForHexDigit1(){
        assertEquals(1, Utilities.hexToDecimal("1"));
    }

    @Test
    void returnBase64DigitForDecimalIndex1(){
        BigDecimal decimalRepOne = new BigDecimal(1);
        assertEquals("B", Utilities.returnBase64DigitForDecimalIndex(decimalRepOne));
    }

    @Test
    void returnBase64DigitForDecimalIndex63() {
        BigDecimal decimalRepSixtyThree = new BigDecimal(63);
        assertEquals("/", Utilities.returnBase64DigitForDecimalIndex(decimalRepSixtyThree));
    }
}