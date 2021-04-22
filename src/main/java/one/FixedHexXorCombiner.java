package one;

public class FixedHexXorCombiner {

    private final HexToBase64WithBinaryConverter hexToBase64WithBinaryConverter = new HexToBase64WithBinaryConverter();
    private final int LENGTH_OF_BINARY_STRING = 4;

    /*
    Convert both hex to binary
    XOR binary
    Convert binary to hex
     */

    String xorHexCombination(String hexOne, String hexTwo) {
        StringBuilder xorHexCombinationBuilder = new StringBuilder();
        String[] hexOneDigitArray = hexOne.split("");
        String[] hexTwoDigitArray = hexTwo.split("");

        for(int i = 0; i < hexOneDigitArray.length; i++) {
            String binaryOfHexOneDigit = hexToBase64WithBinaryConverter.convertHexStringToBinaryString(hexOneDigitArray[i]);
            String binaryOfHexTwoDigit = hexToBase64WithBinaryConverter.convertHexStringToBinaryString(hexTwoDigitArray[i]);
            String xorOfBinaryOfHexDigits = xorOfBinaryOfHexDigit(binaryOfHexOneDigit, binaryOfHexTwoDigit);
            int decimalOfXorOfBinaryOfHexDigits = Utilities.decimalOfBinary(xorOfBinaryOfHexDigits);
            String hexDigitsForXorOfBinaryOfHexDigits = Utilities.decimalToHexadecimalDigit(decimalOfXorOfBinaryOfHexDigits);
            xorHexCombinationBuilder.append(hexDigitsForXorOfBinaryOfHexDigits);
        }
        return xorHexCombinationBuilder.toString();
    }

    String xorOfBinaryOfHexDigit(String firstBinaryOfHexDigit, String secondBinaryOfHexDigit) {
        StringBuilder xorOfBinaryOfHexDigit = new StringBuilder();
        String[] firstBinaryOfHexDigitArray = firstBinaryOfHexDigit.split("");
        String[] secondBinaryOfHexDigitArray = secondBinaryOfHexDigit.split("");

        for(int i = 0; i < LENGTH_OF_BINARY_STRING; i++) {
            String xorOfBinaryDigits = xorOfBinaryDigit(firstBinaryOfHexDigitArray[i], secondBinaryOfHexDigitArray[i]);
            xorOfBinaryOfHexDigit.append(xorOfBinaryDigits);
        }

        return xorOfBinaryOfHexDigit.toString();
    }

    String xorOfBinaryDigit(String firstBinaryDigit, String secondBinaryDigit) {
        Integer xorResult = Integer.parseInt(firstBinaryDigit) ^ Integer.parseInt(secondBinaryDigit);
        return xorResult.toString();
    }
}
