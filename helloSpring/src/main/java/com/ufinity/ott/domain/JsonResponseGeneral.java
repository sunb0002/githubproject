package com.ufinity.ott.domain;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufinity.ott.domain.ResponseEntities.HubidInfo;
import com.ufinity.ott.domain.RightsLocker.Offers;

/**
 * 
 * @author SunBo
 * NOTE: NEED TO MANUALLY ADJUST THE getter and setter to lowercase.
 * Make sure the output JSON key values are all in lowercase.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseGeneral {

	private int STATUS;
	private String MESSAGE;
	private List<Offers> OFFERS;
	private String TXCODE;
	private String ProductList;
	private List<Product> ProductInfo;
	private HubidInfo hubid_info;
	

	public JsonResponseGeneral() {
		STATUS = HttpStatus.OK.value();
		MESSAGE = "";
		OFFERS = null;
		TXCODE = null;
		ProductList = null;
		ProductInfo = null;
		hubid_info = null;
	}

	public JsonResponseGeneral(int code, String msg) {
		STATUS = code;
		MESSAGE = msg;
		OFFERS = null;
		TXCODE = null;
		ProductList = null;
		ProductInfo = null;
		hubid_info = null;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	
	public List<Offers> getOFFERS() {
		return OFFERS;
	}

	public void setOFFERS(List<Offers> oFFERS) {
		OFFERS = oFFERS;
	}

	public String getTXCODE() {
		return TXCODE;
	}

	public void setTXCODE(String tXCODE) {
		TXCODE = tXCODE;
	}

	public String getProductList() {
		return ProductList;
	}

	public void setProductList(String productList) {
		ProductList = productList;
	}

	public List<Product> getProductInfo() {
		return ProductInfo;
	}

	public void setProductInfo(List<Product> productInfo) {
		ProductInfo = productInfo;
	}

	public HubidInfo gethubid_info() {
		return hubid_info;
	}

	public void sethubid_info(HubidInfo hubiD_Info) {
		hubid_info = hubiD_Info;
	}

	@Override
	public String toString() {
		return "JsonResponseGeneral [STATUS=" + STATUS
				+ ", MESSAGE=" + MESSAGE + ", OFFERS=" + OFFERS
				+ ", TXCODE=" + TXCODE
				+ ", ProductList=" + ProductList + ", ProductInfo=" + ProductInfo
				+ ", HubiD_Info=" + hubid_info	+ "]";
	}

}
