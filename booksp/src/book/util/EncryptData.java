package book.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptData {

	/**
	 * 单向散列函数，使用MessageDigest类实现MD5、SHA加密
	 * 
	 * @param algorithm 加密算法
	 * @param data      要加密的数据
	 * @return 加密后的数据
	 */
	public static String encryptData(String algorithm, String data) throws NoSuchAlgorithmException {
		// 应用报文摘要方法，得到单向的加密字符串
		// MD5是16位,SHA是20位（这是两种报文摘要的算法）
		MessageDigest md = MessageDigest.getInstance(algorithm);
		try {
			md.update(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] d = md.digest();
		return byte2hex2(d);
//		得到的是个二进制byte数组，有可能某些byte是不可打印的字符。
//		所以用Base64.encode把它转化成可打印字符。
		// String dbe = new String(Base64.getEncoder().encode(d));

	}

	@SuppressWarnings("unused")
	@Deprecated(since = "2021-01-18")
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	private static String byte2hex2(byte[] bin) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < bin.length; ++i) {
			int x = bin[i] & 0xFF, h = x >>> 4, l = x & 0x0F;
			buf.append((char) (h + ((h < 10) ? '0' : 'a' - 10)));
			buf.append((char) (l + ((l < 10) ? '0' : 'a' - 10)));
		}
		return buf.toString();
	}

	/**
	 * 使用KeyGenerator和Cipher类实现AES对称加密
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 */
	public static void symmetricEncrypt(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException {
		//
		// check args and get plaintext
		if (args.length != 1) {
			System.err.println("Usage: java PrivateExample text");
			System.exit(1);
		}
		byte[] plainText = args[0].getBytes("UTF8");
		//
		// get a DES private key
		System.out.println("\nStart generating DES key");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);//128,192,256三者选一
		Key key = keyGen.generateKey();
		System.out.println("Finish generating DES key");
		// save key
		byte[] encoded = key.getEncoded();
		Arrays.asList(encoded).forEach(System.out::println);
		//
		// get a DES cipher object and print the provider
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		System.out.println("\n" + cipher.getProvider().getInfo());
		//
		// encrypt using the key and the plaintext
		System.out.println("\nStart encryption");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println("Finish encryption: ");
		System.out.println(new String(cipherText, "UTF8"));

		
		byte[] dencoded = encoded;//模拟取出私钥，然后解密加密后的内容
		Key k = new SecretKeySpec(dencoded,"AES");
		// decrypt the ciphertext using the same key
		System.out.println("\nStart decryption");
		Cipher dcipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		dcipher.init(Cipher.DECRYPT_MODE, k);
		byte[] newPlainText = dcipher.doFinal(cipherText);
		System.out.println("Finish decryption: ");
		System.out.println(new String(newPlainText, "UTF8"));

	}

	public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		System.out.println(EncryptData.encryptData("SHA-256", "123"));
		System.out.println(EncryptData.encryptData("SHA-256", "123abc"));
		System.out.println(EncryptData.encryptData("SHA-256", "111111"));
		String[] strs = { "要加密的内容" };
		symmetricEncrypt(strs);
	}

}
