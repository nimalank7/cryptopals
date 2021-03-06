package one;

import java.math.BigDecimal;

public class HexToBase64Converter {

    /*
    Hexadecimal is base 8 (0, 1, 2, 3, 4, 5, 6, 7, 8)
    base64 is base 64 (0, 1, 2, 3...64)

    Step 1: Hexadecimal -> Decimal
    Step 2: Decimal -> base64 (decimal to base64)
     */

    /*
    I originally tried to use a double but it kept on displaying it as standard form
     */

    String convertHexToBase64(String hex) {
        BigDecimal decimalRepOfHex = hexStringToDecimalString(hex);
        String base64 = decimalToBase64(decimalRepOfHex);

        return base64;
    }

    BigDecimal hexStringToDecimalString(String hex) {
        String[] reversedHexArray = new StringBuilder(hex).reverse().toString().split("");

        BigDecimal decimalDoubleValue = new BigDecimal(0);
        BigDecimal sixteen = new BigDecimal(16);

        for(int i = 0; i < reversedHexArray.length; i++ ) {
            BigDecimal powerOfSixteen = sixteen.pow(i);
            BigDecimal decimalIndexForHex = new BigDecimal(Utilities.hexToDecimal(reversedHexArray[i]));
            BigDecimal decimalValueOfHexDigit = powerOfSixteen.multiply(decimalIndexForHex);
            decimalDoubleValue = decimalDoubleValue.add(decimalValueOfHexDigit);
        }

        return decimalDoubleValue;
    }

    String decimalToBase64(BigDecimal decimalRepOfHex) {
        BigDecimal quotient = decimalRepOfHex;
        BigDecimal sixtyFour = new BigDecimal(64);
        String base64Value = "";

        while (quotient.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal remainder = quotient.remainder(sixtyFour);
            String base64DigitForDecimalIndex = Utilities.returnBase64DigitForDecimalIndex(remainder);
            quotient = quotient.divideToIntegralValue(sixtyFour);

            base64Value = base64DigitForDecimalIndex + base64Value;
        }
        return base64Value;
    }
}
