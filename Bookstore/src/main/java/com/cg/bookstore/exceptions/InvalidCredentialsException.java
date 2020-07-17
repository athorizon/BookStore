package com.cg.bookstore.exceptions;

/*
 * 
 * This class is required when admin credentials are invalid
 */
public class InvalidCredentialsException extends RuntimeException {

private String log;
	
	public InvalidCredentialsException(String log)
	{
	 this.log=log;	
	}
	public String getLog()
	{
		return this.log;
	}
}
