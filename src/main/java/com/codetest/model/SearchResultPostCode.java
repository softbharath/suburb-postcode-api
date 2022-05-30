package com.codetest.model;

import java.util.List;

public class SearchResultPostCode {
	private List<String> suburbNames;
	private int totalCharsOfSuburbNames;
	
	public SearchResultPostCode() {
		super();
	}
	
	public SearchResultPostCode(List<String> suburbNames, int totalCharsOfSuburbNames) {
		super();
		this.suburbNames = suburbNames;
		this.totalCharsOfSuburbNames = totalCharsOfSuburbNames;
	}
	
	public List<String> getSuburbNames() {
		return suburbNames;
	}
	
	public void setSuburbNames(List<String> suburbNames) {
		this.suburbNames = suburbNames;
	}
	
	public int getTotalCharsOfSuburbNames() {
		return totalCharsOfSuburbNames;
	}
	
	public void setTotalCharsOfSuburbNames(int totalCharsOfSuburbNames) {
		this.totalCharsOfSuburbNames = totalCharsOfSuburbNames;
	}
}
