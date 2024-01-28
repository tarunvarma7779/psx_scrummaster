package com.posidex.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class StringEncrypter {

	private StringEncrypter() {
	}

	public static final String INITIAL_VECTOR = "AkhilChinnuBosss";
	public static final String KEY = "7HDpF0SgQiWh81==";

	public static String encrypt(String value) throws EncryptionException {
		try {
			IvParameterSpec iv = new IvParameterSpec(INITIAL_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return DatatypeConverter.printBase64Binary(encrypted);
		} catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptionException(e);
		}
	}

	public static String decrypt(String encrypted) throws EncryptionException {
		try {
			IvParameterSpec iv = new IvParameterSpec(INITIAL_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey, iv);
			byte[] message = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
			return new String(message);

		} catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptionException(e);
		}
	}

	public static boolean isPasswordDecrypted(String password) {
		try {
			decrypt(password);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public static class EncryptionException extends Exception {
		private static final long serialVersionUID = 4532310781796654212L;

		public EncryptionException(Throwable t) {
			super(t);
		}
	}
}