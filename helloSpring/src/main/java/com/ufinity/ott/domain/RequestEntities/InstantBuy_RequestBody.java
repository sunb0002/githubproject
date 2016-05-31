package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstantBuy_RequestBody {

	@JsonProperty("hub_id") // to map with JSON field
	private String hub_id;
	@JsonProperty("product_id") // to map with JSON field
	private String product_id;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("bill_remark") // to map with JSON field
	private String bill_remark;
	@JsonProperty("offer_id") // to map with JSON field
	private String offer_id;
	@JsonProperty("asset_id") // to map with JSON field
	private String asset_id;
	@JsonProperty("asset_name") // to map with JSON field
	private String asset_name;
	@JsonProperty("device") // to map with JSON field
	private String device;
	@JsonProperty("time") // to map with JSON field
	private String time;
	
	public String getHub_id() {
		return hub_id;
	}
	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBill_remark() {
		return bill_remark;
	}
	public void setBill_remark(String bill_remark) {
		this.bill_remark = bill_remark;
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
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "InstantBuy_RequestBody [hub_id=" + hub_id + ", product_id=" + product_id + ", type=" + type
				+ ", bill_remark=" + bill_remark + ", offer_id=" + offer_id + ", asset_id=" + asset_id + ", asset_name="
				+ asset_name + ", device=" + device + ", time=" + time + "]";
	}
}
