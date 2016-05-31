package com.ufinity.ott.domain;

import org.springframework.http.HttpStatus;

public class HTTPQueryInfo {
	private int statusCode;
	private String message;
	private Object obj;

	public HTTPQueryInfo() {
		this.statusCode = HttpStatus.OK.value();
		this.message = "";
		this.obj = null;
	}

	public HTTPQueryInfo(int statusCode, String message, Object obj) {
		this.statusCode = statusCode;
		this.message = message;
		this.obj = obj;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isOk() {
		return (this.statusCode == HttpStatus.OK.value());
	}

	@Override
	public String toString() {
		return "HTTPQueryInfo [statusCode=" + statusCode + ", message="
				+ message + ", obj=" + obj + "]";
	}

}
