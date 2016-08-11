package com.ufinity.madoka.domain.ResponseEntities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonGeneral {

	@JsonProperty
	protected int ret_code;
	@JsonProperty
	protected String ret_msg;

	public JsonGeneral() {
	}

	public JsonGeneral(int ret_code, String ret_msg) {
		this.ret_code = ret_code;
		this.ret_msg = ret_msg;
	}

	public int getRet_code() {
		return ret_code;
	}

	public void setRet_code(int ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	@Override
	public String toString() {
		return "MssoGeneral [ret_code=" + ret_code + ", ret_msg=" + ret_msg + "]";
	}

}
