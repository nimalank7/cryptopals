package one;

public class NumberConverter {

    public static final String HEXADECIMAL = "0123456789abcdef";
    public static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static int binaryToDecimal(String binaryString) {
        String[] binaryStringArray = new StringBuilder(binaryString).reverse().toString().split("");
        int decimal = 0;
        for (int i = 0; i < binaryStringArray.length; i++) {
            var product = (int) Math.pow(2, i) * Integer.parseInt(binaryStringArray[i]);
            decimal += product;
        }

        return decimal;
    }

    static String hexadecimalDigitForIndex(int index) {
        String[] hexadecimalSystemArray = HEXADECIMAL.split("");
        return hexadecimalSystemArray[index];
    }

    static int decimalOfBinary(String binaryString) {
        String[] binaryDigitArray = new StringBuilder(binaryString).reverse().toString().split("");
        int decimalOfBinary = 0;
        for (int i = 0; i < binaryDigitArray.length; i++) {
            var productOfBinaryDigitWithPowerOfTwo = (int) Math.pow(2, i) * Integer.parseInt(binaryDigitArray[i]);
            decimalOfBinary += productOfBinaryDigitWithPowerOfTwo;
        }
        return decimalOfBinary;
    }
}
