package com.sunbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunbo.Constants.Keyword;
import com.sunbo.Constants.Regex;

/**
 * @author Sun Bo
 *
 */
public class Calculator {

	private Map<String, String> dictionaryMap;
	private Map<String, Double> priceMap;

	private List<String> dictionaryInfoLines;
	private List<String> priceInfoLines;
	private List<String> questionLines;

	public Calculator(final List<String> notes) {
		loadNotes(notes);
	}

	public void loadNotes(final List<String> notes) {
		dictionaryMap = new HashMap<>();
		priceMap = new HashMap<>();
		dictionaryInfoLines = new ArrayList<>();
		priceInfoLines = new ArrayList<>();
		questionLines = new ArrayList<>();

		parseNoteLines(notes);
	}

	public List<String> getDictionaryInfoLines() {
		return dictionaryInfoLines;
	}

	public List<String> getPriceInfoLines() {
		return priceInfoLines;
	}

	public List<String> getQuestionLines() {
		return questionLines;
	}

	/**
	 * Parse dictionary lines and update dictionaryMap
	 */
	protected void updateDictionary() {

		if (CommonUtils.isEmpty(getDictionaryInfoLines())) {
			return;
		}

		for (String line : dictionaryInfoLines) {
			String[] parts = line.split(Keyword.IS_DELIMETER);
			if (parts.length != 2) {
				continue;
			}

			String dictKey = parts[0].trim();
			String dictVal = parts[1].trim();

			if (dictKey.matches(Regex.WORD) && isValidRomanDigit(dictVal)) {
				dictionaryMap.put(dictKey, dictVal);
			}
		}

	}

	/**
	 * parse price lines and update priceMap
	 */
	protected void updatePrices() {

		if (CommonUtils.isEmpty(getPriceInfoLines()) || CommonUtils.isEmpty(this.dictionaryMap)) {
			return;
		}

		for (String line : priceInfoLines) {

			String[] parts = line.split(Keyword.IS_DELIMETER);

			if (parts.length != 2) {
				continue;
			}

			try {
				String part2 = parts[1].trim();
				String totalPriceStr = part2.substring(0, part2.lastIndexOf(Keyword.CREDITS)).trim();
				double totalPrice = Double.parseDouble(totalPriceStr);
				if (totalPrice < 0) {
					throw new Exception("Invalid price input");
				}

				String part1 = parts[0].trim();
				String[] part1Words = part1.split(" ");
				String product = part1Words[part1Words.length - 1];
				if (!product.matches(Regex.PRODUCT)) {
					throw new Exception("Invalid product input");
				}

				String romanStr = parseAlienStrToRomanStr(part1, true);

				int productCount = toArabValue(romanStr);
				priceMap.put(product, totalPrice / productCount);

			} catch (Exception e) {
				logerror(e);
				continue;
			}

		}

	}

	/**
	 * Sync with the latest notes and solve the questions.
	 * 
	 * @return
	 */
	public List<String> getAnswersToQuestions() {

		updateDictionary();
		updatePrices();

		List<String> output = new ArrayList<>();

		if (CommonUtils.isEmpty(getQuestionLines()) || CommonUtils.isEmpty(this.dictionaryMap)
				|| CommonUtils.isEmpty(this.priceMap)) {
			return output;
		}

		for (String line : getQuestionLines()) {

			try {
				if (!line.contains(Keyword.HOW_MANY) && !line.contains(Keyword.HOW_MUCH)
						&& !line.contains(Keyword.IS_DELIMETER)) {
					throw new Exception();
				}

				String prefixHowMuch = Keyword.HOW_MUCH + Keyword.IS_DELIMETER;
				String prefixHowMany = Keyword.HOW_MANY + " " + Keyword.CREDITS + Keyword.IS_DELIMETER;

				if (line.startsWith(prefixHowMuch)) {
					String alienStr = line.substring(prefixHowMuch.length(), line.length() - Keyword.QMARK.length())
							.trim();
					String romanStr = parseAlienStrToRomanStr(alienStr, false);
					output.add(alienStr + Keyword.IS_DELIMETER + toArabValue(romanStr));
				} else if (line.startsWith(prefixHowMany)) {
					String alienStr = line.substring(prefixHowMany.length(), line.length() - Keyword.QMARK.length())
							.trim();

					String[] alienWords = alienStr.trim().split(" ");
					String product = alienWords[alienWords.length - 1];
					if (!product.matches(Regex.PRODUCT)) {
						throw new Exception("Invalid product input");
					}

					Double price = priceMap.get(product);
					if (price == null) {
						throw new Exception("Unknown product");
					}

					String romanStr = parseAlienStrToRomanStr(alienStr, true);
					output.add(alienStr + Keyword.IS_DELIMETER + (int) (toArabValue(romanStr) * price) + " "
							+ Keyword.CREDITS);

				} else {
					throw new Exception("Unsupported question format");
				}

			} catch (Exception e) {
				logerror(e);
				output.add(Keyword.INVALID_QUESTION);
			}

		}

		return output;

	}

