package com.api.socialmediaapp.error;

public class UnauthorizedException extends RuntimeException{
	
	public UnauthorizedException(String message)
	{
		super(message);
	}

}
