package com.codetest.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codetest.entity.SuburbPostCode;
import com.codetest.exception.SuburbPostCodeAlreadyExistsException;
import com.codetest.exception.SuburbPostCodeNotFoundException;
import com.codetest.model.SearchResultPostCode;
import com.codetest.repository.SuburbPostCodeRepository;

@ExtendWith(MockitoExtension.class)
public class SuburbPostCodeServiceTest {
	@InjectMocks
	private SuburbPostCodeService suburbPostCodeService =  new SuburbPostCodeServiceImpl();
	
	@Mock
	private SuburbPostCodeRepository suburbPostCodeRepository;
	
	@DisplayName("Service: add a new Suburb post code")
	@Nested
	class SaveSuburbPostcodeTests {
		@DisplayName("Add a new Suburb post code postive case")
		@Test
		public void testSaveSuburbPostcode() {
	    	Mockito.when(
	    			suburbPostCodeRepository.findByPostCodeAndSuburbName(any(String.class), any(String.class))).thenReturn(Optional.empty());
	    	SuburbPostCode suburbPostCode = new SuburbPostCode("3000", "SuburbName");
	    	Mockito.when(
	    			suburbPostCodeRepository.save(any(SuburbPostCode.class))).thenReturn(suburbPostCode);
	    	SuburbPostCode actualSuburbPostCode = suburbPostCodeService.saveSuburbPostCode(suburbPostCode);
	    	assertAll(
		    	() -> assertEquals("3000", actualSuburbPostCode.getPostCode()),
		    	() -> assertEquals("SuburbName", actualSuburbPostCode.getSuburbName())
	    	);

		}
		
		@DisplayName("Assert throws 'SuburbPostCodeAlreadyExistsException'")
		@Test
		public void assertThrowsSuburbPostCodeAlreadyExistsException() {
			Optional<SuburbPostCode> mockExisting = Optional.ofNullable(new SuburbPostCode("3000", "ExistingName"));
	    	Mockito.when(
	    			suburbPostCodeRepository.findByPostCodeAndSuburbName(any(String.class), any(String.class))).thenReturn(mockExisting);
			assertThrows(SuburbPostCodeAlreadyExistsException.class,
					() -> suburbPostCodeService.saveSuburbPostCode(new SuburbPostCode("3000", "ExistingName")));
		}
	}
	
	@DisplayName("Service: Search subrub names based on post code Range")
	@Nested
	class searchSuburbnamesPostcodeRangeTests {
		@DisplayName("Service: Search subrub names based on post code Range positive case")
		@Test
		public void testSearchSubrubNameByPostcodeRange() {
			Optional<List<SuburbPostCode>> suburbPostCodeList = Optional.ofNullable(new ArrayList<>());
	    	suburbPostCodeList.get().add(new SuburbPostCode("3000", "name3"));
	    	suburbPostCodeList.get().add(new SuburbPostCode("3000", "name1"));
	    	suburbPostCodeList.get().add(new SuburbPostCode("3001", "name2"));
	
	    	Mockito.when(
	    			suburbPostCodeRepository.findByPostCodeRange(any(String.class),
	    					any(String.class))).thenReturn(suburbPostCodeList);
	    	
	       	SearchResultPostCode srpc = suburbPostCodeService.searchBySuburbPostCodeRange("3000", "3001");
	       	
	    	List<String> expectedNames = new ArrayList<>();
	    	expectedNames.add("name1");
	    	expectedNames.add("name2");
	    	expectedNames.add("name3");
	    	assertAll(
		    	() -> assertEquals(3, srpc.getSuburbNames().size()),
		    	() ->assertEquals(expectedNames, srpc.getSuburbNames()),
		    	() ->assertEquals(15, srpc.getTotalCharsOfSuburbNames())
	    	);
		}
		
		@DisplayName("Assert throws 'SuburbPostCodeNotFoundException'")
		@Test
		public void assertThrowsSuburbPostCodeNotFoundException() {
			Optional<List<SuburbPostCode>> suburbPostCodeList = Optional.empty();
	    	Mockito.when(
	    			suburbPostCodeRepository.findByPostCodeRange(any(String.class),
	    					any(String.class))).thenReturn(suburbPostCodeList);
			assertThrows(SuburbPostCodeNotFoundException.class,
					() -> suburbPostCodeService.searchBySuburbPostCodeRange("3000", "3001"));
		}
	}

}
