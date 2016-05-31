package com.ufinity.ott.domain.MediaPlatform;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Result {
	@JsonProperty("id") // to map with JSON field
	private String id;
	@JsonProperty("name") // to map with JSON field
	private String name;
	@JsonProperty("display_name") // to map with JSON field
	private String display_name;
	@JsonProperty("description") // to map with JSON field
	private String description;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("href") // to map with JSON field
	private String href;
	@JsonProperty("metadata") // to map with JSON field
	private MP_Metadata metadata;
	@JsonProperty("error") // to map with JSON field
	private String error;
	//@JsonProperty("images") // to map with JSON field
	//images
	@JsonProperty("offers") // to map with JSON field
	private List<MP_Offers> offers;
	//labels
	//renditions
	@JsonProperty("children") // to map with JSON field
	private List<MP_Children> children;
	//parents
	//collections
	//channels
	public MP_Result() {}
	public MP_Result(String id, String name, String display_name, String description, String type, String href,
			MP_Metadata metadata, String error) {
		this.id = id;
		this.name = name;
		this.display_name = display_name;
		this.description = description;
		this.type = type;
		this.href = href;
		this.metadata = metadata;
		this.error = error;
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
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public MP_Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(MP_Metadata metadata) {
		this.metadata = metadata;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getSize() {
		int sizeNotEmpty = 0;
		
		if (id != null) {
			sizeNotEmpty++;
		}
		if (name != null) {
			sizeNotEmpty++;
		}
		if (display_name != null) {
			sizeNotEmpty++;
		}
		if (description != null) {
			sizeNotEmpty++;
		}
		if (type != null) {
			sizeNotEmpty++;
		}
		if (href != null) {
			sizeNotEmpty++;
		}
		if (metadata != null) {
			sizeNotEmpty++;
		}
		if (error != null) {
			sizeNotEmpty++;
		}
		return sizeNotEmpty;
	}
	
	@Override
	public String toString() {
		return "MP_Result [id=" + id + ", name=" + name + ", display_name=" + display_name + ", description="
				+ description + ", type=" + type + ", href=" + href + ", metadata=" + metadata + ", error=" + error
				+ "]";
	}
	public List<MP_Offers> getOffers() {
		return offers;
	}
	public void setOffers(List<MP_Offers> offers) {
		this.offers = offers;
	}
	public List<MP_Children> getChildren() {
		return children;
	}
	public void setChildren(List<MP_Children> children) {
		this.children = children;
	}
}
