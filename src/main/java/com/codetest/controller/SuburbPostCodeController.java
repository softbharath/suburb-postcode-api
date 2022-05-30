package com.codetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codetest.entity.SuburbPostCode;
import com.codetest.model.SearchResultPostCode;
import com.codetest.service.SuburbPostCodeService;

/**
 * Restful API for Suburb Post Code search and save a new record
 * 
 * @author bharathkumar
 *
 */

@RestController
@RequestMapping("/api")
public class SuburbPostCodeController {

	@Autowired
	private SuburbPostCodeService service;
	
	/**
	 * Restful API to search Suburb names based on post code range
	 * 
	 * @param postcode must be 4 digits (Australian postcode)
	 * @return The JSON Response containing list of sorted Suburb names and total number of characters of all Suburb names
	 */
	@GetMapping("/search")
	public ResponseEntity<SearchResultPostCode> searchByPostCodeRange(
			@RequestParam ("from")  String postCodeFrom,
			@RequestParam ("to")  String postCodeTo){

		SearchResultPostCode searchResult = service.searchBySuburbPostCodeRange(postCodeFrom, postCodeTo);
		return ResponseEntity.ok(searchResult);
	}
	
	/**
	 * Add new Suburb Post Code row
	 * 
	 * @param newRow
	 * @return added record
	 */
	@PostMapping("/add")
	public ResponseEntity<SuburbPostCode> addSuburbPostCode(@Validated @RequestBody SuburbPostCode newRow){
		SuburbPostCode suburbPostCode =  service.saveSuburbPostCode(newRow);
		return  new ResponseEntity<>(suburbPostCode, HttpStatus.CREATED);
	}
}
