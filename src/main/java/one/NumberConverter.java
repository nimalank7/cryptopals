package one;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class NumberConverter {

    public static final String HEXADECIMAL = "0123456789abcdef";
    public static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static int binaryToDecimal(String binaryString) {
        String[] binaryStringArray = new StringBuilder(binaryString).reverse().toString().split("");
        int decimal = 0;
        for (int i = 0; i < binaryStringArray.length; i++) {
            var product = (int) Math.pow(2, i) * Integer.parseInt(binaryStringArray[i]);
            decimal += product;
        }

        return decimal;
    }

    static int hexToDecimal(String hexDigit) {
        return HEXADECIMAL.indexOf(hexDigit);
    }

    static String decimalToHexadecimalDigit(int index) {
        String[] hexadecimalSystemArray = HEXADECIMAL.split("");
        return hexadecimalSystemArray[index];
    }

    static int decimalOfBinary(String binaryString) {
        String[] binaryDigitArray = new StringBuilder(binaryString).reverse().toString().split("");
        int decimalOfBinary = 0;
        for (int i = 0; i < binaryDigitArray.length; i++) {
            var productOfBinaryDigitWithPowerOfTwo = (int) Math.pow(2, i) * Integer.parseInt(binaryDigitArray[i]);
            decimalOfBinary += productOfBinaryDigitWithPowerOfTwo;
        }
        return decimalOfBinary;
    }

    static String hexOfAscii(String asciiChar) {
        String binaryOfAsciiChar = convertDecimalToByteBinaryString(asciiChar.getBytes(StandardCharsets.US_ASCII)[0]);
        int decimalOfHexOne = decimalOfBinary(binaryOfAsciiChar.substring(0, 4));
        int decimalOfHexTwo = decimalOfBinary(binaryOfAsciiChar.substring(4, 8));
        return new StringBuilder(decimalToHexadecimalDigit(decimalOfHexOne)).append(decimalToHexadecimalDigit(decimalOfHexTwo)).toString();
    }

    static String convertDecimalToBinaryStringWithPadding(int decimalOfHexDigit, int paddingLength) {
        int quotient = decimalOfHexDigit;
        StringBuilder decimalOfHexDigitBuilder = new StringBuilder();

        do {
            int remainder = quotient % 2;
            quotient = quotient/2;
            String remainderString = String.valueOf(remainder);
            decimalOfHexDigitBuilder.insert(0, remainderString);
        } while(quotient > 0);

        if (decimalOfHexDigitBuilder.length() < paddingLength) {
            int padding = paddingLength - decimalOfHexDigitBuilder.length();
            for (int i = 0; i < padding; i++) {
                decimalOfHexDigitBuilder.insert(0, "0");
            }
        }

        return decimalOfHexDigitBuilder.toString();
    }

    static String convertDecimalOfHexDigitToFourBitBinaryString(int decimalOfHexDigit) {
        return convertDecimalToBinaryStringWithPadding(decimalOfHexDigit, 4);
    }

    static String convertDecimalToByteBinaryString(int decimal) {
        return convertDecimalToBinaryStringWithPadding(decimal, 8);

    }

    static String returnBase64DigitForDecimalIndex(BigDecimal base64DigitInt) {
        char base64Digit = new StringBuilder(BASE64).charAt(base64DigitInt.intValue());
        return Character.toString(base64Digit);
    }
}
