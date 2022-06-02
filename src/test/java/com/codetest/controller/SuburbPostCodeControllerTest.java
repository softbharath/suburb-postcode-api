package com.codetest.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.codetest.entity.SuburbPostCode;
import com.codetest.model.SearchResultPostCode;
import com.codetest.service.SuburbPostCodeService;

import reactor.core.publisher.Mono;

@ExtendWith({SpringExtension.class})
@WebFluxTest(SuburbPostCodeController.class)
public class SuburbPostCodeControllerTest {

    
    @Autowired
    private WebTestClient webClient;
    
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
    	
       webClient.get().uri("/api/search?from=3000&to=4000")
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody()
        .consumeWith(response ->
        	Assertions.assertThat(response.getResponseBody()).isNotNull())
        .jsonPath("$.totalCharsOfSuburbNames").isEqualTo(15);

    }
    
    @Test
    @DisplayName("add a new Suburb PostCode record")
    void test_addSuburbPostCode() throws Exception {

    	SuburbPostCode suburbPostCode = new SuburbPostCode("3000", "SuburbName");

    	Mockito.when(
    			suburbPostCodeService.saveSuburbPostCode(Mockito.any())).thenReturn(suburbPostCode);
    	
       
        webClient.post().uri("/api/add")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(suburbPostCode), SuburbPostCode.class)
        .exchange()
        .expectStatus().isCreated()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody()
        .consumeWith(response ->
    	Assertions.assertThat(response.getResponseBody()).isNotNull())
        .jsonPath("$.postCode").isEqualTo("3000")
        .jsonPath("$.suburbName").isEqualTo("SuburbName");
    }

}
