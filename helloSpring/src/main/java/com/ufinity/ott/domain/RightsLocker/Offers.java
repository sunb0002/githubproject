package com.ufinity.ott.domain.RightsLocker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufinity.ott.common.utils.BusinessLogicUtils;
import com.ufinity.ott.common.utils.Validators;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offers {

	/* content_id = offer_id:<asset_id or collection id>
	   publishing_rule_id - refer to config file
	   external_product_id = PMP product_id 
	*/
	@JsonProperty
	private String content_id, publishing_rule_id, external_product_id,
			start_time, end_time, updated_at;

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getPublishing_rule_id() {
		return publishing_rule_id;
	}

	public void setPublishing_rule_id(String publishing_rule_id) {
		this.publishing_rule_id = publishing_rule_id;
	}

	public String getExternal_product_id() {
		return external_product_id;
	}

	public void setExternal_product_id(String external_product_id) {
		this.external_product_id = external_product_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getStart_time_in_current_timezone() {
		if(Validators.isNotEmpty(start_time)) {
			long unixStartTime = BusinessLogicUtils.getUnixTimeFromRightsLocker(start_time);
			return BusinessLogicUtils.getCurrentTimeStampForRightsLocker(unixStartTime);
		}
		else {
			return "";
		}
	}
	
	public String getEnd_time_in_current_timezone() {
		if(Validators.isNotEmpty(end_time)) {
			long unixEndTime = BusinessLogicUtils.getUnixTimeFromRightsLocker(end_time);
			return BusinessLogicUtils.getCurrentTimeStampForRightsLocker(unixEndTime);
		}
		else {
			return "";
		}
	}

	public String getUpdate_time_in_current_timezone() {
		if(Validators.isNotEmpty(updated_at)) {
			long unixUpdateTime = BusinessLogicUtils.getUnixTimeFromRightsLocker(updated_at);
			return BusinessLogicUtils.getCurrentTimeStampForRightsLocker(unixUpdateTime);
		}
		else {
			return "";
		}
	}
	
	@Override
	public String toString() {
		return "Offers [content_id=" + content_id + ", publishing_rule_id=" + publishing_rule_id
				+ ", external_product_id=" + external_product_id + ", start_time=" + start_time + ", end_time="
				+ end_time + ", updated_at=" + updated_at + "]";
	}
	
}
