package one;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NumberConvertersTest {

    @Test
    void returnDecimal3ForBinaryEquivalent() {
        assertEquals(3, NumberConverter.binaryToDecimal("11"));
    }

    @Test
    void returnDecimal0ForBinaryEquivalent() {
        assertEquals(0, NumberConverter.binaryToDecimal("0000"));
    }

    @Test
    void returnHex7aForAsciiLetterz() {
        assertEquals("7a", NumberConverter.hexOfAscii("z"));

    }

    @Test
    void returnDecimal10ForHexA() {
        assertEquals(10, NumberConverter.hexToDecimal("a"));
    }

    @Test
    void generateBinaryStringFromHexInt0() {
        assertEquals("0000", NumberConverter.convertDecimalOfHexDigitToFourBitBinaryString(0));
    }

    @Test
    void generateBinaryStringFromHexInt4() {
        assertEquals("0100", NumberConverter.convertDecimalOfHexDigitToFourBitBinaryString(4));
    }

    @Test
    void generateBinaryStringFromHexInt10() {
        assertEquals("1010", NumberConverter.convertDecimalOfHexDigitToFourBitBinaryString(10));
    }

    @Test
    void generateByteBinaryStringFromDecimalInt15() {
        assertEquals("00001111", NumberConverter.convertDecimalToByteBinaryString(15));
    }

    @Test
    void generateByteBinaryStringFromDecimalInt122() {
        assertEquals("01111010", NumberConverter.convertDecimalToByteBinaryString(122));
    }

    @Test
    void returnDecimalIndex10ForHexDigitA(){
        assertEquals(10, NumberConverter.hexToDecimal("a"));
    }

    @Test
    void returnDecimalIndex1ForHexDigit1(){
        assertEquals(1, NumberConverter.hexToDecimal("1"));
    }

    @Test
    void returnBase64DigitForDecimalIndex1(){
        BigDecimal decimalRepOne = new BigDecimal(1);
        assertEquals("B", NumberConverter.returnBase64DigitForDecimalIndex(decimalRepOne));
    }

    @Test
    void returnBase64DigitForDecimalIndex63() {
        BigDecimal decimalRepSixtyThree = new BigDecimal(63);
        assertEquals("/", NumberConverter.returnBase64DigitForDecimalIndex(decimalRepSixtyThree));
    }
}