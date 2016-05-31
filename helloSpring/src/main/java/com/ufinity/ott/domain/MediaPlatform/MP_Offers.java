package com.ufinity.ott.domain.MediaPlatform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Offers {
	@JsonProperty("id") // to map with JSON field
	private String id;
	@JsonProperty("name") // to map with JSON field
	private String name;
	@JsonProperty("product-id") // to map with JSON field
	private String productid;
	@JsonProperty("signature") // to map with JSON field
	private String signature;
	@JsonProperty("display_name") // to map with JSON field
	private String display_name;
	//@JsonProperty("availability-window") // to map with JSON field
	//private String availabilitywindow;
	//@JsonProperty("blackout-window") // to map with JSON field
	//private String blackoutwindow;
	
	public MP_Offers() {}

	public MP_Offers(String id, String name, String productid, String signature, String display_name) {
		this.id = id;
		this.name = name;
		this.productid = productid;
		this.signature = signature;
		this.display_name = display_name;
	}

	public MP_Offers(String id, String name, String productid, String signature) {//, String availabilitywindow,
			//String blackoutwindow) {
		super();
		this.id = id;
		this.name = name;
		this.productid = productid;
		this.signature = signature;
		//this.availabilitywindow = availabilitywindow;
		//this.blackoutwindow = blackoutwindow;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	@Override
	public String toString() {
		return "MP_Offers [id=" + id + ", name=" + name + ", productid=" + productid + ", signature=" + signature
				+ ", display_name=" + display_name + "]";
	}

	/*public String getAvailabilitywindow() {
		return availabilitywindow;
	}

	public void setAvailabilitywindow(String availabilitywindow) {
		this.availabilitywindow = availabilitywindow;
	}

	public String getBlackoutwindow() {
		return blackoutwindow;
	}

	public void setBlackoutwindow(String blackoutwindow) {
		this.blackoutwindow = blackoutwindow;
	}

	@Override
	public String toString() {
		return "MP_Offers [id=" + id + ", name=" + name + ", productid=" + productid + ", signature=" + signature
				+ ", availabilitywindow=" + availabilitywindow + ", blackoutwindow=" + blackoutwindow + "]";
	}*/

}
