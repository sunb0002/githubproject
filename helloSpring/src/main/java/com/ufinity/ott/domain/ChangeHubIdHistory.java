package com.ufinity.ott.domain;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @since 2015-12-16
 * @author Jason
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 * 
 */
public class ChangeHubIdHistory {

	private long datatime;
	private String hub_id;
	private String new_hub_id;

	public ChangeHubIdHistory() {}

	public ChangeHubIdHistory(String hub_id, String new_hub_id) {
		this.datatime = System.currentTimeMillis() / 1000;
		this.hub_id = hub_id;
		this.new_hub_id = new_hub_id;
	}

	public Object[] getArgumentArray() {
		return new Object[] { this.datatime, this.hub_id, this.new_hub_id };
	}

	public int[] getTypeArray() {
		return new int[] { Types.BIGINT, Types.VARCHAR, Types.VARCHAR };
	}

	public void setDatatime(long datatime) {
		this.datatime = datatime;
	}
	
	public long getDatatime() {
		return datatime;
	}

	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}

	public String getHub_id() {
		return hub_id;
	}

	public void setHub_id_new(String new_hub_id) {
		this.new_hub_id = new_hub_id;
	}

	public String getHub_id_new() {
		return new_hub_id;
	}

	public String getReadableTimeStamp () {
		String stringDateTime = datatime + "";
		long epoch = Long.parseLong(stringDateTime);
		Date timestamp = new Date (epoch * 1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatted = format.format(timestamp);
		return formatted;
	}
	
	@Override
	public String toString() {
		return "ChangeHubIdHistory [datatime=" + datatime + ", hub_id=" + hub_id
				+ ", new_hub_id=" + new_hub_id + "]";
	}

}