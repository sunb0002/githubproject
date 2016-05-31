package com.ufinity.ott.domain;

/**
 * @author SunBo
 * Copied from eSSO
 */
public class Uuid {
	private String uuid, name, nric, email, mobile, dob, passReminder,
			passAnswer, status, nickName, gender, ssoId, lastLoginTime,
			secondaryEmail, associationStatus, lastModifiedTime;

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

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPassReminder() {
		return passReminder;
	}

	public void setPassReminder(String passReminder) {
		this.passReminder = passReminder;
	}

	public String getPassAnswer() {
		return passAnswer;
	}

	public void setPassAnswer(String passAnswer) {
		this.passAnswer = passAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getAssociationStatus() {
		return associationStatus;
	}

	public void setAssociationStatus(String associationStatus) {
		this.associationStatus = associationStatus;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
	public String toString() {
		return "Uuid [uuid=" + uuid + ", name=" + name + ", nric=" + nric
				+ ", email=" + email + ", mobile=" + mobile + ", dob=" + dob
				+ ", passReminder=" + passReminder + ", passAnswer="
				+ passAnswer + ", status=" + status + ", nickName=" + nickName
				+ ", gender=" + gender + ", ssoId=" + ssoId
				+ ", lastLoginTime=" + lastLoginTime + ", secondaryEmail="
				+ secondaryEmail + ", associationStatus=" + associationStatus
				+ ", lastModifiedTime=" + lastModifiedTime + "]";
	}

}
