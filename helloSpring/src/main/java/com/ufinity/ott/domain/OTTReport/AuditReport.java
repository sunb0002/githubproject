package com.ufinity.ott.domain.OTTReport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditReport {
	private long datetime;
	private String hubid, adminid, action, actionmsg, ip;
	
	public AuditReport() {
	}

	public AuditReport(long datetime, String hubid, String adminid, String action, String actionmsg, String ip) {
		this.datetime = datetime;
		this.hubid = hubid;
		this.adminid = adminid;
		this.action = action;
		this.actionmsg = actionmsg;
		this.ip = ip;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public String getHubid() {
		return hubid;
	}

	public void setHubid(String hubid) {
		this.hubid = hubid;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionmsg() {
		return actionmsg;
	}

	public void setActionmsg(String actionmsg) {
		this.actionmsg = actionmsg;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReadableTimeStamp (long time) {
		String stringTime = time + "";
		long epoch = Long.parseLong(stringTime);
		Date timestamp = new Date (epoch * 1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatted = format.format(timestamp);
		return formatted;
	}
	
	@Override
	public String toString() {
		return "AuditReport [datetime=" + datetime + ", hubid=" + hubid + ", adminid=" + adminid + ", action=" + action
				+ ", actionmsg=" + actionmsg + ", ip=" + ip + "]";
	}
}
