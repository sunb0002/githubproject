package com.ufinity.ott.domain.OTTReport;

import java.util.List;

public class TransactionHistoryWrapper {
	private int status;
	private String message;
	private List<TransactionReport> transaction_reports;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<TransactionReport> getTransaction_reports() {
		return transaction_reports;
	}
	public void setTransaction_reports(List<TransactionReport> transaction_reports) {
		this.transaction_reports = transaction_reports;
	}
	@Override
	public String toString() {
		return "TransactionHistoryWrapper [status=" + status + ", message=" + message + ", transaction_reports="
				+ transaction_reports + "]";
	}
}
