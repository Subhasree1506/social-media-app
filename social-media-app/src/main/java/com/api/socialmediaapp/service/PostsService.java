package com.api.socialmediaapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.socialmediaapp.model.Comment;
import com.api.socialmediaapp.model.Like;
import com.api.socialmediaapp.model.Post;
import com.socialmedia.posts.api.model.Comments;
import com.socialmedia.posts.api.model.Likes;
import com.socialmedia.posts.api.model.PostRequest;
import com.socialmedia.posts.api.model.PostResponse;

public interface PostsService {
	
	public ResponseEntity<String> createPost(PostRequest postRequest);
	public List<PostResponse> getPosts(Integer page, Integer pageSize);
	public ResponseEntity<String> deletePost(Integer postId);
	public PostResponse getPostById(Integer postId);
	public String updatePost(Integer postId, PostRequest postRequest);
	public String addComment(String postId, Comments comment);
	public String addLike(Integer postId, Likes likes);

}
