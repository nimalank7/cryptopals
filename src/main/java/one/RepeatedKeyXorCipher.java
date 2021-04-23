package one;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RepeatedKeyXorCipher {

    FixedHexXorCombiner fixedHexXorCombiner = new FixedHexXorCombiner();

    /*
    Grab length of text and then generate key the same length
    Translate ASCII text into binary
    Translate key into binary
    XOR each byte together
    Translate the result from bytes to 2x 4 bits -> hex
     */

    /*
    Care must be taken when using Integer.toHexString() as it will ignore the leading 0s.
    Hence decimal -> binary with 0 padding
     */

    public static void main(String[] args) {
        RepeatedKeyXorCipher repeatedKeyXorCipher = new RepeatedKeyXorCipher();
        System.out.println(repeatedKeyXorCipher.generateRepeatingHexForAsciiTextWithKey("Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal", "ICE"));

    }

    String generateRepeatingHexForAsciiTextWithKey(String asciiText, String asciiKey) {
        String generatedAsciiKey = generateAsciiKeyForTextLength(asciiKey, asciiText.length());
        byte[] asciiTextBytes = asciiText.getBytes(StandardCharsets.US_ASCII);
        byte[] asciiKeyBytes = generatedAsciiKey.getBytes(Charset.defaultCharset());
        StringBuilder encryptedRepeatingKeyHex = new StringBuilder();
        for(int i = 0; i < asciiKeyBytes.length; i++) {
            int asciiXorByte = asciiKeyBytes[i] ^ asciiTextBytes[i];
            String binaryOfAsciiXorByte = Integer.toBinaryString(asciiXorByte);
            StringBuilder paddingBuilder = new StringBuilder();
            if (binaryOfAsciiXorByte.length() < 8) {
                int padding = 8 - binaryOfAsciiXorByte.length();
                for (int p = 0; p < padding; p++) {
                    paddingBuilder.append("0");
                }
            }
            paddingBuilder.append(binaryOfAsciiXorByte);
            int decimalOfBinaryOne = Integer.parseInt(paddingBuilder.substring(0, 4), 2);
            int decimalOfBinaryTwo = Integer.parseInt(paddingBuilder.substring(4, 8), 2);

            String hexOne = Integer.toHexString(decimalOfBinaryOne);
            String hexTwo = Integer.toHexString(decimalOfBinaryTwo);
            encryptedRepeatingKeyHex.append(hexOne).append(hexTwo);
        }
        return encryptedRepeatingKeyHex.toString();
    }

    String generateAsciiKeyForTextLength(String ascii, int length) {
        String[] asciiKeyChars = ascii.split("");
        StringBuilder asciiKeyBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int asciiKeyCharIndex = i % asciiKeyChars.length;
            asciiKeyBuilder.append(asciiKeyChars[asciiKeyCharIndex]);
        }

        return asciiKeyBuilder.toString();
    }
}
