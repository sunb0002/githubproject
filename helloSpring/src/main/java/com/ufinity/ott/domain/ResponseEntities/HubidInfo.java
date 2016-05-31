package com.ufinity.ott.domain.ResponseEntities;

public class HubidInfo {

	private String uuid, name, dob, status, nick_name, gender, email;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "HubidInfo [uuid=" + uuid + ", name=" + name + ", dob=" + dob
				+ ", status=" + status + ", nick_name=" + nick_name
				+ ", gender=" + gender + ", email=" + email + "]";
	}

}
