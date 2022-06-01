package com.codetest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codetest.entity.SuburbPostCode;

@Repository
public interface SuburbPostCodeRepository extends JpaRepository<SuburbPostCode, Long>{
	public Optional<List<SuburbPostCode>> findByPostCodeOrderBySuburbNameAsc(String postCode);
	
	public Optional<SuburbPostCode> findByPostCodeAndSuburbNameIgnoreCase(String postCode, String suburbName);
	
	public Optional<List<SuburbPostCode>> findByPostCodeBetween(String postCodeFrom, String postCodeTo);
}
