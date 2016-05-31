package com.ufinity.ott.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufinity.ott.common.config.CacheConfig;
import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.common.utils.BusinessLogicUtils;
import com.ufinity.ott.common.utils.Validators;
import com.ufinity.ott.dao.DBManager;
import com.ufinity.ott.domain.DBQueryInfo;
import com.ufinity.ott.domain.Product;

@Service
public class ProductManager {

    @Autowired
    private DBManager dBManager;

    /**
     * @author SunBo
     * @param product_id
     * @return
     */
	@Transactional(readOnly = true)
	public DBQueryInfo getOTTProduct(String product_id) {
		LogHelper.debug("Retrieving product with product_id: " + product_id);
		return dBManager.getProductbyProductID(product_id);
	}

	/**
     * @author SunBo
     * @param product_id
     * @return
     */
	@Transactional(readOnly = true)
	public DBQueryInfo getOTTProductList(List<String> ProductIDList) {
		LogHelper.debug("Retrieving product with ProductIDList: "
				+ ProductIDList);
		return dBManager.getProductListbyProductIDList(ProductIDList);

	}
	
	
	public List<String> RetrieveAssetIDsFromProduct(Product OTT_Product) {

		List<String> AssetIDs = new ArrayList<String>();
		if (Validators.isNull(OTT_Product) || Validators.isEmpty(OTT_Product.getAsset_id())) {
			return AssetIDs;
		}
		
		JSONArray temparray = null;
		try {
			temparray = new JSONArray(OTT_Product.getAsset_id());
		} catch (JSONException e) {
			LogHelper
					.error("Failed to Parse AssetID with Product, by right shouldnt happen: "
							+ OTT_Product);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (temparray != null) {
			for (int i = 0; i < temparray.length(); i++) {
				AssetIDs.add(temparray.getString(i));
			}
		}

		return AssetIDs;
	}

    @SuppressWarnings("unchecked")
	public List<Product> API_retrive_all_product_info() {
    	LogHelper.info("Dispatching Service Level API: GET all product info");
    	
    	DBQueryInfo dbi = dBManager.getAllProducts();
    	
    	List<Product> prod = new ArrayList<Product>();
    	
    	if (dbi.isOk()) {
    		prod = (List<Product>) dbi.getObject();
    		
    		if(prod.size() == 0){
        		LogHelper.debug("No product data found!");
        	}else if(prod.size() >= 1){
            	LogHelper.debug("Retrieved productList: " + prod.toString());
        	}
    	} else {
			LogHelper.error("Exception occured: " + dbi.getErrorMessage());
		}
    	return prod;
    }

	
	/** 
	* To calculate rental end time 
	* note: SVOD always return 0
	*
	* @param Product, long rentalStartTime
	* @return long rentalEndTime
	*/
	public long calculateRentalEndTime(Product product, long rentalStartTime) {
		
		long rentalEndTime = 0;
		// return rental period in milliseconds
		///long rentalPeriod = product.getRental_period() * 1000L;
		long rentalPeriod = product.getRental_period();
		
		String product_type = BusinessLogicUtils.getProductType(product);
		
		if (product_type.equals("TVOD")) {			
			rentalEndTime = rentalStartTime + rentalPeriod;
		}
		
		System.out.println("RentalStartTime=" + rentalStartTime + ", RentalEndTime=" + rentalEndTime);
		
		return rentalEndTime;		
	}
	
	/** 
	* To convert long unixtime to formatted String 
	* For RightsLocker
	* 
	* @param long unixtime
	* @return String
	*/
	public String convertTimeStampToFormattedString(long unixtime) {
		
		return BusinessLogicUtils.getCurrentTimeStampForRightsLocker(unixtime * 1000L);
	}
	
	/** 
	* Calculate total price in a shopping cart
	*
	* @author Benard
	* @param List of Shopping_Cart
	* @return double
	*/
	public double calculateCartTotalPrice(List<Product> prod) 
		throws Exception {
		
		double final_price = 0;
		
		// loop product list
		ListIterator<Product> litr = prod.listIterator();
		
		while (litr.hasNext()) {
			Product prod_temp = litr.next();
			final_price = final_price + BusinessLogicUtils.calculateFinalPrice(prod_temp, getGSTConfig());
		}
		
		return final_price;		
	}
	
	/**
	 * 
	 * @return
	 * Return GST configuration
	 */
	public String getGSTConfig(){
		return dBManager.getGSTConfig();
	}
	
	/**
     * @author Willkie
     * @param product_id(s)
     * @return
     */
	@Transactional(readOnly = true)
	@Cacheable(CacheConfig.CACHE_OTT_ENTITLEMENT_SYNC_MAPPING_DATA)
	public DBQueryInfo getESMByProductIds(List<String> ProductIDList) {
		LogHelper.debug("Retrieving ESM with ProductIDList: "
				+ ProductIDList);
		return dBManager.getESMByProductIDList(ProductIDList);

	}
}
