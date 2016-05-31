package com.ufinity.ott.common.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class Common {

	public static String getRealIPAddress(HttpServletRequest request) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (Validators.isEmpty(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;
	}

	public static String getRequestBody(HttpServletRequest request)
			throws IOException {

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line.trim());
		}
		return buffer.toString();
	}

}
