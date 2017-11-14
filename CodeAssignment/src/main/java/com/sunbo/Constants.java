package com.sunbo;

/**
 * @author Sun Bo
 *
 */
public class Constants {

	private Constants() {
	}

	public static class Regex {
		public static final String PRODUCT = "^[A-Z][a-z]+$";
		public static final String WORD = "^[a-zA-Z]+$";

		private Regex() {
		}
	}

	public static class Keyword {
		public static final String CREDITS = "Credits";
		public static final String IS_DELIMETER = " is ";
		public static final String QMARK = "?";
		public static final String HOW_MUCH = "how much";
		public static final String HOW_MANY = "how many";
		public static final String INVALID_QUESTION = "I have no idea what you are talking about";

		private Keyword() {
		}
	}

}
