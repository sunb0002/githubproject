package com.ufinity.madoka.domain.RequestEntities;

import org.hibernate.validator.constraints.NotEmpty;

public class TestInput {

	@NotEmpty
	private String user_id, site_id;

	@NotEmpty
	private String user_password;
	// To add Size restriction in future
	// Somehow I failed to use byte[]. Explore in future.

	public TestInput() {
	}

	public TestInput(String user_id, String user_password, String site_id) {
		this.user_id = user_id;
		this.user_password = user_password;
		this.site_id = site_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	@Override
	public String toString() {
		return "ApiLogin [user_id=" + user_id + ", user_password=" + "***" + ", site_id=" + site_id + "]";
	}

}
