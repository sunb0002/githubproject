package com.ufinity.ott.domain.MediaPlatform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Children {
	@JsonProperty("id") // to map with JSON field
	private String id;
	@JsonProperty("name") // to map with JSON field
	private String name;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("href") // to map with JSON field
	private String href;
	
	public MP_Children(String id, String name, String type, String href) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.href = href;
	}
	
	public MP_Children() {}

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
	
	
}
