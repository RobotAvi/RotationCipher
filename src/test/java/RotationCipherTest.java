import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class RotationCipherTest {
    @Test
    void testZebra() {
        String input = "Zebra-493?";
        int rotationFactor = 3;
        String output = "Cheud-726?";
        assertEquals(output, RotationCipher.rotationalCipher(input, rotationFactor));
    }

    @Test
    void testAlphabetWithNumbers() {
        String input = "abcdefghijklmNOPQRSTUVWXYZ0123456789";
        int rotationFactor = 39;
        String output = "nopqrstuvwxyzABCDEFGHIJKLM9012345678";
        assertEquals(output, RotationCipher.rotationalCipher(input, rotationFactor));

    }
    @Test
    void test_m() {
        String input = "m";
        int rotationFactor = 39;
        String output = "z";
        assertEquals(output, RotationCipher.rotationalCipher(input, rotationFactor));

    }

    @Test
    void getPositionInCycleArrayTest() {
        int[] generalArray = new int[100];
        int subArrayLength = 5;

        int subArrayMaxAddr = generalArray.length - subArrayLength - 1;

        int j = 1;
        for (int i = 0; i < generalArray.length; i++) {
            generalArray[i] = j;
            j++;
            if (j > subArrayLength) j = 1;
        }

        for (int subArrayStartAddr = 0; subArrayStartAddr <= subArrayMaxAddr; subArrayStartAddr++) {
            int pointer = ThreadLocalRandom.current().nextInt(1, subArrayLength);
            int maxShift = (generalArray.length - 1) - (subArrayStartAddr + pointer);
            int shift = ThreadLocalRandom.current().nextInt(0, maxShift);
            int expected = generalArray[subArrayStartAddr + pointer + shift];

            int resultPointer = RotationCipher.getPositionInCycleArray(subArrayLength, pointer, shift);
            int result = generalArray[subArrayStartAddr + resultPointer];

            assertEquals(expected, result);
        }
    }

    @Test
    void getPositionInCycleArrayTest_borders() {
        assertEquals(0, RotationCipher.getPositionInCycleArray(0, 0, 0));
        assertEquals(0, RotationCipher.getPositionInCycleArray(0, 1, 0));
        assertEquals(1, RotationCipher.getPositionInCycleArray(100, 1, 0));
        assertEquals(0, RotationCipher.getPositionInCycleArray(-1, -1, -1));
        assertEquals(100, RotationCipher.getPositionInCycleArray(100, 50, 150));

    }
}
