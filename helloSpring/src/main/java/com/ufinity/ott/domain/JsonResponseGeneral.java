package com.ufinity.ott.domain;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseGeneral {

	private int STATUS;
	private String MESSAGE;

	public JsonResponseGeneral() {
		STATUS = HttpStatus.OK.value();
		MESSAGE = "";
	}

	public JsonResponseGeneral(int sTATUS, String mESSAGE) {
		STATUS = sTATUS;
		MESSAGE = mESSAGE;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	@Override
	public String toString() {
		return "JsonResponseGeneral [STATUS=" + STATUS + ", MESSAGE=" + MESSAGE + "]";
	}

}
