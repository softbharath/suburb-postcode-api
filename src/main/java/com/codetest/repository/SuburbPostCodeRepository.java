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
	
	@Query("select sp from SuburbPostCode sp where sp.postCode = :postCode and  lower(sp.suburbName) = lower(:suburbName)")
	public Optional<SuburbPostCode> findByPostCodeAndSuburbName(String postCode, String suburbName);
	
	@Query("select sp from SuburbPostCode sp where sp.postCode between :postCodeFrom and :postCodeTo")
	public Optional<List<SuburbPostCode>> findByPostCodeRange(String postCodeFrom, String postCodeTo);
}
