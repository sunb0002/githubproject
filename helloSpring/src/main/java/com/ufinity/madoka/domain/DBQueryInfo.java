package com.ufinity.madoka.domain;

import com.ufinity.madoka.common.cons.Constant;

public class DBQueryInfo {
	private int _errcode;

	private String _errmsg;

	public String sql;

	private Object _obj;

	public static final DBQueryInfo OK = new DBQueryInfo(Constant.CODE_SUCCESS, null, null) {
		public void setErrorCode(int i) {
			throw new UnsupportedOperationException("Cannot setErrorCode for this instance,this is a constant");
		}

		public void setErrorMessage(String msg) {
			throw new UnsupportedOperationException("Cannot setErrorMessage for this instance,this is a constant");
		}

		public void setObject(Object msg) {
			throw new UnsupportedOperationException("Cannot setObject for this instance,this is a constant");
		}
	};

	public DBQueryInfo(int i, String m, Object u) {
		this._errcode = i;
		this._errmsg = m;
		this._obj = u;
	}

	public void setErrorCode(int _errcode) {
		this._errcode = _errcode;
	}

	public void setErrorMessage(String _errmsg) {
		this._errmsg = _errmsg;
	}

	public void setObject(Object _obj) {
		this._obj = _obj;
	}

	public int getErrorCode() {
		return (this._errcode);
	}

	public String getErrorMessage() {
		return (this._errmsg);
	}

	public Object getObject() {
		return (this._obj);
	}

	public boolean isOk() {
		return this.getErrorCode() == Constant.CODE_SUCCESS;
	}

	/**
	 */
	public boolean isNotOk() {
		return this.getErrorCode() != Constant.CODE_SUCCESS;
	}

	/**
	 */
	public boolean isOkNotNull() {
		return (this.isOk()) && (this._obj != null);
	}

	/**
	 */
	public boolean isOkButNull() {
		return (this.isOk()) && (this._obj == null);
	}

	public String toString() {

		String sep = System.getProperty("line.separator");

		StringBuffer buffer = new StringBuffer();
		buffer.append(sep);
		buffer.append("_errcode = ");
		buffer.append(this._errcode);
		buffer.append(sep);
		buffer.append("_errmsg = ");
		buffer.append(this._errmsg);
		buffer.append(sep);
		buffer.append("_obj = ");
		buffer.append(this._obj);
		return buffer.toString();
	}

}
