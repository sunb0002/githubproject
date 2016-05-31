package com.ufinity.ott.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ufinity.ott.common.config.ConfigManager;
import com.ufinity.ott.common.cons.Constant;
import com.ufinity.ott.domain.Product;

public class BusinessLogicUtils {
	
	
	/** 
	* Logic to differentiate TVOD and SVOD Product
	*
	* @param Product 
	* @return String
	*/
	public static String getProductType(Product product) {
		
		String product_type = "";
		
		/* get product_type logic
		 * if source is Lysis and non recurring payment then is TVOD
		 * else SVOD
		 */
		switch (product.getSource()) {
			case "Commercial_Catalog": {
				product_type = "SVOD";
				break;
			}
			case "Lysis": {
				product_type = "TVOD";
				break;
			}
			case "PMP": {
				if (product.getPayment_recurring() == 1) {
					product_type = "SVOD";
				} else {
					product_type = "TVOD";
				}
				break;
			}
			case "Bulk Child Cable TV Residential": {
				product_type = "SVOD";
				break;
			}
			default: {
				product_type = "TVOD";
				break;
			}
		}
		
		return product_type;
	}
	
	/*
	 * To validate the incoming social media calls. The calls are invalid after
	 * 15min (configurable).
	 * 
	 * 2015-Feb-02 Fix: If input.length() == 10, validate with seconds instead of milliseconds.
	 */
	public static boolean Expired(String Timestamp) {
		boolean result = true;

		if (Validators.isEmpty(Timestamp)) {
			System.out.println("Invalid Time Stamp");
			return result;
		}

		try {

			long incomingtime = Long.parseLong(Timestamp);
			System.out.println("Recieved Time: " + incomingtime);

			String expirycount = ConfigManager.getConfig("Social_TimeStampMinutes");
			long expirycount_long = Long.parseLong(expirycount);
			long expirytime = incomingtime + expirycount_long * 60;
			System.out.println("Expiry Time: " + expirytime);
		
			long currenttime = System.currentTimeMillis() / 1000;
			System.out.println("Current Time: " + currenttime);

			result = (currenttime > expirytime);

			System.out.println(result ? "Expired" : "NotExpired");

		} catch (Exception ex) {
			System.out.println("Social_TimeStampChecker exception.");
			ex.printStackTrace();
		}
		return result;

	}
	
	/**
	  * 
	  * @param product
	  * @param old_product
	  * @return
	  * Get the fields updated for PMP object
	  */
	 public static String GetUpdatedFields(Product product, Product old_product, String requestSource){
		 String line = "";
		 
		if(product.getPrice_net() != old_product.getPrice_net()){
			line = line + "\nPrice : " + product.getPrice_net();
		}
			
		if(product.getDiscount() != old_product.getDiscount()){
			line = line + "\nDiscount : " + product.getDiscount();
		}
			
		if(product.getRental_period() != old_product.getRental_period()){
			if(product.getRental_unit() != null && !product.getRental_unit().isEmpty()){
				if(product.getRental_unit().equals("seconds")){
					line = line + "\nRental Period : " + product.getRental_period() + " seconds";
				}else if(product.getRental_unit().equals("minutes")){
					line = line + "\nRental Period : " + (product.getRental_period() / 60) + " minutes";
				}else if(product.getRental_unit().equals("hours")){
					line = line + "\nRental Period : " + (product.getRental_period() / (60 * 60)) + " hours";
				}else if(product.getRental_unit().equals("days")){
					line = line + "\nRental Period : " + (product.getRental_period() / (60 * 60 * 24)) + " days";
				}
			}else{
				line = line + "\nRental Period : " + product.getRental_period() + " seconds";
			}
		}
			
		if(product.getPayment_method() != null && !product.getPayment_method().isEmpty() && old_product.getPayment_method() != null && !old_product.getPayment_method().isEmpty()){
			if(!(product.getPayment_method().equals(old_product.getPayment_method()))){
				line = line + "\nPayment Method : " + product.getPayment_method();
			}
		}

		if(product.getDiscount_start_time() != old_product.getDiscount_start_time()){
			java.util.Date timestamp = new java.util.Date(product.getDiscount_start_time() * 1000);
			line = line + "\nDiscount Start Time : " + timestamp;
		}
		
		if(product.getDiscount_end_time() != old_product.getDiscount_end_time()){
			java.util.Date timestamp = new java.util.Date(product.getDiscount_end_time() * 1000);
			line = line + "\nDiscount End Time : " + timestamp;
		}
	
		if(product.getGst_enabled() != old_product.getGst_enabled()){
			if(product.getGst_enabled() == 1){
				line = line + "\nInclude GST : Yes";
			}else{
				line = line + "\nInclude GST : No";
			}
		}
			
		//if(product.getFinal_price() != old_product.getFinal_price()){
		//	line = line + "\nFinal Price : " + product.getFinal_price();
		//}
			
		if(product.getDescription() != null && !product.getDescription().isEmpty() && old_product.getDescription() != null && !old_product.getDescription().isEmpty()){
			if(!(product.getDescription().equals(old_product.getDescription()))){
				line = line + "\nProduct Description : " + product.getDescription();
			}
		}else if((product.getDescription() == null || product.getDescription().isEmpty()) && (old_product.getDescription() == null || old_product.getDescription().isEmpty())){
			//Both are empty / null do nothing.
		}else{
			// Here, either one of the description (new or previous one) is null, which makes them unequal and
			// therefore need to update!
			// Scenario:
			// 1. new product description is null but old product description is not null.
			// 2. new product description is not null but old product description is null.
			line = line + "\nProduct Description : " + product.getDescription();
		}
		
		if(requestSource.equals("PMP")){	
		
			if(product.getPromotion_text() != null && !product.getPromotion_text().isEmpty() && old_product.getPromotion_text() != null && !old_product.getPromotion_text().isEmpty()){
				if(!(product.getPromotion_text().equals(old_product.getPromotion_text()))){
					line = line + "\nPromotion Description : " + product.getPromotion_text();
				}
			}else if((product.getPromotion_text() == null || product.getPromotion_text().isEmpty()) && (old_product.getPromotion_text() == null || old_product.getPromotion_text().isEmpty())){
				//Both are empty / null do nothing.
			}else{
				// Here, either one of the promotion text (new or previous one) is null, which makes them unequal and
				// therefore need to update!
				// Scenario:
				// 1. new promotion description is null but old promotion description is not null.
				// 2. new promotion description is not null but old promotion description is null.
				line = line + "\nPromotion Description : " + product.getPromotion_text();
			}
		}
		
		if(product.getPrice_net() != old_product.getPrice_net() || 
		   product.getDiscount() != old_product.getDiscount() ||
		   product.getGst_enabled() != old_product.getGst_enabled()){
			line = line + "\n(New Final Price : " + product.getFinal_price() + ")";
		   }
		
		return line;
	
	 }
	 