	/**
	 * Verify whether the input is a recognizable RomanDigit.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValidRomanDigit(String s) {

		if (CommonUtils.isEmpty(s)) {
			return false;
		}

		for (RomanDigit rd : RomanDigit.values()) {
			if (rd.name().equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validate and calculate the Arabic value based on the Roman string input
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public int toArabValue(String s) throws Exception {

		int result = 0;
		List<RomanDigit> romanNumber = toRomanNumber(s);

		boolean hasJustSubtracted = false;
		for (int i = 0; i < romanNumber.size(); i++) {
			RomanDigit rd = romanNumber.get(i);

			if (i == romanNumber.size() - 1) {
				result += rd.getArabValue();
			} else {
				RomanDigit nextRd = romanNumber.get(i + 1);
				if (!hasJustSubtracted && rd.getArabValue() < nextRd.getArabValue()) {
					result -= rd.getArabValue();
					hasJustSubtracted = !hasJustSubtracted;
				} else {
					result += rd.getArabValue();
					hasJustSubtracted = false;
				}
			}
		}

		return result;
	}

	/**
	 * Validate and convert input string to a sequence of RomanDigit.
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public List<RomanDigit> toRomanNumber(String s) throws Exception {

		List<RomanDigit> result = new ArrayList<>();
		if (CommonUtils.isEmpty(s)) {
			return result;
		}

		char[] arr = s.toCharArray();

		boolean hasJustSubtracted = false;
		for (int i = 0; i < arr.length; i++) {

			char currentCh = arr[i];
			RomanDigit rd = RomanDigit.valueOf(Character.toString(currentCh));

			// validate for subtracting
			if (i > 0) {
				RomanDigit prevRd = result.get(i - 1);
				if (prevRd.getArabValue() < rd.getArabValue()) {
					if (!hasJustSubtracted && !prevRd.canFollowBy(rd)) {
						throw new Exception(prevRd.name() + " cannot be followed by " + rd.name());
					}
					hasJustSubtracted = !hasJustSubtracted;
				}
			}

			// validate for repeating
			int repeatLimit = rd.getMaxRepeat();
			if (i > repeatLimit - 1) {
				String proceedingStr = s.substring(i - repeatLimit, i);
				StringBuilder checkStr = new StringBuilder();
				for (int j = 0; j < repeatLimit; j++) {
					checkStr.append(currentCh);
				}
				if (checkStr.toString().equals(proceedingStr)) {
					throw new Exception(rd.name() + " cannot repeat for more than " + repeatLimit + "times");
				}
			}
			result.add(rd);
		}

		return result;

	}

	/**
	 * Parse the raw input notes and store as questions, prices and dictionary
	 * (general words).
	 * 
	 * @param notes
	 */
	private void parseNoteLines(List<String> notes) {

		if (CommonUtils.isEmpty(notes)) {
			return;
		}

		for (String line : notes) {

			if (CommonUtils.isEmpty(line)) {
				continue;
			} else {
				line = line.trim();
			}

			if (line.endsWith(Keyword.QMARK)) {
				questionLines.add(line);
			} else if (line.endsWith(Keyword.CREDITS) && line.contains(Keyword.IS_DELIMETER)) {
				priceInfoLines.add(line);
			} else {
				dictionaryInfoLines.add(line);
			}
		}
	}

	/**
	 * Translate alien notes to a Roman sequence string e.g glob prok => XV
	 * 
	 * @param s
	 * @param hasProduct
	 * @return
	 * @throws Exception
	 */
	private String parseAlienStrToRomanStr(String s, boolean hasProduct) throws Exception {

		StringBuilder romanStr = new StringBuilder();

		if (CommonUtils.isEmpty(s, true) || CommonUtils.isEmpty(this.dictionaryMap)) {
			return romanStr.toString();
		}

		String[] words = s.trim().split(" ");

		for (int i = 0; i < words.length - (hasProduct ? 1 : 0); i++) {
			String word = words[i].trim();
			if (!CommonUtils.isEmpty(word)) {
				String romanFromDict = dictionaryMap.get(word);
				if (romanFromDict == null) {
					throw new Exception("Alien word not found in dictionary" + word);
				} else {
					romanStr.append(romanFromDict);
				}
			}
		}

		return romanStr.toString();
	}

	/**
	 * LOGGER
	 * 
	 * @param text
	 */
	private void logerror(Object obj) {
		// System.out.println("com.sunbo.Calculator -- " + obj);
	}

}
