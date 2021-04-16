package set_one;

import java.math.BigDecimal;

public class HexToBase64Converter {

    String hexadecimal = "0123456789abcdef";
    String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /*
    Hexadecimal is base 8 (0, 1, 2, 3, 4, 5, 6, 7, 8)
    base64 is base 64 (0, 1, 2, 3...64)

    Step 1: Hexadecimal -> Decimal
    Step 2: Decimal -> base64 (decimal to base64)

     */

    public String convertHexToBase64(String hex) {
        BigDecimal decimalRepOfHex = hexToDecimal(hex);
        String base64 = decimalToBase64(decimalRepOfHex);

        return base64;
    }

    public BigDecimal hexToDecimal(String hex) {
        String[] reversedHexArray = new StringBuilder(hex).reverse().toString().split("");

        BigDecimal decimalDoubleValue = new BigDecimal(0);
        BigDecimal sixteen = new BigDecimal(16);

        for(int i = 0; i < reversedHexArray.length; i++ ) {
            BigDecimal powerOfSixteen = sixteen.pow(i);
            BigDecimal decimalIndexForHex = new BigDecimal(returnDecimalIndexForHexDigit(reversedHexArray[i]));
            BigDecimal decimalValueOfHexDigit = powerOfSixteen.multiply(decimalIndexForHex);
            decimalDoubleValue = decimalDoubleValue.add(decimalValueOfHexDigit);
        }

        return decimalDoubleValue;
    }

    public String decimalToBase64(BigDecimal decimalRepOfHex) {
        BigDecimal quotient = decimalRepOfHex;
        BigDecimal sixtyFour = new BigDecimal(64);
        String base64Value = "";

        while (quotient.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal remainder = quotient.remainder(sixtyFour);
            String base64DigitForDecimalIndex = returnBase64DigitForDecimalIndex(remainder);
            quotient = quotient.divideToIntegralValue(sixtyFour);

            base64Value = base64DigitForDecimalIndex + base64Value;
        }
        return base64Value;
    }

    public int returnDecimalIndexForHexDigit(String hexDigit) {
        int hexDigitInt = new StringBuilder(hexadecimal).indexOf(hexDigit);
        return hexDigitInt;
    }

    public String returnBase64DigitForDecimalIndex(BigDecimal base64DigitInt) {
        char base64Digit = new StringBuilder(base64).charAt(base64DigitInt.intValue());
        return Character.toString(base64Digit);
    }
}
