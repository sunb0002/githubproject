package com.ufinity.ott.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

	@JsonProperty
	private String name, type;
	@JsonProperty
	private boolean is_active;
	@JsonProperty
	private List<String> restrict_sites, allow_sites;

	public Device(String name, String type, boolean is_active) {
		super();
		this.name = name;
		this.type = type;
		this.is_active = is_active;
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

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public List<String> getRestrict_sites() {
		return restrict_sites;
	}

	public void setRestrict_sites(List<String> restrict_sites) {
		this.restrict_sites = restrict_sites;
	}

	public List<String> getAllow_sites() {
		return allow_sites;
	}

	public void setAllow_sites(List<String> allow_sites) {
		this.allow_sites = allow_sites;
	}

	@Override
	public String toString() {
		return "Device [name=" + name + ", type=" + type + ", is_active=" + is_active + ", restrict_sites="
				+ restrict_sites + ", allow_sites=" + allow_sites + "]";
	}

}
