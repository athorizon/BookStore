package com.cg.bookstore.exceptions;

public class ListNotFoundException extends RuntimeException {
	public ListNotFoundException(String message)
	{
		super(message);
	}
	public ListNotFoundException()
	{}

}
