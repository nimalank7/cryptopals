package one;

import java.math.BigDecimal;

public class HexToBase64WithBinaryConverter {

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
            int decimalForHexDigit = Utilities.hexToDecimal(hexDigit);
            var binaryForHexDigit = Utilities.convertDecimalOfHexDigitToFourBitBinaryString(decimalForHexDigit);
            binaryString.append(binaryForHexDigit);
        }
        return binaryString.toString();
    }

    String convertBinaryOfHexDigitToBase64Digit(String binaryOfHexDigit) {
        int decimalOfBinaryOfHexDigit = Utilities.decimalOfBinary(binaryOfHexDigit);
        String base64DigitOfHexDigit = Utilities.returnBase64DigitForDecimalIndex(new BigDecimal(decimalOfBinaryOfHexDigit));

        return base64DigitOfHexDigit;
    }
}
