package com.ufinity.ott.domain;

import java.util.List;

public class ProductListWrapper {
	private int STATUS;
	private String MESSAGE;
	private List<Product> ProductList;
	
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
	public List<Product> getProductList() {
		return ProductList;
	}
	public void setProductList(List<Product> productList) {
		ProductList = productList;
	}
	
	@Override
	public String toString() {
		return "ProductListWrapper [STATUS=" + STATUS + ", MESSAGE=" + MESSAGE
				+ ", ProductList=" + ProductList + "]";
	}
	
	

}
