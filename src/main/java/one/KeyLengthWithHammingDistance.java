package one;

public class KeyLengthWithHammingDistance implements Comparable<KeyLengthWithHammingDistance> {

    int keyLength;
    float normalizedAverageHammingDistance;

    public KeyLengthWithHammingDistance(int keyLength, float normalizedAverageHammingDistance) {
        this.keyLength = keyLength;
        this.normalizedAverageHammingDistance = normalizedAverageHammingDistance;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public float getNormalizedAverageHammingDistance() {
        return normalizedAverageHammingDistance;
    }

    @Override
    public String toString() {
        return "KeyLengthWithHammingDistance{" +
                "keyLength=" + keyLength +
                ", normalizedAverageHammingDistance=" + normalizedAverageHammingDistance +
                '}';
    }

    @Override
    public int compareTo(KeyLengthWithHammingDistance o) {
        return Float.compare(getNormalizedAverageHammingDistance(), o.getNormalizedAverageHammingDistance());
    }
}
