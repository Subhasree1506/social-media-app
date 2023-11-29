package com.api.socialmediaapp.error;

public class NotFoundException extends RuntimeException{

	public NotFoundException(String message)
	{
		super(message);
	}
}
