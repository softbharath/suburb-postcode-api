package com.codetest.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.codetest.entity.SuburbPostCode;
import com.codetest.model.SearchResultPostCode;

/**
 * Service for Suburb Post Code search and save a new record
 * 
 * @author bharathkumar
 *
 */
public interface SuburbPostCodeService {
	public SuburbPostCode saveSuburbPostCode(SuburbPostCode suburbPostCode);
	public SearchResultPostCode searchBySuburbPostCodeRange(String postCodeFrom, String postCodeTo);
	
	/**
	 * Sort the Suburb names 
	 * 
	 * @param List of SuburbPostCode
	 * @return Sorted list of Suburb name
	 */
	public static List<String> getOrderBySuburbASC(List<SuburbPostCode> suburbPostCodeList) {
		Comparator<SuburbPostCode> byName = (sp1, sp2) -> sp1.getSuburbName().compareTo(sp2.getSuburbName());
		return suburbPostCodeList.stream()
				.sorted(byName)
				.map(SuburbPostCode::getSuburbName)
				.collect(Collectors.toList());

	}
}