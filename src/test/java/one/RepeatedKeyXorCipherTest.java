package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedKeyXorCipherTest {

    RepeatedKeyXorCipher repeatedKeyXorCipher = new RepeatedKeyXorCipher();

    @Test
    void returnEncryptedHexStringOfAsciiTextFirstLine() {
        String asciiToEncrypt = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
        String asciiKey = "ICE";
        String actualEncryptedHex = repeatedKeyXorCipher.generateRepeatingHexForAsciiTextWithKey(asciiToEncrypt, asciiKey);
        String expectedEncryptedHex = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272" +
                "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
        assertEquals(expectedEncryptedHex, actualEncryptedHex);
    }

    @Test
    void returnAsciiKeyICEICEIForStringLengthOfSeven() {
        String hexKey = repeatedKeyXorCipher.generateAsciiKeyForTextLength("ICE", 7);
        assertEquals("ICEICEI", hexKey);
    }
}