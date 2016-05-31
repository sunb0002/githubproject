package com.ufinity.ott.domain.RightsLocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {

	@JsonProperty("offers")
	private Offers[] offers;
	
	public Offers[] getOffers() {
		return offers;
	}
	
	public void setOffers(Offers[] offers) {
		this.offers = offers;
	}
	
	protected List<Offers> getOffersList() {
		return (offers == null) ? (new ArrayList<Offers>())  : Arrays.asList(offers);
	}
	
	public void setOffersbyList(List<Offers> offers_list) {
		Offers[] ofs = new Offers[offers_list.size()];
		this.offers = offers_list.toArray(ofs);
	}

	@Override
	public String toString() {
		return "Content [offers=" + Arrays.toString(offers) + "]";
	}
}
