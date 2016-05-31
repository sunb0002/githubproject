package com.ufinity.ott.domain.RequestEntities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppPlayStore_RequestBody {
	
	@JsonProperty("hub_id") // to map with JSON field
	private String hub_id;
	@JsonProperty("assets") // to map with JSON field
	private List<Assets> assets;
	@JsonProperty("device") // to map with JSON field
	private String device;
	@JsonProperty("time") // to map with JSON field
	private String time;
	
	public String getHub_id() {
		return hub_id;
	}
	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}
	public List<Assets> getAssets() {
		return assets;
	}
	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "AppPlayStore_RequestBody [hub_id=" + hub_id + ", assets=" + assets + ", device=" + device + ", time="
				+ time + "]";
	}
}
