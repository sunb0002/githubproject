package com.sunbo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Sun Bo
 *
 */
public class CommonUtils {

	private CommonUtils() {
	}

	public static boolean isEmpty(String s) {
		return isEmpty(s, true);
	}

	public static boolean isEmpty(String s, boolean trim) {
		return s == null || s.length() == 0 || (trim && s.trim().length() == 0);
	}

	public static boolean isEmpty(final Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean isEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}

	/**
	 * Can use apache-common-io
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readLines(String filePath) throws IOException {

		List<String> result = new ArrayList<>();

		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.add(line.trim());
		}

		fileReader.close();
		return result;

	}

}
