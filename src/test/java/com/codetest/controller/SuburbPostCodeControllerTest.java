package com.codetest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.codetest.entity.SuburbPostCode;
import com.codetest.model.SearchResultPostCode;
import com.codetest.service.SuburbPostCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({SpringExtension.class})
@WebMvcTest(SuburbPostCodeController.class)
@AutoConfigureMockMvc
public class SuburbPostCodeControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
	private SuburbPostCodeService suburbPostCodeService;
    
    @Test
    @DisplayName("Search by PostCode Range test")
    void test_searchByPostCodeRange() throws Exception {
    	List<String> names = new ArrayList<>();
    	names.add("name1");
    	names.add("name2");
    	names.add("name3");
    	int totalChars = 15;
    	SearchResultPostCode srpc = new SearchResultPostCode(names, totalChars);


    	Mockito.when(
    			suburbPostCodeService.searchBySuburbPostCodeRange(Mockito.anyString(),
    					Mockito.anyString())).thenReturn(srpc);
    	
        final String expectedResponseContent = objectMapper.writeValueAsString(srpc);

        mvc.perform(get("/api/search?from=3000&to=4000").contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
       			.andExpect(content().json(expectedResponseContent));

    }
    
    @Test
    @DisplayName("add a new Suburb PostCode record")
    void test_addSuburbPostCode() throws Exception {

    	SuburbPostCode suburbPostCode = new SuburbPostCode("3000", "SuburbName");

    	Mockito.when(
    			suburbPostCodeService.saveSuburbPostCode(Mockito.any())).thenReturn(suburbPostCode);
    	
        final String expectedResponseContent = objectMapper.writeValueAsString(suburbPostCode);

        mvc.perform(post("/api/add").contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON)
        		.content(expectedResponseContent))
        		.andExpect(status().isCreated())
       			.andExpect(content().json(expectedResponseContent));
    }

}
