import java.util.*;
class Main {
    // Function to encrypt the string
    public static String encrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                encrypted.append((char) ((c - 'A' + key) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                encrypted.append((char) ((c - 'a' + key) % 26 + 'a'));
            } else {
                encrypted.append(c); // Keep non-alphabet characters as is
            }
        }
        return encrypted.toString();
    }

    // Function to decrypt the string
    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - key); // Decrypt by reversing the shift
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter any String: ");
        String input = scanner.nextLine();

        System.out.print("Enter the Key: ");
        int key = scanner.nextInt();

        String encrypted = encrypt(input, key);
        System.out.println("Encrypted String is: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted String is: " + decrypted);
    }
}
