package com.cg.bookstore.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message)
	{
		super(message);
	}
	public UserNotFoundException()
	{}

}
