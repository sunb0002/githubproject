package com.ufinity.ott.domain.OTTReport;

public class EntitlementsVisualizer {
	private String offer_id;
	private String asset_id;
	private String asset_name;
	private String start_time;
	private String end_time;
	private String publishing_rule_id;
	private String product_id;
	private String product_name;
	private String transaction_date;
	private String updated_date;
	private String external_product_id;
	private String status;
	
	public EntitlementsVisualizer() {
	}
	
	public EntitlementsVisualizer(String offer_id, String asset_id, String asset_name, String start_time,
			String end_time, String publishing_rule_id, String product_id, String product_name, String transaction_date,
			String updated_date, String external_product_id, String status) {
		super();
		this.offer_id = offer_id;
		this.asset_id = asset_id;
		this.asset_name = asset_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.publishing_rule_id = publishing_rule_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.transaction_date = transaction_date;
		this.updated_date = updated_date;
		this.external_product_id = external_product_id;
		this.status = status;
	}
	
	public String getOffer_id() {
		return offer_id;
	}
	
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
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
	
	public String getStart_time() {
		return start_time;
	}
	
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getPublishing_rule_id() {
		return publishing_rule_id;
	}
	
	public void setPublishing_rule_id(String publishing_rule_id) {
		this.publishing_rule_id = publishing_rule_id;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public String getTransaction_date() {
		return transaction_date;
	}
	
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getExternal_product_id() {
		return external_product_id;
	}
	
	public void setExternal_product_id(String external_product_id) {
		this.external_product_id = external_product_id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EntitlementsVisualizer [offer_id=" + offer_id + ", asset_id=" + asset_id + ", asset_name=" + asset_name
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", publishing_rule_id=" + publishing_rule_id
				+ ", product_id=" + product_id + ", product_name=" + product_name + ", transaction_date="
				+ transaction_date + ", updated_date=" + updated_date + ", external_product_id=" + external_product_id
				+ ", status=" + status + "]";
	}
}
