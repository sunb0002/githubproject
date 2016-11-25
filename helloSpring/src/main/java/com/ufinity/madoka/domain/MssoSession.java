package com.ufinity.madoka.domain;

/**
 * @since 2016-07-23
 * @author SunBo
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 * 
 */
public class MssoSession {

	private String session_id, ssoid, site_id, type, source_site_id;
	private long create_date, expire_date;

	public MssoSession() {
	}

	public MssoSession(String session_id, String ssoid, String site_id, String type, String source_site_id,
			long create_date, long expire_date) {
		this.session_id = session_id;
		this.ssoid = ssoid;
		this.site_id = site_id;
		this.type = type;
		this.source_site_id = source_site_id;
		this.create_date = create_date;
		this.expire_date = expire_date;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSsoid() {
		return ssoid;
	}

	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource_site_id() {
		return source_site_id;
	}

	public void setSource_site_id(String source_site_id) {
		this.source_site_id = source_site_id;
	}

	public long getCreate_date() {
		return create_date;
	}

	public void setCreate_date(long create_date) {
		this.create_date = create_date;
	}

	public long getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(long expire_date) {
		this.expire_date = expire_date;
	}

	@Override
	public String toString() {
		return "MssoSession [session_id=" + session_id + ", ssoid=" + ssoid + ", site_id=" + site_id + ", type=" + type
				+ ", source_site_id=" + source_site_id + ", create_date=" + create_date + ", expire_date=" + expire_date
				+ "]";
	}

}
