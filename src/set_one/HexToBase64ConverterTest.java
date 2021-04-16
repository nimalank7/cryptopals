package set_one;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HexToBase64ConverterTest {
    HexToBase64Converter converter = new HexToBase64Converter();


    @Test
    void hexToBase64() {
        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String convertedValue = converter.convertHexToBase64(hex);
        String base64Value = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        assertEquals(base64Value, convertedValue);
    }

    @Test
    void convertsHex7CtoDecimal124() {
        BigDecimal decimalRep = new BigDecimal(124);
        BigDecimal actualDecimalRep = converter.hexToDecimal("7c");

        assertTrue(decimalRep.compareTo(actualDecimalRep) == 0);
    }

    @Test
    void convertHexfftoDecimal255() {
        BigDecimal decimalRep = new BigDecimal(255);
        BigDecimal actualDecimalRep = converter.hexToDecimal("ff");

        assertTrue(decimalRep.compareTo(actualDecimalRep) == 0);
    }

    @Test
    void convertDecimal10ToBase64K(){
        BigDecimal decimalRepTen = new BigDecimal(10);
        assertEquals("K", converter.decimalToBase64(decimalRepTen));
    }

    @Test
    void convertDecimal63ToBase64K() {
        BigDecimal decimalRepSixtyThree = new BigDecimal(63);
        assertEquals("/", converter.decimalToBase64(decimalRepSixtyThree));
    }

    @Test
    void returnDecimalIndex10ForHexDigitA(){
        assertEquals(10, converter.returnDecimalIndexForHexDigit("a"));
    }

    @Test
    void returnDecimalIndex1ForHexDigit1(){
        assertEquals(1, converter.returnDecimalIndexForHexDigit("1"));
    }

    @Test
    void returnDecimalIndex1ForBase64Digit1(){
        BigDecimal decimalRepOne = new BigDecimal(1);
        assertEquals("B", converter.returnBase64DigitForDecimalIndex(decimalRepOne));
    }

    @Test
    void returnBase64DigitForDecimalIndex63() {
        BigDecimal decimalRepSixtyThree = new BigDecimal(63);
        assertEquals("/", converter.returnBase64DigitForDecimalIndex(decimalRepSixtyThree));
    }


}