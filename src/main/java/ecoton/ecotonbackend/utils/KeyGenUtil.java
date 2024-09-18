package ecoton.ecotonbackend.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenUtil {

	public static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
		KeyPair keyPair;

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		keyPair = keyGen.generateKeyPair();

		return keyPair;
	}

}