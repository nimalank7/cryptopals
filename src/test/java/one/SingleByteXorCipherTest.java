package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleByteXorCipherTest {

    SingleByteXorCipher singleByteXorCipher = new SingleByteXorCipher();

    @Test
    void returnCorrectDecodedAsciiStringForHexString() {
        String expectedAsciiString = "Ready Steady Cook!";
        String hexStringToDecode = "52656164792053746561647920436f6f6b21";
        String actualAsciiString = singleByteXorCipher.returnDecodedAsciiForHexString(hexStringToDecode);
        assertEquals(expectedAsciiString, actualAsciiString);
    }

    @Test
    void returnCorrectAsciiStringForParticularXorCipherKey() {
        String hexToDecode = "1c";
        String key = "68";
        String asciiOfDecodedHex = "t"; // Decoded hex is 74 - I used the example from challenge 2
        assertEquals(asciiOfDecodedHex, singleByteXorCipher.returnDecodedAsciiCharOfXorCipherEncodedHexCharWithKey(hexToDecode, key));
    }

    @Test
    void returnCompleteDecodedAsciiForEntireEncodedHexStringForXorCipherKey() {
        String hexToDecode = "1c1c";
        String key = "68";
        String asciiOfDecodedHex = "tt"; // Decoded hex is 74 - I used the example from challenge 2
        assertEquals(asciiOfDecodedHex, singleByteXorCipher.returnDecodedAsciiTextOfXorCipherEncodedHexStringWithKey(hexToDecode, key));
    }
}