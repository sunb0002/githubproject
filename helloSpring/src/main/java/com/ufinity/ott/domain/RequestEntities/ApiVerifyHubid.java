package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiVerifyHubid {

	@JsonProperty
	private String hub_id, password;

	public String getHub_id() {
		return hub_id;
	}

	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ApiVerifyHubid [hub_id=" + hub_id + ", password=" + password
				+ "]";
	}

}
