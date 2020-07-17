package com.cg.bookstore.exceptions;

/*
 * This exception when customers are empty 
 */
public class NoCustomerFoundException extends RuntimeException {

	private String log;
	
	public NoCustomerFoundException(String log)
	{
	 this.log=log;	
	}
	public String getLog()
	{
		return this.log;
	}
}
