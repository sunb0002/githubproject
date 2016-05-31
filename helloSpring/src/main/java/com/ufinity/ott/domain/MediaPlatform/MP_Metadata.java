package com.ufinity.ott.domain.MediaPlatform;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Metadata {
	@JsonProperty("display-name") // to map with JSON field
	private String display_name;
	@JsonProperty("title-internal") // to map with JSON field
	private String title_internal;
	@JsonProperty("lysis-layer2-node-cat") // to map with JSON field
	private String lysis_layer2_node_cat;
	@JsonProperty("display") // to map with JSON field
	private List<MP_Display> display;
	@JsonProperty("lysis_id") // to map with JSON field
	private String lysis_id;
	@JsonProperty("parental-rating") // to map with JSON field
	private String parental_rating;
	@JsonProperty("provider") // to map with JSON field
	private String provider;
	@JsonProperty("ad-allowed") // to map with JSON field
	private boolean ad_allowed;
	@JsonProperty("hd-content") // to map with JSON field
	private boolean hd_content;
	@JsonProperty("ooyala-state") // to map with JSON field
	private String ooyala_state;
	@JsonProperty("deactivated") // to map with JSON field
	private String deactivated;
	//@JsonProperty("ooyala-primary-image") // to map with JSON field
	//private String ooyala_primary_image;
	public MP_Metadata() {}
	public MP_Metadata(String display_name, String title_internal, String lysis_layer2_node_cat,
			List<MP_Display> display, String lysis_id, String parental_rating, String provider, boolean ad_allowed,
			boolean hd_content, String ooyala_state, String deactivated) {
		super();
		this.display_name = display_name;
		this.title_internal = title_internal;
		this.lysis_layer2_node_cat = lysis_layer2_node_cat;
		this.display = display;
		this.lysis_id = lysis_id;
		this.parental_rating = parental_rating;
		this.provider = provider;
		this.ad_allowed = ad_allowed;
		this.hd_content = hd_content;
		this.ooyala_state = ooyala_state;
		this.deactivated = deactivated;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getTitle_internal() {
		return title_internal;
	}
	public void setTitle_internal(String title_internal) {
		this.title_internal = title_internal;
	}
	public String getLysis_layer2_node_cat() {
		return lysis_layer2_node_cat;
	}
	public void setLysis_layer2_node_cat(String lysis_layer2_node_cat) {
		this.lysis_layer2_node_cat = lysis_layer2_node_cat;
	}
	public List<MP_Display> getDisplay() {
		return display;
	}
	public void setDisplay(List<MP_Display> display) {
		this.display = display;
	}
	public String getLysis_id() {
		return lysis_id;
	}
	public void setLysis_id(String lysis_id) {
		this.lysis_id = lysis_id;
	}
	public String getParental_rating() {
		return parental_rating;
	}
	public void setParental_rating(String parental_rating) {
		this.parental_rating = parental_rating;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public boolean isAd_allowed() {
		return ad_allowed;
	}
	public void setAd_allowed(boolean ad_allowed) {
		this.ad_allowed = ad_allowed;
	}
	public boolean isHd_content() {
		return hd_content;
	}
	public void setHd_content(boolean hd_content) {
		this.hd_content = hd_content;
	}
	public String getOoyala_state() {
		return ooyala_state;
	}
	public void setOoyala_state(String ooyala_state) {
		this.ooyala_state = ooyala_state;
	}
	public String getDeactivated() {
		return deactivated;
	}
	public void setDeactivated(String deactivated) {
		this.deactivated = deactivated;
	}
	@Override
	public String toString() {
		return "MP_Metadata [display_name=" + display_name + ", title_internal=" + title_internal
				+ ", lysis_layer2_node_cat=" + lysis_layer2_node_cat + ", display=" + display + ", lysis_id=" + lysis_id
				+ ", parental_rating=" + parental_rating + ", provider=" + provider + ", ad_allowed=" + ad_allowed
				+ ", hd_content=" + hd_content + ", ooyala_state=" + ooyala_state + ", deactivated=" + deactivated
				+ "]";
	}
}
