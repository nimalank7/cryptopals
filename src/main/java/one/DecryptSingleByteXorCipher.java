package one;

public class DecryptSingleByteXorCipher {

    private FixedHexXorCombiner hexXorCombiner = new FixedHexXorCombiner();
    private HexToBase64WithBinaryConverter hexToBase64WithBinaryConverter = new HexToBase64WithBinaryConverter();


    public static void main(String[] args) {
        String encodedHex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        DecryptSingleByteXorCipher decryptSingleByteXorCipher = new DecryptSingleByteXorCipher();
        System.out.println(decryptSingleByteXorCipher.decodesAsciiForEncodedHexForEachAsciiKeys(encodedHex));
    }

    /*
    Sample string 'hello' is converted into ASCII and then into binary
    Sample character is converted into ASCII and then into binary
    Both are XOR-ed and the resulting binary converted into hex
    To get the original hex string we need to XOR the encoded string with the character
    Then convert the 'decoded' string into ASCII and print out
     */

    /*
    Take our initial number 0
    Convert to hexadecimal
    Pass in our string with the hexadecimal into our XOR
    Decode the resultant value
     */

    DecryptedText decodesAsciiForEncodedHexForEachAsciiKeys(String text) {
        int bestScore = 0;
        int bestKey = 0;
        String bestText = "";

        for (int i = 0; i < 256; i++) {
            String hexDigitsOfBinaryStringKey = Utilities.hexDigitsOfAscii(i);
            String decodedAscii = returnDecodedAsciiTextOfXorCipherEncodedHexStringWithKey(text, hexDigitsOfBinaryStringKey);
            //System.out.println(decodedAscii + " Key: " + binaryStringOfKey + " int value: " + i);
            int scoreOfText = score(decodedAscii);
            if (scoreOfText > bestScore) {
                bestScore = scoreOfText;
                bestKey = i;
                bestText = decodedAscii;
            }
        }
        return new DecryptedText(bestKey, bestScore, bestText, text);
    }

    int score(String englishString) {
        int score = 0;
        char[] frequentEnglishLetters = {'e', 't', 'a', 'o', 'i', 'n', ' '};
        for(char letter: frequentEnglishLetters) {
            for (int i = 0; i < englishString.length(); i++) {
                if (letter == englishString.toLowerCase().charAt(i)) {
                    score += 1;
                }
            }
        }

        return score;
    }

    String returnDecodedAsciiTextOfXorCipherEncodedHexStringWithKey(String hex, String key) {
        StringBuilder asciiText = new StringBuilder();
        for (int i = 0; i < hex.length(); i+= 2) {
            String hexForAsciiChar = hex.substring(i, i + 2);
            String decodedAsciiChar = returnDecodedAsciiCharOfXorCipherEncodedHexCharWithKey(hexForAsciiChar, key);
            asciiText.append(decodedAsciiChar);
        }

        return asciiText.toString();
    }

    String returnDecodedAsciiCharOfXorCipherEncodedHexCharWithKey(String hex, String key) {
        String decodedHex = hexXorCombiner.xorHexCombination(hex, key);
        return returnDecodedAsciiForHexString(decodedHex);
    }

    String returnDecodedAsciiForHexString(String hexString) {
        StringBuilder asciiString = new StringBuilder();

        for (int i = 0; i < hexString.length(); i+= 2) {
            String hexDigitOne = Character.toString(hexString.charAt(i));
            String hexDigitTwo = Character.toString(hexString.charAt(i + 1));
            String binaryForHexDigitOne = hexToBase64WithBinaryConverter.convertHexStringToBinaryString(hexDigitOne);
            String binaryForHexDigitTwo = hexToBase64WithBinaryConverter.convertHexStringToBinaryString(hexDigitTwo);
            int asciiInt = Utilities.binaryToDecimal(binaryForHexDigitOne + binaryForHexDigitTwo);
            asciiString.append((char) asciiInt);
        }
        return asciiString.toString();
    }


}
