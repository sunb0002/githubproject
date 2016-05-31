package com.ufinity.ott.domain;

public class SearchForm {

	private String category;
	private String productid;
	private String productDescription;
	private String siebelnum;
	private String source;
	private String duration;
	private String searchCreationStart;
	private String searchCreationEnd;
	private String exactPrice;
	private String priceGreaterThan;
	private String priceLesserThan;


	public String getExactPrice() {
		return exactPrice;
	}

	public void setExactPrice(String exactPrice) {
		this.exactPrice = exactPrice;
	}

	public String getPriceGreaterThan() {
		return priceGreaterThan;
	}

	public void setPriceGreaterThan(String priceGreaterThan) {
		this.priceGreaterThan = priceGreaterThan;
	}

	public String getPriceLesserThan() {
		return priceLesserThan;
	}

	public void setPriceLesserThan(String priceLesserThan) {
		this.priceLesserThan = priceLesserThan;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getSearchCreationStart() {
		return searchCreationStart;
	}

	public void setSearchCreationStart(String searchCreationStart) {
		this.searchCreationStart = searchCreationStart;
	}

	public String getSearchCreationEnd() {
		return searchCreationEnd;
	}

	public void setSearchCreationEnd(String searchCreationEnd) {
		this.searchCreationEnd = searchCreationEnd;
	}
	
	// Set and Get category
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
    
    // Set and Get Product id
    public String getProductid() {
        return productid;
    }
 
    public void setProductid(String productid) {
        this.productid = productid;
    }
    
    // Set and Get Siebel Part Number
    public String getSiebelnum() {
        return siebelnum;
    }
 
    public void setSiebelnum(String siebelnum) {
        this.siebelnum = siebelnum;
    }
    
    // Set and Get Source
    public String getSource() {
        return source;
    }
 
    public void setSource(String source) {
        this.source = source;
    }
    
    // Set and Get Duration
    public String getDuration() {
        return duration;
    }
 
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
