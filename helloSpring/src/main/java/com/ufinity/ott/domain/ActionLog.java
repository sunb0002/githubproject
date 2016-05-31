package com.ufinity.ott.domain;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufinity.ott.common.utils.Validators;

/**
 * @since 2015-10-27
 * @author SunBo
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 * 
 */
public class ActionLog {

	private long datatime;
	private String hub_id;
	private String admin_id;
	private String action;

	// Leave this field as String but hack its getter+setter
	// in order to enforce JSON format as String
	private String msg;

	private String ip;

	public ActionLog() {}

	public ActionLog(String hub_id, String admin_id, String action,
			ActionLogMSG MSGObj, String ip) {
		this.datatime = System.currentTimeMillis() / 1000;
		this.hub_id = hub_id;
		this.admin_id = admin_id;
		this.action = action;
		this.setMsg(MSGObj);
		this.ip = ip;
	}

	/**
	 * 
	 */
	public Object[] getArgumentArray() {
		return new Object[] { this.datatime, this.hub_id, this.admin_id,
				this.action, this.msg, this.ip };
	}

	/**
	 * 
	 */
	public int[] getTypeArray() {
		return new int[] { Types.BIGINT, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
	}

	public long getDatatime() {
		return datatime;
	}

	public void setDatatime(long datatime) {
		this.datatime = datatime;
	}

	public String getHub_id() {
		return hub_id;
	}

	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Parse JSON format String to get ActionLogMSG
	 */
	public ActionLogMSG getMsg() {

		if (Validators.isNotNull(this.msg)) {
			try {
				return new ObjectMapper().readValue(this.msg,
						ActionLogMSG.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Convert ActionLogMSG into a JSON format String
	 */
	public void setMsg(ActionLogMSG MSGObj) {

		String MSG = "";
		if (Validators.isNotNull(MSGObj)) {
			JSONObject jb = new JSONObject(MSGObj);
			MSG = jb.toString();
		}

		this.msg = MSG;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReadableTimeStamp () {
		String stringDateTime = datatime + "";
		long epoch = Long.parseLong(stringDateTime);
		Date timestamp = new Date (epoch * 1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatted = format.format(timestamp);
		return formatted;
	}
	
	public String getFromMessage (String key1, String key2) {
		String returnItem = "";
		String msg = this.msg;
		if (!msg.isEmpty()) {
			JSONObject jOb = new JSONObject(msg);
			if (jOb.has(key1)) {
				JSONObject key1_obj = jOb.getJSONObject(key1);
				if (key1_obj.has(key2)) {
					returnItem = (String) key1_obj.get(key2);
				}
			}
		}
		
		return returnItem;
	}
	
	@Override
	public String toString() {
		return "ActionLog [datatime=" + datatime + ", hub_id=" + hub_id
				+ ", admin_id=" + admin_id + ", action=" + action + ", msg="
				+ msg + ", ip=" + ip + "]";
	}

}
