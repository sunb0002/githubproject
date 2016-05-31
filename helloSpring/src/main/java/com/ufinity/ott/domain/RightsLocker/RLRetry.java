package com.ufinity.ott.domain.RightsLocker;

import java.sql.Types;
import java.util.List;

import org.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ufinity.ott.common.utils.Validators;
import com.ufinity.ott.domain.OTTReport.TransactionReport;

public class RLRetry {

	private long datetime;
	private String hub_id;
	private String method;
	private String payload;
	private String transaction_report;
	private int retry_count;
	private int failed;
	private String failed_msg;
	private String source;
	private String ip;
	
	public RLRetry(long datetime, String hub_id, String method, String payload, String transaction_report,
			int retry_count, int failed, String failed_msg, String source, String ip) {
		this.datetime = datetime;
		this.hub_id = hub_id;
		this.method = method;
		this.payload = payload;
		this.transaction_report = transaction_report;
		this.retry_count = retry_count;
		this.failed = failed;
		this.failed_msg = failed_msg;
		this.source = source;
		this.ip = ip;
	}
	
	public RLRetry() {

	}
	public long getDatetime() {
		return datetime;
	}
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	public String getHub_id() {
		return hub_id;
	}
	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public void setTransaction_report(String transaction_report) {
		this.transaction_report = transaction_report;
	}
	public String getPayLoad() {
		return payload;
	}
	public String getTransaction_Report() {
		return transaction_report;
	}
	
	public List<Offers> getOffersFromPayload() {
		if (Validators.isNotNull(this.payload)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				List<Offers> offers = mapper.readValue(payload,
					mapper.getTypeFactory().constructCollectionType(List.class,  
								   Offers.class));	
				return offers;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public void setPayloadFromOffersList(List<Offers> payload) {

		String payLoad = "";
		if (Validators.isNotNull(payload)) {
			JSONArray ja = new JSONArray(payload);
			payLoad = ja.toString();
		}
		this.payload = payLoad;
	}
	
	public List<TransactionReport> getListFromTransaction_report() {
		if (Validators.isNotNull(this.transaction_report)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				List<TransactionReport> tr_list = mapper.readValue(transaction_report,
						TypeFactory.defaultInstance().constructCollectionType(List.class,  
								TransactionReport.class));
				return tr_list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public void setTransaction_reportFromTransactionReportList(List<TransactionReport> transaction_report) {

		String tr = "";
		if (Validators.isNotNull(transaction_report)) {
			JSONArray jb = new JSONArray(transaction_report);
			tr = jb.toString();
		}

		this.transaction_report = tr;
	}
	public int getRetry_count() {
		return retry_count;
	}
	public void setRetry_count(int retry_count) {
		this.retry_count = retry_count;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	public String getFailed_msg() {
		return failed_msg;
	}
	public void setFailed_msg(String failed_msg) {
		this.failed_msg = failed_msg;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Object[] getRLRetryInfo() {
		return new Object[] {
			this.datetime, this.hub_id, this.method, this.payload,
			this.transaction_report, this.retry_count, this.failed,
			this.failed_msg, this.source, this.ip
		};
	}
	
	public int[] getTypeArray() {
		return new int[] { Types.BIGINT, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TINYINT, 
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
	}
	
	@Override
	public String toString() {
		return "RLRetry [datetime=" + datetime + ", hub_id=" + hub_id + ", method=" + method + ", payload=" + payload
				+ ", transaction_report=" + transaction_report + ", retry_count=" + retry_count + ", failed=" + failed
				+ ", failed_msg=" + failed_msg + ", source=" + source + ", ip=" + ip + "]";
	}
}