	/**
	 * @author Prem
	 * @param timestamp
	 * @return
	 * @throws Exception
	 * Get the unix timestamp of a given date
	 */
	 public static long getUnixTimestamp(String timestamp) throws Exception{ 
		 long unixTimestamp = 0;
			  
		 if(timestamp != null && !timestamp.isEmpty()){
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 Date ts = sdf.parse(timestamp);
			 unixTimestamp = ts.getTime() / 1000;
		 }
			 
		 return unixTimestamp;
	 }
	 
	 /**
	  * 
	  * @param ts
	  * @return
	  * Format unix timestamp into readable timestamp
	  */
	 public static String ReturnReadableTimestamp(long ts){
			Date timestamp = new Date(ts * 1000L);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// need to set timezone? - refer to CC cron job..
			String formattedTimestamp = sdf.format(timestamp);
			return formattedTimestamp;
	}
	 
	 /** 
	* To calculate final price for a product
	*
	* @param Product 
	* @return double Final Price
	*/
	public static double calculateFinalPrice(Product product, String gst) {
			
		double final_price = product.getPrice_net();
			
		double discount_percentage = applyDiscount(product);
			
		if (discount_percentage > 0) {
			// apply discount
			final_price = final_price * ((100 - discount_percentage)/100);
		}
			
		if (product.getGst_enabled() == 1) {
			// apply GST
			//String gst = pmp_dBManager.getGSTConfig();
			double gst_value = Double.parseDouble(gst);
			final_price = final_price * ((100 + gst_value)/100);
		} 
			
		// round to two decimal place
		final_price = Math.round(final_price*100.0)/100.0;
			
		return final_price;
	}
	
	/** 
	* To check whether this product apply discount during this period
	*
	* @param Product 
	* @return double discount percentage
	*/
	public static double applyDiscount(Product product) {
		
		double discount_percentage = 0;
		
		// get offer start date and end date
		long unix_offerstartdate = product.getDiscount_start_time();
		long unix_offerenddate = product.getDiscount_end_time();
		
		Date offer_startdate = new Date(unix_offerstartdate * 1000L); // *1000 is to convert seconds to milliseconds
		Date offer_enddate = new Date(unix_offerenddate * 1000L); // *1000 is to convert seconds to milliseconds
		
		Date current_date = new Date();
		
		if (current_date.compareTo(offer_startdate) >= 0 && 
				current_date.compareTo(offer_enddate) <=0) {
			// within promotion period, apply promotion
			discount_percentage = product.getDiscount();
		}
				
		return discount_percentage;		
	}
	
	public static long getUnixTimeFromRightsLocker (String rightslockertime) {
		
		String pattern = Constant.RIGHTSLOCKER_DATEPATTERN;
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    long unixTime = 0L;
		try {
			Date temp_date = format.parse(rightslockertime);
			unixTime = temp_date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return unixTime;
	}
	
	/**
	 * @param unixtime_milseconds
	 * 
	 */
	public static String getCurrentTimeStampForRightsLocker(long unixtime_milseconds) {

		String pattern = Constant.RIGHTSLOCKER_DATEPATTERN;
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		
	    Date temp_date = new Date (unixtime_milseconds);
	    String finalTime = format.format(temp_date);
	    
		return finalTime;
	}
	
	public static String getCurrentTimeStampForRightsLocker() {
		return getCurrentTimeStampForRightsLocker(System.currentTimeMillis());
	}
	
	/*
	public static boolean CheckIfOfferNeedToBeRenamed(String offerid){
		boolean need_to_rename = false;
		String line = "";
		int count = 0;
		File input = new File(ConfigManager.getConfig("Offers_to_be_renamed"));
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(input));
			
			while ((line = in.readLine()) != null) {
			
			}
			in.close();
		}
		catch(Exception e){
			
		}
		
		return need_to_rename;
	}
	*/
	
	public static String removeSpecialCharactersFromHubId(String hubid) {
		if (Validators.isNotEmpty(hubid)) {
			String Str = new String(hubid);
			String regex = " ";
			return Str.replaceAll(regex, "");
		}
		else {
			return "";
		}
	}
		
}