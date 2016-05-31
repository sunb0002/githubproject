package com.ufinity.ott.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @version 2016-Mar-28
 * @author SunBo 
 * Simplified from RightsLockerSignature.java
 * 
 */
public class OTTSignature {

	public String generateSignature_noURLencode(String secretKey,
			String HTTPMethod, String requestPath, String requestBody)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String encodedReqPath = URLEncoder.encode(requestPath,
				StandardCharsets.UTF_8.name());

		String stringToSign = secretKey + encodedReqPath + HTTPMethod;
		stringToSign += requestBody;

		MessageDigest digestProvider = MessageDigest.getInstance("SHA-256");
		digestProvider.reset();

		byte[] digest = digestProvider.digest(stringToSign.getBytes());
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String signedInput = base64Encoder.encode(digest);
		return signedInput.substring(0, 43);
	}

	public String generateSignature(String secretKey, String HTTPMethod,
			String requestPath, String requestBody)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return URLEncoder.encode(
				generateSignature_noURLencode(secretKey, HTTPMethod,
						requestPath, requestBody), "US-ASCII");
	}

}