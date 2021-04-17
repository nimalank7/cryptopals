package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexToBase64WithBinaryConverterTest {

    private HexToBase64WithBinaryConverter converter = new HexToBase64WithBinaryConverter();

    @Test
    void hexToBase64() {
        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String convertedValue = converter.convertHexToBase64(hex);
        String base64Value = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        assertEquals(base64Value, convertedValue);
    }

    @Test
    void generatesPartialBase64StringForPartialBinary() {
        String expectedBase64String = "R";
        String binaryString = "010001";
        assertEquals(expectedBase64String, converter.convertBinaryOfHexDigitToBase64Digit(binaryString));
    }

    @Test
    void generatesCompleteBinaryStringForHexString0() {
        String expectedBinaryString = "0000";
        String hexString = "0";
        assertEquals(expectedBinaryString, converter.convertHexStringToBinaryString(hexString));
    }

    @Test
    void generatesCompleteBinaryStringForHexString0A89() {
        String expectedBinaryString = "0000101010001001";
        String hexString = "0a89";
        assertEquals(expectedBinaryString, converter.convertHexStringToBinaryString(hexString));
    }

    @Test
    void generatesCompleteBinaryStringForHexStringAB12() {
        String expectedBinaryString = "1010101100010010";
        String hexString = "ab12";
        assertEquals(expectedBinaryString, converter.convertHexStringToBinaryString(hexString));
    }

    @Test
    void generateBinaryStringFromHexInt0() {
        assertEquals("0000", converter.convertDecimalOfHexDigitToBinaryString(0));
    }

    @Test
    void generateBinaryStringFromHexInt4() {
        assertEquals("0100", converter.convertDecimalOfHexDigitToBinaryString(4));
    }

    @Test
    void generateBinaryStringFromHexInt10() {
        assertEquals("1010", converter.convertDecimalOfHexDigitToBinaryString(10));
    }

    @Test
    void generateBinaryStringFromHexInt56() {
        assertEquals("111000", converter.convertDecimalOfHexDigitToBinaryString(56));
    }
}