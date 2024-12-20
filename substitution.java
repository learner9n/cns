import java.util.*;

public class Main {

    // Function to create substitution mappings
    private static void createMappings(String substitutionAlphabet, HashMap<Character, Character> encryptMap, HashMap<Character, Character> decryptMap) {
        String regularAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 26; i++) {
            encryptMap.put(regularAlphabet.charAt(i), substitutionAlphabet.charAt(i)); // Encryption map
            decryptMap.put(substitutionAlphabet.charAt(i), regularAlphabet.charAt(i)); // Decryption map
        }
    }

    // Function to encrypt the string
    public static String encrypt(String text, HashMap<Character, Character> encryptMap) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char upperChar = Character.toUpperCase(c);
                char mappedChar = encryptMap.get(upperChar);
                encrypted.append(Character.isLowerCase(c) ? Character.toLowerCase(mappedChar) : mappedChar);
            } else {
                encrypted.append(c); // Non-alphabet characters stay the same
            }
        }
        return encrypted.toString();
    }

    // Function to decrypt the string
    public static String decrypt(String text, HashMap<Character, Character> decryptMap) {
        StringBuilder decrypted = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char upperChar = Character.toUpperCase(c);
                char mappedChar = decryptMap.get(upperChar);
                decrypted.append(Character.isLowerCase(c) ? Character.toLowerCase(mappedChar) : mappedChar);
            } else {
                decrypted.append(c); // Non-alphabet characters stay the same
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter any String: ");
        String input = scanner.nextLine();

        System.out.print("Enter the Substitution Alphabet (26 unique uppercase letters): ");
        String substitutionAlphabet = scanner.nextLine().toUpperCase();

        // Validate substitution alphabet
        if (substitutionAlphabet.length() != 26 || !substitutionAlphabet.chars().allMatch(Character::isUpperCase)) {
            System.out.println("Error: Substitution alphabet must be 26 unique uppercase letters.");
            return;
        }

        HashMap<Character, Character> encryptMap = new HashMap<>();
        HashMap<Character, Character> decryptMap = new HashMap<>();
        createMappings(substitutionAlphabet, encryptMap, decryptMap);

        String encrypted = encrypt(input, encryptMap);
        System.out.println("Encrypted String is: " + encrypted);

        String decrypted = decrypt(encrypted, decryptMap);
        System.out.println("Decrypted String is: " + decrypted);

        scanner.close();
    }
}









Enter any String: hello world
Enter the Substitution Alphabet (26 unique uppercase letters): QWERTYUIOPLKJHGFDSAZXCVBNM
Encrypted String is: itkkg vgskr
Decrypted String is: hello world

  
