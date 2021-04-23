package one;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DetectSingleCharacterXor {

    private DecryptSingleByteXorCipher decryptSingleByteXorCipher = new DecryptSingleByteXorCipher();
    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    /*
    Load up the file
    Place the file into an array of strings
    Return the array of strings
     */

    public static void main(String[] args) {
        DetectSingleCharacterXor detectSingleCharacterXor = new DetectSingleCharacterXor();
        Path pathToFile = Utilities.PATH_TO_RESOURCES.resolve(Paths.get("set_1_challenge_4.txt"));
        System.out.println(detectSingleCharacterXor.returnBestGuessDecryptedAsciiTestForFile(pathToFile));
        detectSingleCharacterXor.executorService.shutdown();
    }

    DecryptedText returnBestGuessDecryptedAsciiTestForFile(Path pathToFile) {
        List<String> encryptedStringsList = loadEncryptedTextFromFile(pathToFile);
        return returnBestGuessDecryptedAsciiForListOfStrings(encryptedStringsList);
    }

    DecryptedText returnBestGuessDecryptedAsciiForListOfStrings(List<String> listOfEncryptedText) {
        List<CompletableFuture<DecryptedText>> futuresDecryptedTexts = new ArrayList<>();

        listOfEncryptedText.forEach(text -> {
            futuresDecryptedTexts.add(CompletableFuture.supplyAsync(() -> decryptSingleByteXorCipher.decodesAsciiForEncodedHexForEachAsciiKeys(text), executorService));
        });

        List<DecryptedText> decryptedTexts = futuresDecryptedTexts.stream().map(CompletableFuture::join).collect(Collectors.toList());

        int score = 0;
        int index = 0;

        for(int i = 0; i < decryptedTexts.size(); i++) {
            DecryptedText decryptedText = decryptedTexts.get(i);
            int decryptedTextScore = decryptedText.getScore();
            if (decryptedTextScore > score) {
                score = decryptedTextScore;
                index = i;
            }
        }

        return decryptedTexts.get(index);
    }

    List<String> loadEncryptedTextFromFile(Path pathToFile) {
        List<String> encryptedHexStrings = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathToFile)) {
            String readString;
            while((readString = reader.readLine()) != null) {
                encryptedHexStrings.add(readString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encryptedHexStrings;
    }
}
