package com.ufinity.ott.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JPUser {

	@JsonProperty
	private String uuid, full_name;

	@JsonProperty
	private List<Device> devices;

	public JPUser(String uuid) {
		this.uuid = uuid;
	}

	public JPUser(String uuid, String full_name, List<Device> devices) {
		this.uuid = uuid;
		this.full_name = full_name;
		this.devices = devices;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "JPUser [uuid=" + uuid + ", full_name=" + full_name + ", devices=" + devices + "]";
	}

}
