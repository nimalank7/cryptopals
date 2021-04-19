package one;

public class DecryptedText {

    private int key;
    private int score;
    private String decryptedAscii;

    public DecryptedText(int key, int score, String decryptedAscii) {
        this.key = key;
        this.score = score;
        this.decryptedAscii = decryptedAscii;
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

    @Override
    public String toString() {
        return "DecryptedText{" +
                "key=" + key +
                ", score=" + score +
                ", decryptedAscii='" + decryptedAscii + '\'' +
                '}';
    }
}
