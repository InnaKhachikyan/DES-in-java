public class Des {
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
        14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
        0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
        4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
        15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
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

    private static int[] initialPermutation(int[] plaintext) {
        int[] permutedPlaintext = new int[64];
        for(int i = 0; i < 64; i++) {
            permutedPlaintext[i] = plaintext[initialPermutation[i]-1];
        }
        return permutedPlaintext;
    }

    private static int[][] splitPlaintext(int[] permutedPlaintext) {
        int[] leftHalf = new int[32];
        int[] rightHalf = new int[32];
        for (int i = 0; i < 32; i++) {
            leftHalf[i] = permutedPlaintext[i];
            rightHalf[i] = permutedPlaintext[i+32];
        }
        int[][] result = {leftHalf, rightHalf};
        return result;
    }

    private static int[] expandRightHalf(int[] rightHalf) {
        int[] expandedRightHalf = new int[48];
        for(int i = 0; i < 48; i++) {
            expandedRightHalf[i] = rightHalf[expansion[i]-1];
        }
        return expandedRightHalf;
    }

    private static int[] permutedChoiceOne(int[] key) {
        int[] permutedKey = new int[56];
        for(int i = 0; i < 56; i++) {
            permutedKey[i] = key[permutedChoiceOne[i]-1];
        }
        return permutedKey;
    }

    private static int[] permutedChoiceTwo(int[] key) {
        int[] permutedKey = new int[48];
        for(int i = 0; i < 48; i++) {
            permutedKey[i] = key[permutedChoiceTwo[i]-1];
        }
        return permutedKey;
    }

    private static int[] leftRotateKey(int[] key, int round) {
        //rotate left 28 and right 28 bits
        int[] leftHalf = new int[28];
        int[] rightHalf = new int[28]; 
        for (int i = 0; i < 28; i++) {
            leftHalf[i] = key[i];
            rightHalf[i] = key[i+28];
        }
        int[] rotatedLeftHalf = new int[28];
        int[] rotatedRightHalf = new int[28];
        int rotate = leftRotate[round];
        for (int i = 0; i < 28; i++) {
            rotatedLeftHalf[i] = leftHalf[(i+rotate)%28];
            rotatedRightHalf[i] = rightHalf[(i+rotate)%28];
        }
        int[] rotatedKey = new int[56];
        for (int i = 0; i < 28; i++) {
            rotatedKey[i] = rotatedLeftHalf[i];
            rotatedKey[i+28] = rotatedRightHalf[i];
        }
        return rotatedKey;
    }

    private static int[] xor(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] ^ b[i];
        }
        return result;
    }

    private static int[] sBox(int[] input) {
        int[] output = new int[32];
        for (int i = 0; i < 8; i++) {
            int[] sBoxInput = new int[6];
            int[] sBoxOutput = new int[4];
            for (int j = 0; j < 6; j++) {
                sBoxInput[j] = input[i*6+j];
            }
            int row = sBoxInput[0]*2 + sBoxInput[5];
            int col = sBoxInput[1]*8 + sBoxInput[2]*4 + sBoxInput[3]*2 + sBoxInput[4];
            int value = sBoxes[i][row*16+col];
            for (int j = 0; j < 4; j++) {
                sBoxOutput[j] = (value >> (3-j)) & 1;
            }
            for (int j = 0; j < 4; j++) {
                output[i*4+j] = sBoxOutput[j];
            }
        }
        return output;
    }

    private static int[] permutation(int[] input) {
        int[] output = new int[32];
        for (int i = 0; i < 32; i++) {
            output[i] = input[permutation[i]-1];
        }
        return output;
    }

    private static int[] inverseIP(int[] input) {
        int[] output = new int[64];
        for (int i = 0; i < 64; i++) {
            output[i] = input[inverseIP[i]-1];
        }
        return output;
    }

    public static int[] des(int[] key, int[] plaintext) {
        int[] permutedPlaintext = initialPermutation(plaintext);
        int[] permutedKey = permutedChoiceOne(key);
        for(int i = 0; i < 16; i++) {
            int[][] splitPlaintext = splitPlaintext(permutedPlaintext);
            int[] leftHalf = splitPlaintext[0];
            int[] rightHalf = splitPlaintext[1];
            int[] rotatedKey = leftRotateKey(permutedKey, i);
            int[] permutedKey2 = permutedChoiceTwo(rotatedKey);
            int[] expandedRightHalf = expandRightHalf(rightHalf);
            int[] xorResult = xor(expandedRightHalf, permutedKey2);
            int[] sBoxResult = sBox(xorResult);
            int[] permutedSboxResult = permutation(sBoxResult);
            int[] newRightHalf = xor(leftHalf, permutedSboxResult);
            int[] newPlaintext = new int[64];
            for (int j = 0; j < 32; j++) {
                newPlaintext[j] = rightHalf[j];
                newPlaintext[j+32] = newRightHalf[j];
            }
            permutedPlaintext = newPlaintext;
            permutedKey = rotatedKey;
        }
        // swap 32 bits
        int[] swappedPlaintext = new int[64];
        for (int i = 0; i < 32; i++) {
            swappedPlaintext[i] = permutedPlaintext[i+32];
            swappedPlaintext[i+32] = permutedPlaintext[i];
        }
        return inverseIP(swappedPlaintext);
    }

    public static void main(String[] args) {
        int[] plaintext = {
            0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0,1,0,0,0,1,0,1,0,1,1,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,0,1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,0,0
        };

        int[] key = {
            0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,1,0,0,1,0,1,0,0,0,1,1,1,1,1,0,1,1,0,0,1,1,1,1,0,1,0,0,0,0,1,0,1,1,0,0,1
        };

        int[] result = des(key, plaintext);
        for (int i = 0; i < 64; i++) {
            System.out.print(result[i]);
        }
    }
}


