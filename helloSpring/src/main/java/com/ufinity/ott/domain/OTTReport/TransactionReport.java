package com.ufinity.ott.domain.OTTReport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @since 2015-11-04
 * @author Benard
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 * 
 */
public class TransactionReport {

	@JsonProperty
	private long datetime;
	@JsonProperty
	private String payment_method;
	@JsonProperty
	private String hubid;
	@JsonProperty
	private String offer_id;
	@JsonProperty
	private String asset_id;
	@JsonProperty
	private String asset_name;
	@JsonProperty
	private double price_final;
	@JsonProperty
	private long rental_start_date;
	@JsonProperty
	private long rental_end_date;
	@JsonProperty
	private String purchase_type;
	@JsonProperty
	private String device;
	@JsonProperty
	private String transaction_status;
	@JsonProperty
	private int retry_count;
	@JsonProperty
	private String error_message;
	@JsonProperty
	private String description;
	@JsonProperty
	private String type;
	
	public TransactionReport () {}
	
	public TransactionReport(long datetime, String payment_method,
			String hubid, String offer_id, String asset_id, String asset_name,
			double price_final, long rental_start_date, long rental_end_date,
			String purchase_type, String device, String transaction_status,
			int retry_count, String error_message, String description, String type) {
		super();
		this.datetime = datetime;
		this.payment_method = payment_method;
		this.hubid = hubid;
		this.offer_id = offer_id;
		this.asset_id = asset_id;
		this.asset_name = asset_name;
		this.price_final = price_final;
		this.rental_start_date = rental_start_date;
		this.rental_end_date = rental_end_date;
		this.purchase_type = purchase_type;
		this.device = device;
		this.transaction_status = transaction_status;
		this.retry_count = retry_count;
		this.error_message = error_message;
		this.description = description;
		this.type = type;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getHubid() {
		return hubid;
	}

	public void setHubid(String hubid) {
		this.hubid = hubid;
	}

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public double getPrice_final() {
		return price_final;
	}

	public void setPrice_final(double price_final) {
		this.price_final = price_final;
	}

	public long getRental_start_date() {
		return rental_start_date;
	}

	public void setRental_start_date(long rental_start_date) {
		this.rental_start_date = rental_start_date;
	}

	public long getRental_end_date() {
		return rental_end_date;
	}

	public void setRental_end_date(long rental_end_date) {
		this.rental_end_date = rental_end_date;
	}

	public String getPurchase_type() {
		return purchase_type;
	}

	public void setPurchase_type(String purchase_type) {
		this.purchase_type = purchase_type;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public int getRetry_count() {
		return retry_count;
	}

	public void setRetry_count(int retry_count) {
		this.retry_count = retry_count;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description; 
	}
	
	public String getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReadableTimeStamp (long time) {
		if(time == 0){
			return "";
		}
		
		String stringTime = time + "";
		long epoch = Long.parseLong(stringTime);
		Date timestamp = new Date (epoch * 1000L);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String formatted = format.format(timestamp);
		return formatted;
	}

	@Override
	public String toString() {
		return "TransactionReport [datetime=" + datetime + ", payment_method=" + payment_method + ", hubid=" + hubid
				+ ", offer_id=" + offer_id + ", asset_id=" + asset_id + ", asset_name=" + asset_name + ", price_final="
				+ price_final + ", rental_start_date=" + rental_start_date + ", rental_end_date=" + rental_end_date
				+ ", purchase_type=" + purchase_type + ", device=" + device + ", transaction_status="
				+ transaction_status + ", retry_count=" + retry_count + ", error_message=" + error_message
				+ ", description=" + description + ", type=" + type + "]";
	}	
}
