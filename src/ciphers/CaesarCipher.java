package ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class CaesarCipher {
	private final static String NAME = "Caesar Cipher";
	private HashMap<Character, Integer> charMap;
	private final static char encryptionArr[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public CaesarCipher(){
		charMap =  new HashMap<Character, Integer>();
		charMap.put('A', 0);
		charMap.put('B', 1);
		charMap.put('C', 2);
		charMap.put('D', 3);
		charMap.put('E', 4);
		charMap.put('F', 5);
		charMap.put('G', 6);
		charMap.put('H', 7);
		charMap.put('I', 8);
		charMap.put('J', 9);
		charMap.put('K', 10);
		charMap.put('L', 11);
		charMap.put('M', 12);
		charMap.put('N', 13);
		charMap.put('O', 14);
		charMap.put('P', 15);
		charMap.put('Q', 16);
		charMap.put('R', 17);
		charMap.put('S', 18);
		charMap.put('T', 19);
		charMap.put('U', 20);
		charMap.put('V', 21);
		charMap.put('W', 22);
		charMap.put('X', 23);
		charMap.put('Y', 24);
		charMap.put('Z', 25);
	}
	/*
	 * Returns an integer that is between 0 - 25 (a-z). 
	 */
	public int generateKey(){
		SecureRandom secureRandom = new SecureRandom();
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return secureRandom.nextInt(26);
	}
	
	/*
	 * Encrypts using the formula: (m(i) + k) mod 26.
	 * Returns cipher text or null if a error occurred.
	 */
	public String encrypt(String plainText, int key){
		String encryptedText = "";
		//Make sure the key is valid. 
		if(key < 0 || key > 25 || plainText.length() <= 0){
			return null;
		}
		//Eliminates any whitespace and non alpha char's.
		plainText = plainText.trim();
		plainText = plainText.replaceAll("\\W", "");
		if(plainText.contains(" ")){
			plainText = plainText.replaceAll(" ", "");
		}
		//Makes sure that all the letters are uppercase.
		plainText = plainText.toUpperCase();
		for(int i = 0; i < plainText.length(); i++){
			char letter = plainText.charAt(i);
			int lookUp = (charMap.get(letter) + key) % 26;
			encryptedText += encryptionArr[lookUp];
		}
		return encryptedText;
	}
	
	/*
	 * Decrypts using the formula: (c(i) – k) mod 26.
	 * Returns plain text or null if a error occurred. 
	 */
	public String decrypt(String cipherText, int key){
		String decryptedText = "";
		//Make sure the key is valid. 
		if(key < 0 || key > 25){
			return null;
		}
		//Eliminates any whitespace and non alph char's.
		cipherText = cipherText.trim();
		cipherText = cipherText.replaceAll("\\W", "");
		if(cipherText.contains(" ")){
			cipherText = cipherText.replaceAll(" ", "");
		}
		//Makes sure that all the letters are uppercase.
		cipherText = cipherText.toUpperCase();
		for(int i = 0; i < cipherText.length(); i++){
			char letter = cipherText.charAt(i);
			int lookUp = (charMap.get(letter) - key) % 26;
			//Returns a positive number on negative input.
			if (lookUp < 0){
				lookUp += 26;
			}
			decryptedText += encryptionArr[lookUp];
		}
		return decryptedText;
	}
	
	/*
	 * Returns the name of the cipher.
	 */
	public String getName(){
		return NAME;
	}

}
