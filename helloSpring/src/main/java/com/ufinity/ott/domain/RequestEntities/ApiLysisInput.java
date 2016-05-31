package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiLysisInput {
	@JsonProperty("price_net")
	private double price_net;
	@JsonProperty("price_final")
	private double price_final;
	@JsonProperty("rental_period")
	private int rental_period;
	@JsonProperty("gst_flag")
	private boolean gst_flag;
	@JsonProperty("type")
	private String type;
	@JsonProperty("lysis_id")
	private String lysis_id;
	@JsonProperty("category")
	private String category;
	@JsonProperty("description")
	private String description;
	@JsonProperty
	private String time;

	public double getPrice_net() {
		return price_net;
	}

	public void setPrice_net(double price_net) {
		this.price_net = price_net;
	}

	public double getPrice_final() {
		return price_final;
	}

	public void setPrice_final(double price_final) {
		this.price_final = price_final;
	}

	public int getRental_period() {
		return rental_period;
	}

	public void setRental_period(int rental_period) {
		this.rental_period = rental_period;
	}

	public boolean isGst_flag() {
		return gst_flag;
	}

	public void setGst_flag(boolean gst_flag) {
		this.gst_flag = gst_flag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLysis_id() {
		return lysis_id;
	}

	public void setLysis_id(String lysis_id) {
		this.lysis_id = lysis_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ApiLysisInput [price_net=" + price_net + ", price_final="
				+ price_final + ", rental_period=" + rental_period
				+ ", gst_flag=" + gst_flag + ", type=" + type + ", lysis_id="
				+ lysis_id + ", category=" + category + ", description="
				+ description + ", time=" + time + "]";
	}

}
