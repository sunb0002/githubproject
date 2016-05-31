package com.ufinity.ott.domain.RequestEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiEntitlementSynchronize {

	@JsonProperty
	private String hub_id, type;

	public String getHub_id() {
		return hub_id;
	}

	public void setHub_id(String hUB_ID) {
		hub_id = hUB_ID;
	}

	public String getType() {
		return type;
	}

	public void setType(String tYPE) {
		type = tYPE;
	}

	@Override
	public String toString() {
		return "ApiEntitlementSynchronize [hub_id=" + hub_id + ", type=" + type
				+ "]";
	}

}
