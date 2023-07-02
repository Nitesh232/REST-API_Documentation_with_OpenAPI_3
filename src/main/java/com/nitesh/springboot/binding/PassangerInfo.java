package com.nitesh.springboot.binding;

import lombok.Data;

@Data
public class PassangerInfo {
	
	//private Integer passangerId;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private Long contactNumber;
	
	private String toStation;
	
	private String fromStation;
	
	private String journeyDate;
	
	private Integer ticketCount;
	

}
