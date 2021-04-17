package one;

import java.math.BigDecimal;

public class HexToBase64WithBinaryConverter {

    private HexToBase64Converter oldConverter = new HexToBase64Converter();

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
            completeBase64String.append(convertSixBitBinaryToBase64Digit(partialBinaryString));
        }

        return completeBase64String.toString();
    }

    String convertHexStringToBinaryString(String hex) {
        String[] hexArray = hex.split("");
        StringBuilder binaryString = new StringBuilder();
        for(String hexDigit : hexArray) {
            int decimalForHexDigit = oldConverter.returnDecimalIndexForHexDigit(hexDigit);
            var binaryForHexDigit = convertHexIntDigitToBinaryString(decimalForHexDigit);
            binaryString.append(binaryForHexDigit);
        }
        return binaryString.toString();
    }

    String convertSixBitBinaryToBase64Digit(String binaryString) {
        String[] binaryStringArray = new StringBuilder(binaryString).reverse().toString().split("");
        int decimal = 0;
        for (int i = 0; i < binaryStringArray.length; i++) {
            var product = (int) Math.pow(2, i) * Integer.parseInt(binaryStringArray[i]);
            decimal += product;
        }

        String base64Digit = oldConverter.returnBase64DigitForDecimalIndex(new BigDecimal(decimal));

        return base64Digit;
    }

    /*
    Hex int -> binary string
    E.g. A -> 1010
     */

    String convertHexIntDigitToBinaryString(int byteString) {
        int quotient = byteString;
        StringBuilder binaryStringBuilder = new StringBuilder();

        do {
            int remainder = quotient % 2;
            quotient = quotient/2;
            String remainderString = String.valueOf(remainder);
            binaryStringBuilder.insert(0, remainderString);
        } while(quotient > 0);

        if (binaryStringBuilder.length() < 4) {
            int padding = 4 - binaryStringBuilder.length();
            for (int i = 0; i < padding; i++) {
                binaryStringBuilder.insert(0, "0");
            }
        }

        return binaryStringBuilder.toString();
    }
}
