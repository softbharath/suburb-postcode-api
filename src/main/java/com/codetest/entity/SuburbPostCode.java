package com.codetest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="suburb_postcode")
public class SuburbPostCode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@NotBlank(message = "Postcode may not be null or blank")
	@Column(name="postcode")
	@Size(max = 6, message = "Maximum number of characters post code is 6")
	private String postCode;
	
	@NotBlank(message = "Suburb name may not be null or blank")
	@Column(name="name")
	@Size(max = 100, message = "Maximum number of characters suburb name is 100")
	private String suburbName;
	
	public SuburbPostCode() {
		super();
	}	
	
	public SuburbPostCode(String postCode, String suburbName) {
		super();
		this.postCode = postCode;
		this.suburbName = suburbName;
	}	
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getSuburbName() {
		return suburbName;
	}
	public void setSuburbName(String suburbName) {
		this.suburbName = suburbName;
	}
}
