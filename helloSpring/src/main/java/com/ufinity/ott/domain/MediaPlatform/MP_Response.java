package com.ufinity.ott.domain.MediaPlatform;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Response {
	@JsonProperty("results") // to map with JSON field
	private List<MP_Result> results; 
	@JsonProperty("total_size") // to map with JSON field
	private int total_size;
	@JsonProperty("page") // to map with JSON field
	private int page;
	@JsonProperty("page_size") // to map with JSON field
	private int page_size;
	
	public MP_Response() {}
	public MP_Response(List<MP_Result> results, int total_size, int page, int page_size) {
		this.results = results;
		this.total_size = total_size;
		this.page = page;
		this.page_size = page_size;
	}
	public List<MP_Result> getResults() {
		return results;
	}
	public void setResults(List<MP_Result> results) {
		this.results = results;
	}
	public int getTotal_size() {
		return total_size;
	}
	public void setTotal_size(int total_size) {
		this.total_size = total_size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	@Override
	public String toString() {
		return "MP_Response [results=" + results + ", total_size=" + total_size + ", page=" + page + ", page_size="
				+ page_size + "]";
	}
}
