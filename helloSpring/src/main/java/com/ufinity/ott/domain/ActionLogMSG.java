package com.ufinity.ott.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufinity.ott.domain.OTTReport.LoginReport;
import com.ufinity.ott.domain.OTTReport.TransactionReport;

/**
 * @since 2015-10-27
 * @author SunBo
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionLogMSG {

	@JsonProperty
	private String title;
	@JsonProperty
	private LoginReport login_report;
	@JsonProperty
	private TransactionReport transaction_report;

	/*
	 * @JsonProperty private TransactionReport transactionReport;
	 */
	public ActionLogMSG(){}
	
	public ActionLogMSG(String title) {
		this.title = title;
		login_report = null;
		transaction_report = null;
	}
	
	public ActionLogMSG(String title, LoginReport loginReport) {
		this.title = title;
		login_report = loginReport;
	}
	
	public ActionLogMSG(String title, TransactionReport transaction_report) {
		this.title = title;
		this.transaction_report = transaction_report;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LoginReport getLoginReport() {
		return login_report;
	}

	public void setLoginReport(LoginReport loginReport) {
		login_report = loginReport;
	}
	
	public TransactionReport getTransaction_report() {
		return transaction_report;
	}

	public void setTransaction_report(TransactionReport transaction_report) {
		this.transaction_report = transaction_report;
	}

	@Override
	public String toString() {
		return "ActionLogMSG [title=" + title + ", login_report=" + login_report + ", transaction_report="
				+ transaction_report + "]";
	}
}
