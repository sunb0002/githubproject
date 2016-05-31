package com.ufinity.ott.domain.MediaPlatform;

public class EntitlementSynchronizerMapping {
	private String contentid;
	private String productid;
	private String name;
	private String display_name;

	public EntitlementSynchronizerMapping() {}

	public EntitlementSynchronizerMapping(String contentid, String productid, String name, String display_name) {
		super();
		this.contentid = contentid;
		this.productid = productid;
		this.name = name;
		this.display_name = display_name;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	@Override
	public String toString() {
		return "EntitlementSynchronizerMapping [contentid=" + contentid + ", productid=" + productid + ", name=" + name
				+ ", display_name=" + display_name + "]";
	}
}
