public class Des {

        // int[] key =  {
        //     0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,1,0,0,1,0,1,0,0,0,1,1,1,1,1,0,1,1,0,0,1,1,1,1,0,1,0,0,0,0,1,0,1,1,0,0,1
        // };

        // int[] plaintext = {
        //     0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0,1,0,0,0,1,0,1,0,1,1,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,0,1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,0,0
        // };

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
            32, 1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9,
            8,  9,  10, 11, 12, 13,
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

        private static final int[][] sBox1 = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        };


        private int[] initialPermutation(int[] plaintext) {
            int[] permutedPlaintext = new int[64];
            for(int i = 0; i < 64; i++) {
                permutedPlaintext[i] = plaintext[initialPermutation[i]-1];
            }
            return permutedPlaintext;
        }

        private int[][] splitHalves(int[] permutedPlaintext) {
            int[] leftHalf = new int[32];
            int[] rightHalf = new int[32];
            for (int i = 0; i < 32; i++) {
                leftHalf[i] = permutedPlaintext[i];
                rightHalf[i] = permutedPlaintext[i+32];
            }
            int[][] result = {leftHalf, rightHalf};
            return result;
        }

        private int[] expandRightHalf(int[] rightHalf) {
            int[] expandedRightHalf = new int[48];
            for(int i = 0; i < 48; i++) {
                expandedRightHalf[i] = rightHalf[expansion[i]-1];
            }
            return expandedRightHalf;
        }

        private int[] permutedChoiceOne(int[] key) {
            int[] permutedKey = new int[56];
            for(int i = 0; i < 56; i++) {
                permutedKey[i] = key[permutedChoiceOne[i]-1];
            }
            return permutedKey;
        }

        private int[] permutedChoiceTwo(int[] key) {
            int[] permutedKey = new int[48];
            for(int i = 0; i < 48; i++) {
                permutedKey[i] = key[permutedChoiceTwo[i]-1];
            }
            return permutedKey;
        }

        private int[] xor(int[] rightHalf, int[] key) {
            int[] result = new int[48];
            for(int i = 0; i < 48; i++) {
                result[i] = rightHalf[i] ^ key[i];
            }
            return result;
        }


        // int[] sBoxResult = new int[8];
        // for (int i = 0; i < 8; i++) {
        //     int startIndex = i * 6;

        //     int firstBit = xorResult[startIndex];
        //     int lastBit = xorResult[startIndex + 5];
        //     int row = (firstBit << 1) | lastBit;

        //     int column = 0;
        //     for (int j = 0; j < 4; j++) {
        //         column = (column << 1) | xorResult[startIndex + 1 + j];
        //     }
        //     sBoxResult[i] = sBox1[row][column];
        // }

        // int[] binaryResult = new int[32];
        // int bitIndex = 0;
        
        // for (int i = 0; i < 8; i++) {
        //     int value = sBoxResult[i];
        //     for (int j = 3; j >= 0; j--) {
        //         binaryResult[bitIndex++] = (value >> j) & 1;
        //     }
        // }

        // int[] newRightHalf = new int[32];
        // for(int i = 0; i < 32; i++) {
        //     newRightHalf[i] = binaryResult[i] ^ leftHalf[i];
        // }

        // for(int i = 0; i < 32; i++) {
        //     System.out.print(newRightHalf[i]);
        // }
}
