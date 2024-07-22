public class Encryption {
    private static final int[] initialPermutation = {
        58, 50, 42, 34, 26, 18, 10, 2, 
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17,  9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };
        
    private static final int[] inverseIP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27, 
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9,  49, 17, 57, 25
    };

    private static final int[] expansion = {
        32,  1,  2,  3,  4,  5,
         4,  5,  6,  7,  8,  9,
         8,  9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32,  1
    };

    private static final int[] permutation = {
        16,  7, 20, 21, 29, 12, 28, 17,
        1, 15, 23, 26,  5, 18, 31, 10,
        2,  8, 24, 14, 32, 27,  3,  9,
        19, 13, 30,  6, 22, 11,  4, 25
    };

    private static final int[] permutedChoiceOne = {
        57, 49, 41, 33, 25, 17,  9,
         1, 58, 50, 42, 34, 26, 18,
        10,  2, 59, 51, 43, 35, 27,
        19, 11,  3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
         7, 62, 54, 46, 38, 30, 22,
        14,  6, 61, 53, 45, 37, 29,
        21, 13,  5, 28, 20, 12,  4
    };

    private static final int[] permutedChoiceTwo = {
        14, 17, 11, 24,  1,  5,  3, 28,
        15,  6, 21, 10, 23, 19, 12,  4,
        26,  8, 16,  7, 27, 20, 13,  2,
        41, 52, 31, 37, 47, 55, 30, 40,
        51, 45, 33, 48, 44, 49, 39, 56, 
        34, 53, 46, 42, 50, 36, 29, 32
    };

    private static final int[] sBox1 = {
        14,  4, 13, 1,  2, 15, 11,  8,  3, 10,  6, 12, 5,  9, 0, 7,
         0, 15,  7, 4, 14,  2, 13,  1, 10,  6, 12, 11, 9,  5, 3, 8,
         4,  1, 14, 8, 13,  6,  2, 11, 15, 12,  9,  7, 3, 10, 5, 0,
        15, 12,  8, 2,  4,  9,  1,  7,  5, 11,  3, 14, 10, 0, 6, 13
    };

    private static final int[] sBox2 = {
        15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
        3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
        0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
        13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
    };

    private static final int[] sBox3 = {
        10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
        13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
        13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
        1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
    };

    private static final int[] sBox4 = {
        7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
        13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
        10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
        3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
    };

    private static final int[] sBox5 = {
        2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
        14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
        4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
        11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
    };

    private static final int[] sBox6 = {
        12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
        10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
        9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
        4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
    };

    private static final int[] sBox7 = {
        4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
        13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
        1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
        6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
    };

    private static final int[] sBox8 = {
        13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
        1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
        7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
        2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
    };

    private static final int[][] sBoxes = {
        sBox1, sBox2, sBox3, sBox4, sBox5, sBox6, sBox7, sBox8
    };

    private static final int[] leftRotate = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };


    public static long charsToLong(String block) {
        long result = 0L;
        for(int i = 0; i < 4; i++) {
            result = result << 16;
            result = result | block.charAt(i);
        }
        // System.out.println(Long.toBinaryString(result));
        return result;
    }

    private static long initialPermutation(long plaintext) {
        long result = 0L;
        for(int i = 0; i < 64; i++) {
            result = result << 1;
            result |= (plaintext >> (64 - initialPermutation[i])) & 1;
            // System.out.println("i: " + Long.toBinaryString(result));
        }
        // System.out.println(Long.toBinaryString(result));
        return result;
    }

    private static long[] splitPlaintext(long permutedPlaintext) {
        long mask = 0x00000000FFFFFFFFL;
        long leftHalf = permutedPlaintext >> 32;
        long rightHalf = mask & permutedPlaintext;
        long[] result = {leftHalf, rightHalf};
        // System.out.println(Long.toBinaryString(leftHalf));
        // System.out.println(Long.toBinaryString(rightHalf));
        return result;
    }

    private static long permutedChoiceOne(long key) {
        long result = 0L;
        for(int i = 0; i < 56; i++) {
            result = result << 1;
            result |= (key >> (64 - permutedChoiceOne[i])) & 1;
        }
        // System.out.println(Long.toBinaryString(result));
        return result;
    }

    private static long rotateKey(long key, int round) {
        long temp = 0x00000000FFFFFFFFL;
        long leftHalf = key >> 32;
        long rightHalf = temp & key;
        if(leftRotate[round] == 1) {
            leftHalf = oneBitLeftRotate(leftHalf);
            rightHalf = oneBitLeftRotate(rightHalf);
        }
        else {
            leftHalf = twoBitsLeftRotate(leftHalf);
            rightHalf = twoBitsLeftRotate(rightHalf);
        }
        long result = (leftHalf << 32) | (rightHalf & temp);
        return result;

    }

    private static long oneBitLeftRotate(long key) {
        key = key << 1;
        long check = 0b100000000000000000000000000000000L;
        long temp = key & check;
        temp = temp >> 31;
        key = key | temp;
        key = key & 0b11111111111111111111111111111111;
        return key;
    }

    private static long twoBitsLeftRotate(long key) {
        key = key << 2;
        long check = 0b1100000000000000000000000000000000L;
        long temp = key & check;
        temp = temp >> 31;
        key = key | temp;
        key = key & 0b11111111111111111111111111111111;
        return key;
    }

    private static long permutedChoiceTwo(long key) {
        long result = 0L;
        for(int i = 0; i < 48; i++) {
            result = result << 1;
            result |= (key >> (64 - permutedChoiceTwo[i])) & 1;
        }
        // System.out.println(Long.toBinaryString(result));
        return result;
    }

    public static long encrypt(long plaintext, long key) {
        long result = 0L;
        long permutedPlaintext = initialPermutation(plaintext);
        long[] splittedPlaintext = splitPlaintext(permutedPlaintext);
        long leftHalf = splittedPlaintext[0];
        long rightHalf = splittedPlaintext[1];
        long permutedKey = permutedChoiceOne(key);
        long rotatedKey = rotateKey(permutedKey, 0);
        long permutedKey2 = permutedChoiceTwo(rotatedKey);
        return result;
    }

    public static void main(String[] args) {
        long step1 = 163971057783956512L;
        long step2 = initialPermutation(step1);
        long[] splittedPlaintext = splitPlaintext(step2);
        long key = 0x0f157c947d9e859L;
        long permutedKey = permutedChoiceOne(key);
        long rotatedKey = rotateKey(permutedKey, 0);
        long permutedKey2 = permutedChoiceTwo(rotatedKey);
        System.out.println(Long.toBinaryString(permutedKey2));
    }
}