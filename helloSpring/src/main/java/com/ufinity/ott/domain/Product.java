package com.ufinity.ott.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Product {
	private String product_id;
	private String category;
	private double price_net;
	private double discount;
	private int rental_period;
	private long start_time;
	private long end_time;
	private String asset_id;
	private String source;
	private long date_created;
	private long date_modified;
	private String description;
	private String payment_method;
	private long discount_start_time;
	private long discount_end_time;
	private int payment_recurring;
	private long creditcard_start_time;
	private long creditcard_end_time;
	private int gst_enabled;
	private double final_price;
	private String product_type;
	private String promotion_text;
	//private String created_by;
	//private String modified_by;
	private String rental_unit;
	
	public String getRental_unit() {
		return rental_unit;
	}

	public void setRental_unit(String rental_unit) {
		this.rental_unit = rental_unit;
	}

	//public String getCreated_by() {
	//	return created_by;
	//}

	//public void setCreated_by(String created_by) {
	//	this.created_by = created_by;
	//}

	//public String getModified_by() {
	//	return modified_by;
	//}

	//public void setModified_by(String modified_by) {
	//	this.modified_by = modified_by;
	//}

	public String getPromotion_text() {
		return promotion_text;
	}

	public void setPromotion_text(String promotion_text) {
		this.promotion_text = promotion_text;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public double getFinal_price() {
		return final_price;
	}

	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}

	public int getPayment_recurring() {
		return payment_recurring;
	}

	public void setPayment_recurring(int payment_recurring) {
		this.payment_recurring = payment_recurring;
	}

	public long getCreditcard_start_time() {
		return creditcard_start_time;
	}

	public void setCreditcard_start_time(long creditcard_start_time) {
		this.creditcard_start_time = creditcard_start_time;
	}

	public long getCreditcard_end_time() {
		return creditcard_end_time;
	}

	public void setCreditcard_end_time(long creditcard_end_time) {
		this.creditcard_end_time = creditcard_end_time;
	}

	public int getGst_enabled() {
		return gst_enabled;
	}

	public void setGst_enabled(int gst_enabled) {
		this.gst_enabled = gst_enabled;
	}
	
	public long getDate_created() {
		return date_created;
	}

	public void setDate_created(long date_created) {
		this.date_created = date_created;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice_net() {
		return price_net;
	}

	public void setPrice_net(double price_net) {
		this.price_net = price_net;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getRental_period() {
		return rental_period;
	}

	public void setRental_period(int rental_period) {
		this.rental_period = rental_period;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}

	public long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(long date_modified) {
		this.date_modified = date_modified;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public long getDiscount_start_time() {
		return discount_start_time;
	}

	public void setDiscount_start_time(long discount_start_time) {
		this.discount_start_time = discount_start_time;
	}

	public long getDiscount_end_time() {
		return discount_end_time;
	}

	public void setDiscount_end_time(long discount_end_time) {
		this.discount_end_time = discount_end_time;
	}
	
	public Product(){
		
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", category=" + category
				+ ", price_net=" + price_net + ", discount=" + discount
				+ ", rental_period=" + rental_period + ", start_time="
				+ start_time + ", end_time=" + end_time + ", asset_id="
				+ asset_id + ", source=" + source + ", date_created="
				+ date_created + ", date_modified=" + date_modified
				+ ", description=" + description + ", payment_method="
				+ payment_method + ", discount_start_time="
				+ discount_start_time + ", discount_end_time="
				+ discount_end_time + ", payment_recurring="
				+ payment_recurring + ", creditcard_start_time="
				+ creditcard_start_time + ", creditcard_end_time="
				+ creditcard_end_time + ", gst_enabled=" + gst_enabled
				+ ", final_price=" + final_price + ", product_type=" + product_type 
				+ ", promotion_text=" + promotion_text + "]";
	}

	public String toCSVHeader(){
		return "product_id;category;price_net;discount;gst_enabled;final_price;rental_period;start_time;end_time;asset_id;"
				+ "source;date_created;date_modified;description;payment_method;discount_start_time;"
				+ "discount_end_time;payment_recurring;creditcard_start_time;creditcard_end_time;product_type;"
				+ "promotion_text";
	}
	
	public String toCSVContent(){
		return product_id + ";" + category + ";" + price_net + ";"
				+ discount + ";" + gst_enabled + ";" + final_price + ";" + rental_period + ";" + start_time + ";"
				+ end_time + ";" + asset_id + ";" + source + ";"
				+ date_created + ";" + date_modified + ";" + description + ";"
				+ payment_method + ";" + discount_start_time + ";" + discount_end_time + ";"
				+ payment_recurring + ";" + creditcard_start_time + ";" + creditcard_end_time + ";" + product_type + ";"
				+ promotion_text;
	}

	@SuppressWarnings("unused")
	@JsonCreator
	public Product(Map<String,Object> jsonObj){
		//String start = (Number) jsonObj.get("start_time") + "";
		//String end = (Number) jsonObj.get("end_time") + "";
		String price = (Number) jsonObj.get("price_net") + "";
		String priceFinal = (Number) jsonObj.get("price_final") + "";
		//String Discount = (Number) jsonObj.get("discount") + "";
		String rentalperiod = (Number) jsonObj.get("rental_period") + "";
		String gstflag = (Boolean) jsonObj.get("gst_flag") + "";
		String operation = (String) jsonObj.get("type");
		
		/*
		String start = (String) jsonObj.get("start_time") + "";
		String end = (String) jsonObj.get("end_time") + "";
		String price = (String) jsonObj.get("price_net") + "";
		String priceFinal = (String) jsonObj.get("price_final") + "";
		//String Discount = (Number) jsonObj.get("discount") + "";
		String rentalperiod = (String) jsonObj.get("rental_period") + "";
		String gstflag = (String) jsonObj.get("gst_flag") + "";
		String operation = (String) jsonObj.get("type");
		*/
		
		
		this.product_id = (String) jsonObj.get("lysis_id");
		this.category = (String) jsonObj.get("category");
		this.price_net = ParseStringToDoubleLysis(price + "");
		//this.discount = ParseStringToDoubleLysis(Discount);
		this.rental_period = ParseStringToIntegerLysis(rentalperiod + "");
		//this.start_time = ParseStringToLongLysis(start);
		//this.end_time = ParseStringToLongLysis(end);
		this.asset_id = "";
		this.source = "TVOD";
		this.description = (String) jsonObj.get("description");
		this.gst_enabled = SetGSTFlag(gstflag);
		this.payment_method = "Both";

	}
	
	public int SetGSTFlag(String gstflag){
		int flag = 1;
		
		if(gstflag != null && !gstflag.isEmpty()){
			
			if(gstflag.equalsIgnoreCase("true")){
				flag = 1;
			}else{
				flag = 0;
			}
			//flag =  Integer.parseInt(gstflag);
	    }else{
	    	flag = 1;
	    }
		
		return flag;
	}
	
	public long ParseStringToLongLysis(String n){
	    long final_num = 0;
	    if(n != null && !n.isEmpty()){
	    	final_num =  Long.parseLong(n);
	    	// need to parse to date and set timezone? - refer to CC cron job..
	    }else{
	    	final_num = 0;
	    }
	    	
	    return final_num;
	}
	
	public int ParseStringToIntegerLysis(String n){
	    int final_num = 0;
	    if(n != null && !n.isEmpty()){
	    	final_num =  Integer.parseInt(n);
	    }else{
	    	final_num = 0;
	    }
	    	
	    return final_num;
	}
	
	public double ParseStringToDoubleLysis(String n){
	    double final_num = 0.0000;
	    if(n != null && !n.isEmpty()){
	    	String formatted_num = String.format("%.4f", Double.parseDouble(n));
	    	final_num =  Double.parseDouble(formatted_num);
	    }else{
	    	final_num = 0.0000;
	    }
	    	
	    return final_num;
	}
	
	@Override
	public boolean equals(Object p){
		boolean bothEqual = false;
		
		if(p instanceof Product){
			Product that = (Product) p;
			
			if(this.product_id.equals(that.product_id) &&
			   this.category.equals(that.category) &&
			   this.price_net == that.price_net &&
			   this.discount == that.discount &&
			   this.rental_period == that.rental_period &&
			   this.start_time == that.start_time &&
			   this.end_time == that.end_time &&
			   this.source.equals(that.source) &&
			   this.date_modified == that.date_modified &&
			   //this.description.equals(that.description) &&
			   this.gst_enabled == that.gst_enabled){
				
				if((this.description != null && !this.description.isEmpty()) && (that.description != null && !that.description.isEmpty())){
					if(this.description.equals(that.description)){
						bothEqual = true;
					}else{
						bothEqual = false;
					}
				}else if((this.description == null || this.description.isEmpty()) && (that.description == null || that.description.isEmpty())){
					bothEqual = true;
				}else{
					bothEqual = false;
				}
				
			}else{
				bothEqual = false;
			}
		}
		
		return bothEqual;
	}

}
