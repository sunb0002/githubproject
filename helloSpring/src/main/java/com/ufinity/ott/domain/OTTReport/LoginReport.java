package com.ufinity.ott.domain.OTTReport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginReport {

	@JsonProperty
	private long datetime;
	@JsonProperty
	private String hubid, hubid_type, login_status;
	
	public LoginReport() {}
	
	public LoginReport(String hubid, String hubid_type, String login_status) {
		this.datetime = System.currentTimeMillis() / 1000;
		this.hubid = hubid;
		this.hubid_type = hubid_type;
		this.login_status = login_status;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public String getHubid() {
		return hubid;
	}

	public void setHubid(String hubid) {
		this.hubid = hubid;
	}

	public String getHubid_type() {
		return hubid_type;
	}

	public void setHubid_type(String hubid_type) {
		this.hubid_type = hubid_type;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getReadableTimeStamp (long time) {
		String stringTime = time + "";
		long epoch = Long.parseLong(stringTime);
		Date timestamp = new Date (epoch * 1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatted = format.format(timestamp);
		return formatted;
	}
	@Override
	public String toString() {
		return "LoginReport [datetime=" + datetime + ", hubid=" + hubid
				+ ", hubid_type=" + hubid_type + ", login_status="
				+ login_status + "]";
	}

}
