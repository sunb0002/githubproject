package com.ufinity.ott.domain.RightsLocker;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RLEntity {

	@JsonProperty
	private String pcode;
	@JsonProperty
	private String account_id;
	@JsonProperty("content")
	private Content content;

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "RLEntity [pcode=" + pcode + ", account_id=" + account_id
				+ ", content=" + content + "]";
	}

	public List<Offers> getOffersList() {

		return (content == null) ? (new ArrayList<Offers>()) : content
				.getOffersList();
	}

	public void setOffersbyList(List<Offers> off) {
		this.content = new Content();
		this.content.setOffersbyList(off);
	}
}
