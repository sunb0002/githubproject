package com.sunbo;

import java.io.IOException;
import java.util.List;

/**
 * @author Sun Bo
 *
 */
public class CalculatorRunner {

	private CalculatorRunner() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log("Merchant galaxy calculator starts.");

		String filePath = "Notes.txt";

		try {
			List<String> notes = CommonUtils.readLines(filePath);
			Calculator calculator = new Calculator(notes);
			List<String> output = calculator.getAnswersToQuestions();
			for (String s : output) {
				System.out.println(s);
			}

		} catch (IOException e) {
			log("Unexpected error occurred: " + e);
		}

		log("Merchant galaxy calculator starts.");

	}

	private static void log(Object obj) {
		// System.out.println("com.sunbo.CalculatorRunner -- " + obj);
	}

}
