package com.posidex.util;

import java.io.UnsupportedEncodingException;
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

	public static final String initialVector = "AkhilChinnuBosss";
	public static final String key = "7HDpF0SgQiWh81==";

	public static String encrypt(String value) throws EncryptionException {
		try {
			IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return DatatypeConverter.printBase64Binary(encrypted);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptionException(e);
		}
	}

	public static String decrypt(String encrypted) throws EncryptionException {
		try {
			IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes("UTF-8"));
			SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey, iv);
			byte[] message = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
			return new String(message);

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptionException(e);
		}
	}
	
	/**
	 * The Class EncryptionException.
	 */
	public static class EncryptionException extends Exception {
		private static final long serialVersionUID = 4532310781796654212L;

		public EncryptionException(Throwable t) {
			super(t);
		}
	}
}
