package com.application.security.hmac;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hash-based message authentication
 * code锛屽埄鐢ㄥ搱甯岀畻娉曪紝浠ヤ竴涓瘑閽ュ拰涓�釜娑堟伅涓鸿緭鍏ワ紝鐢熸垚涓�釜娑堟伅鎽樿浣滀负杈撳嚭
 * 
 * @author yingp
 *
 */
public class HmacEncoder {

	private final String algorithm;

	public HmacEncoder(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * 鏍规嵁缁欏畾瀵嗛挜鐢熸垚绠楁硶鍒涘缓瀵嗛挜
	 * 
	 * @param algorithm
	 *            瀵嗛挜绠楁硶
	 * @return 瀵嗛挜
	 * @throws RuntimeException
	 *             褰�{@link java.security.NoSuchAlgorithmException} 鍙戠敓鏃�
	 */
	public byte[] getKey() {
		// 鍒濆鍖朘eyGenerator
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
		// 浜х敓瀵嗛挜
		SecretKey secretKey = keyGenerator.generateKey();
		// 鑾峰緱瀵嗛挜
		return secretKey.getEncoded();
	}

	/**
	 * 杞崲瀵嗛挜
	 * 
	 * @param key
	 *            浜岃繘鍒跺瘑閽�
	 * @param algorithm
	 *            瀵嗛挜绠楁硶
	 * @return 瀵嗛挜
	 */
	private static Key toKey(byte[] key, String algorithm) {
		// 鐢熸垚瀵嗛挜
		return new SecretKeySpec(key, algorithm);
	}

	/**
	 * 浣跨敤鎸囧畾娑堟伅鎽樿绠楁硶璁＄畻娑堟伅鎽樿
	 * 
	 * @param data
	 *            鍋氭秷鎭憳瑕佺殑鏁版嵁
	 * @param key
	 *            瀵嗛挜
	 * @return 娑堟伅鎽樿锛堥暱搴︿负16鐨勫瓧鑺傛暟缁勶級
	 */
	public byte[] encode(byte[] data, Key key) {
		Mac mac = null;
		try {
			mac = Mac.getInstance(algorithm);
			mac.init(key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return new byte[0];
		}
		return mac.doFinal(data);
	}

	/**
	 * 浣跨敤鎸囧畾娑堟伅鎽樿绠楁硶璁＄畻娑堟伅鎽樿
	 * 
	 * @param data
	 *            鍋氭秷鎭憳瑕佺殑鏁版嵁
	 * @param key
	 *            瀵嗛挜
	 * @return 娑堟伅鎽樿锛堥暱搴︿负16鐨勫瓧鑺傛暟缁勶級
	 */
	public byte[] encode(byte[] data, byte[] key) {
		return encode(data, toKey(key, algorithm));
	}

}
