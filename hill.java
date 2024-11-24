import java.util.Scanner;

public class HillCipher {

    // Helper function to compute modular inverse of a number mod 26
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1; // Shouldn't reach here if key matrix is valid
    }

    // Encrypt function
    public static String encrypt(String text, int[][] key) {
        int n = key.length;
        text = text.toUpperCase();
        while (text.length() % n != 0) {
            text += "X"; // Padding with 'X'
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = text.charAt(i + j) - 'A';
            }

            int[] result = new int[n];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    result[row] += key[row][col] * block[col];
                }
                result[row] %= 26;
            }

            for (int value : result) {
                encryptedText.append((char) (value + 'A'));
            }
        }

        return encryptedText.toString();
    }

    // Decrypt function
    public static String decrypt(String text, int[][] key) {
        int n = key.length;
        int det = determinant(key, n);
        int detInv = modInverse(det % 26 + 26, 26); // Modular inverse of determinant

        int[][] adj = adjoint(key);
        int[][] inverse = new int[n][n];

        // Compute inverse matrix modulo 26
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = (adj[i][j] * detInv) % 26;
                if (inverse[i][j] < 0) {
                    inverse[i][j] += 26; // Ensure positive modulo
                }
            }
        }

        // Print the inverse matrix
        System.out.println("Inverse Matrix is:");
        for (int[] row : inverse) {
            for (int value : row) {
                System.out.print(value / 26.0 + " "); // Printing normalized values
            }
            System.out.println();
        }

        return encrypt(text, inverse); // Decrypt by reusing encryption logic with inverse matrix
    }

    // Helper function to calculate determinant of a matrix
    private static int determinant(int[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }

        int det = 0;
        int[][] temp = new int[n][n];

        for (int p = 0; p < n; p++) {
            int subi = 0;
            for (int i = 1; i < n; i++) {
                int subj = 0;
                for (int j = 0; j < n; j++) {
                    if (j == p) continue;
                    temp[subi][subj++] = matrix[i][j];
                }
                subi++;
            }
            det += Math.pow(-1, p) * matrix[0][p] * determinant(temp, n - 1);
        }

        return det;
    }

    // Helper function to calculate adjoint of a matrix
    private static int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] adj = new int[n][n];

        if (n == 1) {
            adj[0][0] = 1;
            return adj;
        }

        int sign = 1;
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int subi = 0;
                for (int row = 0; row < n; row++) {
                    if (row == i) continue;
                    int subj = 0;
                    for (int col = 0; col < n; col++) {
                        if (col == j) continue;
                        temp[subi][subj++] = matrix[row][col];
                    }
                    subi++;
                }
                adj[j][i] = (sign * determinant(temp, n - 1));
                sign = -sign;
            }
        }

        return adj;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a 3-letter string: ");
        String input = scanner.nextLine();

        // Hardcoded 3x3 key matrix
        int[][] key = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        String encrypted = encrypt(input, key);
        System.out.println("Encrypted string is: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted string is: " + decrypted);

        scanner.close();
    }
}





Enter a 3-letter string: hai
Encrypted string is: YPA
Inverse Matrix is:
0.3076923076923077 0.19230769230769232 0.38461538461538464 
0.8076923076923077 0.3076923076923077 0.8076923076923077 
0.8076923076923077 0.46153846153846156 0.3076923076923077 
Decrypted string is: HAI
