package com.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codetest.entity.SuburbPostCode;
import com.codetest.exception.SuburbPostCodeAlreadyExistsException;
import com.codetest.exception.SuburbPostCodeNotFoundException;
import com.codetest.model.SearchResultPostCode;
import com.codetest.repository.SuburbPostCodeRepository;

/**
 * Service implementation for Suburb Post Code search and save a new record
 * 
 * @author bharathkumar
 *
 */

@Service
public class SuburbPostCodeServiceImpl implements SuburbPostCodeService {
    @Value(value = "${suburbPostCode.exception.alreadyExists}")
    private String alreadyExists;
    @Value(value = "${suburbPostCode.exception.notFound}")
    private String notFound;
    
	@Autowired
	private SuburbPostCodeRepository suburbPostCodeRepository;
	
	@Override
	public SearchResultPostCode searchBySuburbPostCodeRange(String postCodeFrom, String postCodeTo) {
		Optional<List<SuburbPostCode>> suburbPostCodeList = suburbPostCodeRepository.findByPostCodeRange(postCodeFrom, postCodeTo);
		if(!suburbPostCodeList.isPresent() || suburbPostCodeList.get().isEmpty()) {
			throw new SuburbPostCodeNotFoundException(notFound);
		}
		List<String> suburbPostCodeNames = SuburbPostCodeService.getOrderBySuburbASC(suburbPostCodeList.get());
		int totalChars = suburbPostCodeNames.stream().mapToInt(String::length).sum();
		return new SearchResultPostCode(suburbPostCodeNames, totalChars);
	}

	@Override
	public SuburbPostCode saveSuburbPostCode(SuburbPostCode suburbPostCode) {
		Optional<SuburbPostCode> suburbPostCodeOpt = suburbPostCodeRepository.findByPostCodeAndSuburbName(
								suburbPostCode.getPostCode(), 
								suburbPostCode.getSuburbName());
		if(suburbPostCodeOpt.isPresent()) {
			throw new SuburbPostCodeAlreadyExistsException("SuburbPostCode already exists");
		}
		return suburbPostCodeRepository.save(suburbPostCode);
	}

}
