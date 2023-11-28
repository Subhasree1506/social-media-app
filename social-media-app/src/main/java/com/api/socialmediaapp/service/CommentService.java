package com.api.socialmediaapp.service;

import java.util.List;

import com.api.socialmediaapp.model.Comment;

public interface CommentService {
	
	public List<Comment> getAllCommentsForPost(Integer postId);

}
