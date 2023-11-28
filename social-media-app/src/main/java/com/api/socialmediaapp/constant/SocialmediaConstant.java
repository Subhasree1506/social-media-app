package com.api.socialmediaapp.constant;

public class SocialmediaConstant {
	
	public static final String PASSWORD_REGEX ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$]).{8,15}$";
	public static final Integer POST_LENGTH=500;
	public static final Integer COMMENT_LENGTH=500;
	public static final String POST_LENGTH_EXCEEDS_LIMIT="Post length should be below 500 characters";
	public static final String POST_EMPTY="Post content cannot be empty";
	public static final String COMMENT_LENGTH_EXCEEDS_LIMIT="Comment length should be below 500 characters";
	public static final String COMMENT_EMPTY="Comment content cannot be empty";
	public static final String POST_DELETED = "Post Deleted successfully";
	public static final String POST_NOT_FOUND="Post not found";
	public static final String COMMENT_ADDED="Comment added successfully";
	public static final String POST_UPDATED="Post updated successfully";
	public static final String USER_NOT_FOUND="User not found";
	public static final String INVALID_EMAIL_FORMAT="Invalid email format";
	public static final String INVALID_PASSWORD_FORMAT= "Invalid password format";
	public static final String INVALID_PASSWORD= "Invalid password";
	public static final String LIKE_ADDED = "Like added successfully";
	public static final String POST_CREATED="Post created successfully";


	

}
