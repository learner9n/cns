import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DESEncryptionExample {

    public static void main(String[] argv) {
        try {
            System.out.println("Message Encryption using DES Algorithm\n----");

            // Generate the DES key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keyGenerator.generateKey();

            // Create Cipher object
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize cipher in encrypt mode
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

            // Input message
            byte[] text = "we are going to war tomorrow".getBytes();

            // Print the original message
            System.out.println("Message [Byte Format]: " + text);
            System.out.println("Message: " + new String(text));

            // Encrypt the text
            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.println("Encrypted Message: " + textEncrypted);

            // Initialize cipher in decrypt mode
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

            // Decrypt the text
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);
            System.out.println("Decrypted Message: " + new String(textDecrypted));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
