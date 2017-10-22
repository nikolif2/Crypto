import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.*;
import java.nio.charset.*;

class Main {
	


	public static void main(String[] args) {

		//The user enters a password
		System.out.println("Enter your password: ");
		Scanner scan = new Scanner(System.in);
		String password = scan.next();

		//Hash the password and salt
		//System.out.println(hash(password, generateSalt()));

	}

	public static byte [] hash(String password, byte [] salt) {

		//Password (p) and salt (s) concatenated 
		byte[] bytePassword = password.getBytes(Charset.forName("UTF-8"));

		byte[] saltPassword = new byte[bytePassword.length + salt.length];
		System.arraycopy(bytePassword, 0, saltPassword, 0, bytePassword.length);
		System.arraycopy(salt, 0, saltPassword, bytePassword.length, salt.length);
		
		// A java.security class - used as a message digest algorithm
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte [] hash = md.digest(saltPassword);

			for (int i = 0; i < 199; i++) {
				hash = md.digest(hash);
			}

			return hash;

		} catch (Exception NoSuchAlgorithmException) {}

		return null;
	}

	//Password - Strong Password stored in a text file
	public static void readPassword() throws IOException {  

		try {
				File file = new File("C:/Users/FILIP/Desktop/Crypto/password.txt");

				BufferedReader pass = new BufferedReader(
					new InputStreamReader(
					new FileInputStream(file), "UTF8"));

				String str;

				while ((str = pass.readLine()) != null) {
					System.out.println(str);
				}

				pass.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
    
	//Salt - Randomly Generated 128-bit value as an array of 16 bytes
	public static byte[] generateSalt() {
		
		Random random = new Random(); 
		byte[] salt = new byte[16];

		//Assign each byte a randomly generated value -128 to 127
		for (int i = 0; i < salt.length; i++) {
			int randNum = random.nextInt(255);
			salt [i] =  (byte)(randNum - 128);
			System.out.println(salt[i]);
		}
		return salt;
	}
}