package one;

public class DecryptedText {

    private int key;
    private int score;
    private String decryptedAscii;
    private String encodedHex;

    public DecryptedText(int key, int score, String decryptedAscii, String encodedHex) {
        this.key = key;
        this.score = score;
        this.decryptedAscii = decryptedAscii;
        this.encodedHex = encodedHex;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDecryptedAscii() {
        return decryptedAscii;
    }

    public void setDecryptedAscii(String decryptedAscii) {
        this.decryptedAscii = decryptedAscii;
    }

    public String getEncodedHex() {
        return encodedHex;
    }

    public void setEncodedHex(String encodedHex) {
        this.encodedHex = encodedHex;
    }

    @Override
    public String toString() {
        return "DecryptedText{" +
                "key=" + key +
                ", score=" + score +
                ", decryptedAscii='" + decryptedAscii + '\'' +
                ", encodedHex='" + encodedHex + '\'' +
                '}';
    }
}
