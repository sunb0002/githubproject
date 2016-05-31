package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFreeVOD_RequestBody {

	@JsonProperty("hub_id") // to map with JSON field
	private String hub_id;
	@JsonProperty("offer_id") // to map with JSON field
	private String offer_id;
	@JsonProperty("asset_id") // to map with JSON field
	private String asset_id;
	@JsonProperty("product_id") // to map with JSON field
	private String product_id;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("start_time") // to map with JSON field
	private long start_time;
	@JsonProperty("end_time") // to map with JSON field
	private long end_time;
	public String getHub_id() {
		return hub_id;
	}
	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
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
	public long getStart_time() {
		return start_time;
	}
	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}
	public long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "AddFreeVOD_RequestBody [hub_id=" + hub_id + ", offer_id=" + offer_id + ", asset_id=" + asset_id
				+ ", product_id=" + product_id + ", type=" + type + ", start_time=" + start_time + ", end_time="
				+ end_time + "]";
	}
}
