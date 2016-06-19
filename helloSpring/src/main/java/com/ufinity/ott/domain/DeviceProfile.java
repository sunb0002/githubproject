package com.ufinity.ott.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceProfile {

	@JsonProperty
	// @NotNull
	private String number, display_name, name, type;
	@JsonProperty
	private boolean is_active, parent_access;
	@JsonProperty
	private List<String> filter_list, restrict_sites, allow_sites;

	public DeviceProfile(String display_name, String type, boolean is_active) {
		this.display_name = display_name;
		this.type = type;
		this.is_active = is_active;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
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

	public boolean isParent_access() {
		return parent_access;
	}

	public void setParent_access(boolean parent_access) {
		this.parent_access = parent_access;
	}

	public List<String> getFilter_list() {
		return filter_list;
	}

	public void setFilter_list(List<String> filter_list) {
		this.filter_list = filter_list;
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
		return "DeviceProfile [number=" + number + ", display_name=" + display_name + ", name=" + name + ", type="
				+ type + ", is_active=" + is_active + ", parent_access=" + parent_access + ", filter_list="
				+ filter_list + ", restrict_sites=" + restrict_sites + ", allow_sites=" + allow_sites + "]";
	}

}
