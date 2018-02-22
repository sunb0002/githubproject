package com.sunbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sunbo.Constants.Keyword;

/**
 * @author Sun Bo
 *
 */
public class CalculatorTest {

	private Calculator cal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<String> notes = new ArrayList<>();
		cal = new Calculator(notes);
	}

	@Test
	public void testAll() {

		List<String> notes = new ArrayList<>();
		notes.add("glob is I");
		notes.add("prok is V");
		notes.add("pish is X");
		notes.add("tegj is L");
		notes.add("glob glob Silver is 34 Credits");
		notes.add("glob prok Gold is 57800 Credits");
		notes.add("pish pish Iron is 3910 Credits");
		notes.add("how much is pish tegj glob glob ?");
		notes.add("how many Credits is glob prok Silver ?");
		notes.add("how many Credits is glob prok Gold ?");
		notes.add("how many Credits is glob prok Iron ?");
		notes.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		cal.loadNotes(notes);
		List<String> output = cal.getAnswersToQuestions();
		log(output);

		assertFalse(CommonUtils.isEmpty(output));
		assertEquals(output.size(), 5);
		assertEquals(output.get(0), "pish tegj glob glob is 42");
		assertEquals(output.get(4), Keyword.INVALID_QUESTION);

	}

	@Test
	public void testLoadNotes() {

		List<String> notes = new ArrayList<>();
		notes.add("glob is I");
		cal.loadNotes(notes);
		assertEquals(cal.getDictionaryInfoLines().get(0), "glob is I");

		notes.add("glob glob Silver is 34 Credits");
		cal.loadNotes(notes);
		assertEquals(cal.getPriceInfoLines().get(0), "glob glob Silver is 34 Credits");

		notes.add("how many Credits is glob prok Silver ?");
		cal.loadNotes(notes);
		assertEquals(cal.getQuestionLines().get(0), "how many Credits is glob prok Silver ?");

	}

	@Test
	public void testIsValidRomanDigit() {

		assertTrue(cal.isValidRomanDigit("X"));
		assertFalse(cal.isValidRomanDigit("A"));
	}

	@Test
	public void testToArabValue() {

		int result = -1;
		try {
			result = cal.toArabValue("MMVI");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(result, 2006);

		try {
			result = cal.toArabValue("MCMXLIV");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(result, 1944);

		try {
			result = cal.toArabValue("MCMIII");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(result, 1903);
	}

	@Test
	public void testToRomanNumber() {

		List<RomanDigit> result = null;

		String input = "MMVI";
		try {
			result = cal.toRomanNumber(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(result);
		assertEquals(result.size(), input.length());

		input = "MCMXLIV";
		try {
			result = cal.toRomanNumber(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(result);
		assertEquals(result.size(), input.length());

		result = null;
		try {
			result = cal.toRomanNumber("VV");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(result);

		result = null;
		try {
			result = cal.toRomanNumber("XXXX");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(result);

		result = null;
		try {
			result = cal.toRomanNumber("XXXIX");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(result);

		result = null;
		try {
			result = cal.toRomanNumber("IM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(result);

		result = null;
		try {
			result = cal.toRomanNumber("XM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(result);

		result = null;
		try {
			result = cal.toRomanNumber("VM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(result);

	}

	private void log(Object obj) {
		System.out.println("com.sunbo.CalculatorTest -- " + obj);
	}

}
