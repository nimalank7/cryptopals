package one;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreakRepeatedKeyXorCipherTest {
    BreakRepeatedKeyXorCipher breakRepeatedKeyXorCipher = new BreakRepeatedKeyXorCipher();

    @Test
    void returnAverageNormalizedHammingDistanceForBytes() {
        byte[] string = {29, 66, 31, 77};
        float normalizedAverageHammingDistance = breakRepeatedKeyXorCipher.calculateNormalizedAverageHammingDistanceForKeySize(string, 2);
        assertEquals(2.5, normalizedAverageHammingDistance);
    }

    @Test
    void returnHammingDistance37ForBothStrings() {
        String one = "this is a test";
        String two = "wokka wokka!!!";
        assertEquals(37, breakRepeatedKeyXorCipher.calculateHammingDistanceForStrings(one.getBytes(), two.getBytes()));
    }

    @Test
    void returnHexStringForBase64() {
        String base64String = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
        String expectedHexString = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        assertEquals(expectedHexString, breakRepeatedKeyXorCipher.convertBase64StringToHex(base64String));
    }

    @Test
    void returnListOfConvertedHexStringsForBase64() {
        String base64StringOne = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
        String base64StringTwo = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
        String expectedHexStringOne = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String expectedHexStringTwo = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        List<String> listOfBase64Strings = List.of(base64StringOne, base64StringTwo);
        List<String> listOfConvertedHexStrings = List.of(expectedHexStringOne, expectedHexStringTwo);
        assertEquals(listOfConvertedHexStrings, breakRepeatedKeyXorCipher.convertAllBase64StringsToHex(listOfBase64Strings));
    }
}