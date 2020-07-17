package com.cg.bookstore.entities;

import java.time.LocalDate;

public class ExceptionsResponseDTO {

       public LocalDate dateOfQuery;
	   public String reason;
	   public String statusMessage;
	
	public ExceptionsResponseDTO(LocalDate localDate,String reason, String statusMessage) {
		super();
		this.dateOfQuery = localDate;
		this.reason=reason;
		this.statusMessage = statusMessage;
	}
	
	
	
}
