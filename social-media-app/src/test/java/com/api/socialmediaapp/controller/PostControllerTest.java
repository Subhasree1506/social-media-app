package com.api.socialmediaapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.socialmediaapp.constant.SocialmediaConstant;
import com.api.socialmediaapp.error.BadRequestException;
import com.api.socialmediaapp.service.PostsService;
import com.socialmedia.posts.api.model.Comments;
import com.socialmedia.posts.api.model.Likes;
import com.socialmedia.posts.api.model.PostRequest;
import com.socialmedia.posts.api.model.PostResponse;

class PostControllerTest {

	 	@Mock
	    private PostsService postService;

	    @InjectMocks
	    private PostsController postsController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	@Test
	void createPost_ValidPost_ReturnsCreated() {
		PostRequest postRequest = new PostRequest();
		postRequest.setContent("Valid content");

		when(postService.createPost(postRequest)).thenReturn(ResponseEntity.ok("Post created successfully"));

		ResponseEntity<String> response = postsController.createPost(postRequest);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Post created successfully", response.getBody());

		verify(postService, times(1)).createPost(postRequest);
	}

	@Test
	void createPost_InvalidPost_ThrowsBadRequestException() {
		PostRequest postRequest = new PostRequest();
		postRequest.setContent("Invalid content exceeding limit".repeat(SocialmediaConstant.POST_LENGTH));

		assertThrows(BadRequestException.class, () -> postsController.createPost(postRequest));

		verify(postService, never()).createPost(any());
	}

	@Test
	void getPosts_ReturnsListOfPosts() {
		List<PostResponse> mockPosts = Collections.singletonList(new PostResponse());
		when(postService.getPosts(anyInt(), anyInt())).thenReturn(mockPosts);

		ResponseEntity<List<PostResponse>> response = postsController.getPosts(0, 10);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockPosts, response.getBody());

		verify(postService, times(1)).getPosts(0, 10);
	}

	@Test
	void deletePost_ExistingPostId_ReturnsOk() {
		int postId = 1;
		when(postService.deletePost(postId,1)).thenReturn(ResponseEntity.ok("Post deleted successfully"));

		ResponseEntity<String> response = postsController.deletePost(postId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Post deleted successfully", response.getBody());

		verify(postService, times(1)).deletePost(postId,1);
	}

	@Test
	void getPostById_ExistingPostId_ReturnsPostResponse() {
		int postId = 1;
		PostResponse mockPost = new PostResponse();
		when(postService.getPostById(postId)).thenReturn(mockPost);

		ResponseEntity<PostResponse> response = postsController.getPostById(postId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockPost, response.getBody());

		verify(postService, times(1)).getPostById(postId);
	}

	@Test
	void updatePost_ValidPost_ReturnsOk() {
	    int postId = 1;
	    PostRequest postRequest = new PostRequest();
	    postRequest.setContent("Updated content");

	    when(postService.updatePost(postId, postRequest)).thenReturn(ResponseEntity.ok("Post updated successfully"));

	    ResponseEntity<String> response = postsController.updatePost(postId, postRequest);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Post updated successfully", response.getBody());

	    verify(postService, times(1)).updatePost(postId, postRequest);
	}


	@Test
	void updatePost_InvalidPost_ThrowsBadRequestException() {
		int postId = 1;
		PostRequest postRequest = new PostRequest();
		postRequest.setContent("Invalid content exceeding limit".repeat(SocialmediaConstant.POST_LENGTH));

		assertThrows(BadRequestException.class, () -> postsController.updatePost(postId, postRequest));

		verify(postService, never()).updatePost(anyInt(), any());
	}

	@Test
	void addComment_ValidComment_ReturnsCreated() {
		int postId = 1;
		Comments comment = new Comments();
		comment.setValue("Valid comment");

		when(postService.addComment(String.valueOf(postId), comment)).thenReturn("Comment added successfully");

		ResponseEntity<String> response = postsController.addComment(String.valueOf(postId), comment);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Comment added successfully", response.getBody());

		verify(postService, times(1)).addComment(String.valueOf(postId), comment);
	}

	@Test
	void addComment_InvalidComment_ThrowsBadRequestException() {
		int postId = 1;
		Comments comment = new Comments();
		comment.setValue("Invalid comment exceeding limit".repeat(SocialmediaConstant.COMMENT_LENGTH));

		assertThrows(BadRequestException.class, () -> postsController.addComment(String.valueOf(postId), comment));

		verify(postService, never()).addComment(anyString(), any());
	}

	@Test
	void addLike_ValidLike_ReturnsCreated() {
	    int postId = 1;
	    Likes likes = new Likes();

	    when(postService.addLike(postId, likes)).thenReturn(ResponseEntity.ok("Like added successfully"));

	    ResponseEntity<String> response = postsController.addLike(postId, likes);

	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    assertEquals("Like added successfully", response.getBody());

	    verify(postService, times(1)).addLike(postId, likes);
	}

}
