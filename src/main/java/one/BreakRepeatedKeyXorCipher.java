package one;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BreakRepeatedKeyXorCipher {

    private DetectSingleCharacterXor detectSingleCharacterXor = new DetectSingleCharacterXor();
    private int MIN_KEY_SIZE = 2;
    private int MAX_KEY_SIZE = 40;

    /*
    Transform string into bytes
    Iterate keystring size from 2 to 4 (bytes)
    Divide up the transformed bytes into chunks of 2
    Calculate the normalized hamming distance and find the average
    Store the average in a list outside the 2 to 4 iteration
    Return the list of hamming distances
     */

    public static void main(String[] args) {
        Path pathToFile = Utilities.PATH_TO_RESOURCES.resolve(Paths.get("set_1_challenge_6.txt"));
        BreakRepeatedKeyXorCipher breakRepeatedKeyXorCipher = new BreakRepeatedKeyXorCipher();
        List<String> listOfBase64Strings = breakRepeatedKeyXorCipher.loadEncryptedTextFromFile(pathToFile);
        String completeBase64String = breakRepeatedKeyXorCipher.convertListOfStringToString(listOfBase64Strings);
        byte[] bytesBase64 = breakRepeatedKeyXorCipher.convertBase64ToBytes(completeBase64String);
        List<KeyLengthWithHammingDistance> listOfKeyLenghts = breakRepeatedKeyXorCipher.returnListOfHammingDistanceAndKeyLength(bytesBase64);
        System.out.println(listOfKeyLenghts);
    }

    List<KeyLengthWithHammingDistance> returnListOfHammingDistanceAndKeyLength(byte[] cipherTextBytes) {
        List<KeyLengthWithHammingDistance> listOfKeyLengths = new ArrayList<>();

        for(int i = 2; i < 41; i++) {
            float normalizedAverageHammingDistance = calculateNormalizedAverageHammingDistanceForKeySize(cipherTextBytes, i);
            listOfKeyLengths.add(new KeyLengthWithHammingDistance(i, normalizedAverageHammingDistance));
        }

        return listOfKeyLengths;
    }

    float calculateNormalizedAverageHammingDistanceForKeySize(byte[] cipherChunkBytes, int keySize) {

        /*
        Multiply the keysize * 2 to give us the total number of 1st and 2nd chunks
        E.g. if keysize is 2 then we need 4 bytes to do a proper hamming distance - 1 chunk = 2 and 2 chunk = 2
        So the iteration limit - i.e. < iteration limit = total number of 1st and 2nd chunks

         */

        // Revisit this code - return an object containing the average and the normalized for comparison

        int doubleKeySize = 2 * keySize;
        int numberOfDoubleKeysizeChunks = cipherChunkBytes.length/(doubleKeySize);
        int totalHammingDistance = 0;

        for (int i = 0; i + doubleKeySize <= (numberOfDoubleKeysizeChunks * doubleKeySize); i += doubleKeySize) {
            byte[] cipherChunkOne = Arrays.copyOfRange(cipherChunkBytes, i, i + keySize);
            byte[] cipherChunkTwo = Arrays.copyOfRange(cipherChunkBytes, i + keySize, i + doubleKeySize);
            int hammingDistance = calculateHammingDistanceForStrings(cipherChunkOne, cipherChunkTwo);
            totalHammingDistance += hammingDistance;
        }

        float averageHammingDistance = totalHammingDistance/(float) numberOfDoubleKeysizeChunks;
        float normalizedAverageHammingDistance = averageHammingDistance/keySize;

        return normalizedAverageHammingDistance;
    }

    int calculateHammingDistanceForStrings(byte[] stringOneBytes, byte[] stringTwoBytes) {
        int totalDifferentBits = 0;

        for (int i = 0; i < stringOneBytes.length; i++) {
            int xorOfByte = stringOneBytes[i] ^ stringTwoBytes[i];
            totalDifferentBits += Integer.toBinaryString(xorOfByte).replace("0", "").length();
        }

        return totalDifferentBits;
    }

    String convertListOfStringToString(List<String> listOfStrings) {
        StringBuilder completeString = new StringBuilder();
        listOfStrings.stream().forEach(string -> completeString.append(string));
        return completeString.toString();
    }

    List<String> convertAllBase64StringsToHex(List<String> encryptedBase64Strings) {
        return encryptedBase64Strings.stream().map(base64String -> convertBase64StringToHex(base64String)).collect(Collectors.toList());
    }

    List<String> loadEncryptedTextFromFile(Path pathToFile) {
        return detectSingleCharacterXor.loadEncryptedTextFromFile(pathToFile);
    }

    byte[] convertBase64ToBytes(String base64String) {
        return Base64.decodeBase64(base64String);
    }

    String convertBase64StringToHex(String base64String) {
        byte[] bytesOfBase64String = Base64.decodeBase64(base64String);
        String hexString = Hex.encodeHexString(bytesOfBase64String);
        return hexString;
    }
}
