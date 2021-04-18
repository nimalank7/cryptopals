package one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberConvertersTest {

    @Test
    void returnDecimal3ForBinaryEquivalent() {
        assertEquals(3, NumberConverter.binaryToDecimal("11"));
    }

    @Test
    void returnDecimal0ForBinaryEquivalent() {
        assertEquals(0, NumberConverter.binaryToDecimal("0000"));
    }



}