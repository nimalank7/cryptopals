package one;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HexToBase64ConverterTest {
    private HexToBase64Converter converter = new HexToBase64Converter();

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
        BigDecimal actualDecimalRep = converter.hexStringToDecimalString("7c");

        assertTrue(decimalRep.compareTo(actualDecimalRep) == 0);
    }

    @Test
    void convertHexfftoDecimal255() {
        BigDecimal decimalRep = new BigDecimal(255);
        BigDecimal actualDecimalRep = converter.hexStringToDecimalString("ff");

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
}