package com.api.socialmediaapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.socialmediaapp.constant.SocialmediaConstant;
import com.api.socialmediaapp.error.BadRequestException;
import com.api.socialmediaapp.service.PostsService;
import com.socialmedia.posts.api.PostsApi;
import com.socialmedia.posts.api.model.Comments;
import com.socialmedia.posts.api.model.Likes;
import com.socialmedia.posts.api.model.PostRequest;
import com.socialmedia.posts.api.model.PostResponse;

@RestController
@RequestMapping("/api")
public class PostsController implements PostsApi {

    private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    PostsService postService;

    @Override
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        isValidPost(postRequest.getContent());
        postService.createPost(postRequest);
        logger.info("Post created successfully");
        return new ResponseEntity<>(SocialmediaConstant.POST_CREATED, HttpStatus.CREATED);
    }

    public void isValidPost(String content) {
        if (content.length() > SocialmediaConstant.POST_LENGTH) {
            throw new BadRequestException(SocialmediaConstant.POST_LENGTH_EXCEEDS_LIMIT);
        } else if (content.isBlank() || content.isEmpty()) {
            throw new BadRequestException(SocialmediaConstant.POST_EMPTY);
        }
    }

    @Override
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        logger.info("Fetching posts. Page: {}, PageSize: {}", page, pageSize);
        return new ResponseEntity<>(postService.getPosts(page, pageSize), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        String message = postService.deletePost(postId).getBody();
        logger.info(message);
        return ResponseEntity.ok(message);
    }

    @Override
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Integer postId) {
        PostResponse postResponse = postService.getPostById(postId);
        logger.info("Retrieved post by ID: {}", postId);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Override
    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Integer postId, @RequestBody PostRequest postRequest) {
        isValidPost(postRequest.getContent());
        String message = postService.updatePost(postId, postRequest);
        logger.info(message);
        return ResponseEntity.ok(message);
    }

    @Override
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addComment(@PathVariable String postId, @RequestBody Comments comment) {
        if (comment.getValue().length() > SocialmediaConstant.COMMENT_LENGTH) {
            throw new BadRequestException(SocialmediaConstant.COMMENT_LENGTH_EXCEEDS_LIMIT);
        } else if (comment.getValue().isBlank() || comment.getValue().isEmpty()) {
            throw new BadRequestException(SocialmediaConstant.COMMENT_EMPTY);
        }
        String message = postService.addComment(postId, comment);
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @Override
    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<String> addLike(@PathVariable Integer postId, @RequestBody Likes likes) {
        String message = postService.addLike(postId, likes);
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
