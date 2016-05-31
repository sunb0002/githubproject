package com.ufinity.ott.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shopping_Cart {
	
	@JsonProperty("txcode") // to map with JSON field 
	private String txcode;
	@JsonProperty("msisdn") // to map with JSON field
	private String msisdn;
	@JsonProperty("hub_id") // to map with JSON field
	private String hub_id;
	@JsonProperty("ua") // to map with JSON field
	private String ua;
	@JsonProperty("ffurl") // to map with JSON field
	private String ffurl;
	@JsonProperty("product_id") // to map with JSON field
	private String product_id;
	@JsonProperty("type") // to map with JSON field
	private String type;
	@JsonProperty("asset_name") // to map with JSON field
	private String asset_name;
	@JsonProperty("asset_url") // to map with JSON field
	private String asset_url;
	@JsonProperty("asset_desc") // to map with JSON field
	private String asset_desc;
	@JsonProperty("asset_img") // to map with JSON field
	private String asset_img;
	@JsonProperty("offer_id") // to map with JSON field
	private String offer_id;
	@JsonProperty("asset_id") // to map with JSON field
	private String asset_id;
	@JsonProperty("isprs") // to map with JSON field
	private int isprs;
	@JsonProperty("device") // to map with JSON field
	private String device;
	@JsonProperty("item_id") // to map with JSON field
	private String item_id;
	@JsonProperty("payment_status") // to map with JSON field
	private String payment_status;
	@JsonProperty("reference_no") // to map with JSON field
	private String reference_no;
	@JsonProperty("payment_amount") // to map with JSON field
	private double payment_amount;

	/**
	 * @return the txcode
	 */
	public String getTxcode() {
		return txcode;
	}

	/**
	 * @param txcode the txcode to set
	 */
	public void setTxcode(String txcode) {
		this.txcode = txcode;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the hub_id
	 */
	public String getHub_id() {
		return hub_id;
	}

	/**
	 * @param hub_id the hub_id to set
	 */
	public void setHub_id(String hub_id) {
		this.hub_id = hub_id;
	}

	/**
	 * @return the ua
	 */
	public String getUa() {
		return ua;
	}

	/**
	 * @param ua the ua to set
	 */
	public void setUa(String ua) {
		this.ua = ua;
	}

	/**
	 * @return the ffurl
	 */
	public String getFfurl() {
		return ffurl;
	}

	/**
	 * @param ffurl the ffurl to set
	 */
	public void setFfurl(String ffurl) {
		this.ffurl = ffurl;
	}

	/**
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the asset_name
	 */
	public String getAsset_name() {
		return asset_name;
	}

	/**
	 * @param asset_name the asset_name to set
	 */
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	/**
	 * @return the asset_url
	 */
	public String getAsset_url() {
		return asset_url;
	}

	/**
	 * @param asset_url the asset_url to set
	 */
	public void setAsset_url(String asset_url) {
		this.asset_url = asset_url;
	}

	/**
	 * @return the asset_desc
	 */
	public String getAsset_desc() {
		return asset_desc;
	}

	/**
	 * @param asset_desc the asset_desc to set
	 */
	public void setAsset_desc(String asset_desc) {
		this.asset_desc = asset_desc;
	}

	/**
	 * @return the asset_img
	 */
	public String getAsset_img() {
		return asset_img;
	}

	/**
	 * @param asset_img the asset_img to set
	 */
	public void setAsset_img(String asset_img) {
		this.asset_img = asset_img;
	}

	/**
	 * @return the asset_id
	 */
	public String getOffer_id() {
		return offer_id;
	}

	/**
	 * @param asset_id the asset_id to set
	 */
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	
	/**
	 * @return the asset_id
	 */
	public String getAsset_id() {
		return asset_id;
	}

	/**
	 * @param asset_id the asset_id to set
	 */
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	/**
	 * @return the isprs
	 */
	public int getIsprs() {
		return isprs;
	}

	/**
	 * @param isprs the isprs to set
	 */
	public void setIsprs(int isprs) {
		this.isprs = isprs;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}

	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	/**
	 * @return the payment_status
	 */
	public String getPayment_status() {
		return payment_status;
	}

	/**
	 * @param payment_status the payment_status to set
	 */
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	/**
	 * @return the reference_no
	 */
	public String getReference_no() {
		return reference_no;
	}

	/**
	 * @param reference_no the reference_no to set
	 */
	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
	}

	/**
	 * @return the payment_amount
	 */
	public double getPayment_amount() {
		return payment_amount;
	}

	/**
	 * @param payment_amount the payment_amount to set
	 */
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}

	/** 
	* To return pre-defined Object format that can be inserted to OTT_GW_TRANSACTION_CART table
	*
	* @author Benard
	* @return Object with Hub_ID, txcode, msisdn, ffurl, unixtimestamp
	*/
	public Object[] getCartInfo() {
		
		Date now = new Date();
		
		return new Object[] {
			this.hub_id, this.txcode, this.msisdn, this.ffurl, this.device, now.getTime()
		};
	}	
	
	/** 
	* To return pre-defined Object format that can be inserted to OTT_GW_TRANSACTION_CART_ITEM table
	*
	* @author Benard
	* @param String (product_type), String (offer_id), String (asset_id)
	* @return Object with Hub_ID, txcode, msisdn, ffurl, unixtimestamp
	*/
	public Object[] getCartItemInfo(String product_type, String offer_id, String asset_id, String type) {
		
		Date now = new Date();
		
		return new Object[] {
			this.hub_id, this.txcode, product_type, this.product_id, this.asset_name, 
			this.asset_url, this.asset_desc, offer_id, asset_id, type, now.getTime()
		};
	}	
	
	/** 
	* To return pre-defined Object format that to be removed from OTT_GW_TRANSACTION_CART table
	*
	* @author Benard
	* @return Object with txcode, asset_id
	*/
	public Object[] retrieveCartItem() {
		
		return new Object[] {
			this.txcode, this.offer_id
		};
	}
	
	/** 
	* To return pre-defined Object format that to be removed from OTT_GW_TRANSACTION_CART table
	*
	* @author Prem
	* @return Object with txcode, asset_id
	*/
	public Object[] retrieveCartItemForRemove() {
		
		return new Object[] {
			this.txcode, this.asset_id
		};
	}

	@Override
	public String toString() {
		return "Shopping_Cart [txcode=" + txcode + ", msisdn=" + msisdn + ", hub_id=" + hub_id + ", ua=" + ua
				+ ", ffurl=" + ffurl + ", product_id=" + product_id + ", type=" + type + ", asset_name=" + asset_name
				+ ", asset_url=" + asset_url + ", asset_desc=" + asset_desc + ", asset_img=" + asset_img + ", offer_id="
				+ offer_id + ", asset_id=" + asset_id + ", isprs=" + isprs + ", device=" + device + ", item_id="
				+ item_id + ", payment_status=" + payment_status + ", reference_no=" + reference_no
				+ ", payment_amount=" + payment_amount + "]";
	}
}
