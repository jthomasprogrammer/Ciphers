package ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class VigenereCipher {
	private final static String NAME = "Vigenere Cipher";
	private HashMap<Character, Integer> charMap;
	private final static char encryptionArr[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public VigenereCipher(){
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
	 * Returns an key that has a length between 1 and a given length or null if theres a problem with the input length. 
	 */
	public String generateKey(int length){
		if(length <= 0){
			return null;
		}
		SecureRandom secureRandom = new SecureRandom();
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		int chosenLength = secureRandom.nextInt(length);
		//Added + 1 to the chosen length so it would never be 0.
		if(chosenLength == 0){
			chosenLength = 1;
		}
		String key = "";
		//Builds the key.
		for(int i = 0; i < chosenLength; i++){
			int randomValue = secureRandom.nextInt(26);
			key += encryptionArr[randomValue];
		}
		return key;
	}

	/*
	 * Encrypts using the formula: m(i) + next character of the key (wrapping when necessary) mod 26. 
	 * Returns cipher text or null if a error occurred.
	 */
	public String encrypt(String plainText, String key){
		String encryptedText = "";
		//Make sure the key and plainText is valid. 
		if(plainText.length() <= 0 || key.length() <= 0){
			return null;
		}
		//Eliminates any whitespace and any alph char's.
		plainText = plainText.trim();
		plainText = plainText.replaceAll("\\W", "");
		key = key.trim();
		if(plainText.contains(" ") || key.contains(" ")){
			plainText = plainText.replaceAll(" ", "");
			key = key.replaceAll(" ", "");
		}
		//Makes sure that all the letters are uppercase.
		plainText = plainText.toUpperCase();
		key = key.toUpperCase();
		
		for(int i = 0; i < plainText.length(); i++){
			char letter = plainText.charAt(i);
			char keyLetter = key.charAt((i % key.length()));
			int lookUp = (charMap.get(letter) + charMap.get(keyLetter)) % 26;
			encryptedText += encryptionArr[lookUp];
		}
		return encryptedText;
	}
	/*
	 * Decrypts using the formula: m(i) – next character of the key (wrapping when necessary) mod 26. 
	 * Returns the plain text or null if a error occurred.
	 */
	public String decrypt(String cipherText, String key){
		String plainText = "";
		//Make sure the key and plainText is valid. 
		if(cipherText.length() <= 0 || key.length() <= 0){
			return null;
		}
		//Eliminates any whitespace and any alpha char's.
		cipherText = cipherText.trim();
		cipherText = cipherText.replaceAll("\\W", "");
		key = key.trim();
		if(cipherText.contains(" ") || key.contains(" ")){
			cipherText = cipherText.replaceAll(" ", "");
			key = key.replaceAll(" ", "");
		}
		//Makes sure that all the letters are uppercase.
		cipherText = cipherText.toUpperCase();
		key = key.toUpperCase();
		
		for(int i = 0; i < cipherText.length(); i++){
			char letter = cipherText.charAt(i);
			char keyLetter = key.charAt((i % key.length()));
			int lookUp = (charMap.get(letter) - charMap.get(keyLetter)) % 26;
			//Returns a positive number on negative input.
			if (lookUp < 0){
				lookUp += 26;
			}
			plainText += encryptionArr[lookUp];
		}
		return plainText;
	}
	

	/*
	 * Returns the name of the cipher.
	 */
	public String getName(){
		return NAME;
	}
}
