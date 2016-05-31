package com.ufinity.ott.domain;

import java.util.List;

public class PMP_ProcessInfo {
	private String status;
	private String error_code;
	private String message;
	private Product product;
	private List<Product> productList;
	private String sqlSearch;
	
	public String getSqlSearch() {
		return sqlSearch;
	}

	public void setSqlSearch(String sqlSearch) {
		this.sqlSearch = sqlSearch;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setError_code(String error_code){
		this.error_code = error_code;
	}
	
	public String getError_code(){
		return error_code;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public Product getProduct(){
		return product;
	}

	public void setProductList(List<Product> productList){
		this.productList = productList;
	}
	
	public List<Product> getProductList(){
		return productList;
	}
	
}
