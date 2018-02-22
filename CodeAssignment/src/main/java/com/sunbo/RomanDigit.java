package com.sunbo;

import java.util.Arrays;

/**
 * @author Sun Bo
 *
 */
public enum RomanDigit {
	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private int arabValue;

	private RomanDigit(int i) {
		arabValue = i;
	}

	/**
	 * @return the arabValue
	 */
	public int getArabValue() {
		return arabValue;
	}

	public int getMaxRepeat() {

		switch (this) {
		case I:
		case X:
		case C:
		case M:
			return 3;
		default:
			return 1;
		}
	}

	public boolean canFollowBy(RomanDigit next) {

		// for adding, no restrictions
		if (next.getArabValue() < this.getArabValue()) {
			return true;
		}

		// for subtracting
		switch (this) {
		case I:
			return Arrays.asList(V, X).contains(next);
		case X:
			return Arrays.asList(L, C).contains(next);
		case C:
			return Arrays.asList(D, M).contains(next);
		case V:
		case L:
		case D:
			return false;
		default:
			return true;
		}

	}

}