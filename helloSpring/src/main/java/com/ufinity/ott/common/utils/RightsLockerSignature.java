package com.ufinity.ott.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import sun.misc.BASE64Encoder;

/**
 * 
 * @author Ooyala
 * @modified SunBo
 * 
 */
@SuppressWarnings("restriction")
public class RightsLockerSignature {

	private String concatenateParams(HashMap<String, String> parameters,
			String separator) {
		Vector<String> keys = new Vector<String>(parameters.keySet());
		Collections.sort(keys);

		String string = "";
		for (Enumeration<String> e = keys.elements(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String value = (String) parameters.get(key);
			if (!string.isEmpty())
				string += separator;
			string += key + "=" + value;
		}
		return string;
	}

	public String generateSignature_noURLencode(String secretKey,
			String HTTPMethod, String requestPath,
			HashMap<String, String> parameters, String requestBody)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String stringToSign = secretKey + HTTPMethod + requestPath;
		stringToSign += concatenateParams(parameters, "");
		stringToSign += requestBody;
		MessageDigest digestProvider = MessageDigest.getInstance("SHA-256");
		digestProvider.reset();

		byte[] digest = digestProvider.digest(stringToSign.getBytes());
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String signedInput = base64Encoder.encode(digest);
		return signedInput.substring(0, 43);
	}

	public String generateSignature(String secretKey, String HTTPMethod,
			String requestPath, HashMap<String, String> parameters,
			String requestBody) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		return URLEncoder.encode(
				generateSignature_noURLencode(secretKey, HTTPMethod,
						requestPath, parameters, requestBody), "US-ASCII");
	}

}