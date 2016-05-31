package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assets {
	
	@JsonProperty("offer_id") // to map with JSON field
	private String offer_id;
	@JsonProperty("asset_id") // to map with JSON field
	private String asset_id;
	@JsonProperty("asset_name") // to map with JSON field
	private String asset_name;
	@JsonProperty("product_id") // to map with JSON field
	private String product_id;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("price_final") // to map with JSON field
	private String price_final;
	@JsonProperty("rental_start_date") // to map with JSON field
	private String rental_start_date;
	@JsonProperty("rental_end_date") // to map with JSON field
	private String rental_end_date;
	@JsonProperty("purchase_type") // to map with JSON field
	private String Purchase_type;
	
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
	public String getPrice_final() {
		return price_final;
	}
	public void setPrice_final(String price_final) {
		this.price_final = price_final;
	}
	public String getRental_start_date() {
		return rental_start_date;
	}
	public void setRental_start_date(String rental_start_date) {
		this.rental_start_date = rental_start_date;
	}
	public String getRental_end_date() {
		return rental_end_date;
	}
	public void setRental_end_date(String rental_end_date) {
		this.rental_end_date = rental_end_date;
	}
	public String getPurchase_type() {
		return Purchase_type;
	}
	public void setPurchase_type(String purchase_type) {
		Purchase_type = purchase_type;
	}
	@Override
	public String toString() {
		return "Assets [offer_id=" + offer_id + ", asset_id=" + asset_id + ", asset_name=" + asset_name
				+ ", product_id=" + product_id + ", type=" + type + ", price_final=" + price_final
				+ ", rental_start_date=" + rental_start_date + ", rental_end_date=" + rental_end_date
				+ ", Purchase_type=" + Purchase_type + "]";
	}
}
