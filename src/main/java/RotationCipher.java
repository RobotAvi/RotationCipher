public class RotationCipher {
    private static final int NUMBERS_ASCII_START = 48;
    private static final int NUMBERS_ASCII_END = 57;
    private static final int CAPITAL_LETTERS_ASCII_START = 65;
    private static final int CAPITAL_LETTERS_ASCII_END = 90;
    private static final int LETTERS_ASCII_START = 97;
    private static final int LETTERS_ASCII_END = 122;

    static String rotationalCipher(String input, int rotationFactor) {

        StringBuilder output = new StringBuilder();
        for (int charNumber = 0; charNumber < input.length(); charNumber++) {
            int asciiCode = input.charAt(charNumber);

            int segmentAddr = asciiCode;
            int segmentLength = 1;
            int pointer;
            int shift = 0;

            if (asciiCode >= NUMBERS_ASCII_START && asciiCode <= NUMBERS_ASCII_END) {
                segmentAddr = NUMBERS_ASCII_START;
                segmentLength = NUMBERS_ASCII_END - NUMBERS_ASCII_START + 1;
                shift = rotationFactor;
            }
            if (asciiCode >= LETTERS_ASCII_START && asciiCode <= LETTERS_ASCII_END) {
                segmentAddr = LETTERS_ASCII_START;
                segmentLength = LETTERS_ASCII_END - LETTERS_ASCII_START + 1;
                shift = rotationFactor;
            }

            if (asciiCode >= CAPITAL_LETTERS_ASCII_START && asciiCode <= CAPITAL_LETTERS_ASCII_END) {
                segmentAddr = CAPITAL_LETTERS_ASCII_START;
                segmentLength = CAPITAL_LETTERS_ASCII_END - CAPITAL_LETTERS_ASCII_START + 1;
                shift = rotationFactor;
            }


            pointer = asciiCode - (segmentAddr - 1);

            int newPointer = getPositionInCycleArray(segmentLength, pointer, shift);
            char resultChar = (char) ((segmentAddr - 1) + newPointer);

            output.append(resultChar);
        }

        return output.toString();
    }

    static int getPositionInCycleArray(int arrayLength, int pointer, int shift) {
        if (arrayLength == 0) return 0;
        if (pointer <= 0) return 0;
        if ((pointer + shift) % arrayLength == 0) return arrayLength;
        if (pointer + shift > arrayLength) return (pointer + shift) % arrayLength;
        return pointer + shift;
    }

}
