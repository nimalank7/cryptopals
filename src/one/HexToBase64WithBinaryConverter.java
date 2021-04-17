package one;

import java.math.BigDecimal;

public class HexToBase64WithBinaryConverter {

    private final HexToBase64Converter oldConverter = new HexToBase64Converter();

    String convertHexToBase64(String hex) {
        StringBuilder completeBinaryString = new StringBuilder(convertHexStringToBinaryString(hex));
        StringBuilder completeBase64String = new StringBuilder();

        int completeBinaryStringLength = completeBinaryString.length();
        int amount_of_padding = completeBinaryStringLength % 6;
        boolean requiresPadding = amount_of_padding != 0;

        if (requiresPadding) {
            for(int i = 0; i < amount_of_padding; i++) {
                completeBinaryString.append("0");
            }
        }

        for (int i = 0; i < completeBinaryStringLength; i+= 6) {
            String partialBinaryString = completeBinaryString.substring(i, i+6);
            completeBase64String.append(convertBinaryOfHexDigitToBase64Digit(partialBinaryString));
        }

        return completeBase64String.toString();
    }

    String convertHexStringToBinaryString(String hex) {
        String[] hexArray = hex.split("");
        StringBuilder binaryString = new StringBuilder();
        for(String hexDigit : hexArray) {
            int decimalForHexDigit = oldConverter.returnDecimalIndexForHexDigit(hexDigit);
            var binaryForHexDigit = convertDecimalOfHexDigitToBinaryString(decimalForHexDigit);
            binaryString.append(binaryForHexDigit);
        }
        return binaryString.toString();
    }

    String convertBinaryOfHexDigitToBase64Digit(String binaryOfHexDigit) {
        int decimalOfBinaryOfHexDigit = NumberConverter.decimalOfBinary(binaryOfHexDigit);
        String base64DigitOfHexDigit = oldConverter.returnBase64DigitForDecimalIndex(new BigDecimal(decimalOfBinaryOfHexDigit));

        return base64DigitOfHexDigit;
    }

    /*
    Hex int -> binary string
    E.g. A -> 1010
     */

    String convertDecimalOfHexDigitToBinaryString(int decimalOfHexDigit) {
        int quotient = decimalOfHexDigit;
        StringBuilder decimalOfHexDigitBuilder = new StringBuilder();

        do {
            int remainder = quotient % 2;
            quotient = quotient/2;
            String remainderString = String.valueOf(remainder);
            decimalOfHexDigitBuilder.insert(0, remainderString);
        } while(quotient > 0);

        if (decimalOfHexDigitBuilder.length() < 4) {
            int padding = 4 - decimalOfHexDigitBuilder.length();
            for (int i = 0; i < padding; i++) {
                decimalOfHexDigitBuilder.insert(0, "0");
            }
        }

        return decimalOfHexDigitBuilder.toString();
    }
}
