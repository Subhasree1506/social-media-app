package com.api.socialmediaapp.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.socialmediaapp.constant.SocialmediaConstant;
import com.api.socialmediaapp.error.NotFoundException;
import com.api.socialmediaapp.error.UnauthorizedException;
import com.api.socialmediaapp.model.Comment;
import com.api.socialmediaapp.model.Like;
import com.api.socialmediaapp.model.Post;
import com.api.socialmediaapp.model.PostResponseInputs;
import com.api.socialmediaapp.model.User;
import com.api.socialmediaapp.repo.CommentRepository;
import com.api.socialmediaapp.repo.LikesRepository;
import com.api.socialmediaapp.repo.PostsRepository;
import com.api.socialmediaapp.repo.UserRepository;
import com.api.socialmediaapp.service.PostsService;
import com.api.socialmediaapp.util.PostMapper;
import com.socialmedia.posts.api.model.Comments;
import com.socialmedia.posts.api.model.Likes;
import com.socialmedia.posts.api.model.PostRequest;
import com.socialmedia.posts.api.model.PostResponse;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	PostsRepository postsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	LikesRepository likesRepository;
	
	@Override
	public ResponseEntity<String> createPost(PostRequest postRequest) {

		Optional<User> userData = userRepository.findById(postRequest.getUserId().intValue());
		Post post = new Post();
		post.setUser(userData.get());
		post.setContent(postRequest.getContent());
		postsRepository.save(post);
		return new ResponseEntity<String>("Post created successfully", HttpStatus.CREATED);
	}

	@Override
	public List<PostResponse> getPosts(Integer page, Integer pageSize) {

		Page<Post> res = postsRepository.findAll(PageRequest.of(page, pageSize));

		List<Post> postList = res.getContent();

		List<Integer> postIds = postList.stream().map(Post::getPost_id).collect(Collectors.toList());
		Map<Integer, List<Comment>> commentsMap = getCommentsForPostList(postIds);
		Map<Integer, List<Like>> likesMap = getLikesForPostList(postIds);

		PostResponseInputs postsInput = PostResponseInputs.builder().commentList(commentsMap).likesList(likesMap)
				.postList(postList).build();

		List<PostResponse> response = PostMapper.mapToPostResponses(postsInput);

		return response;
	}

	public Map<Integer, List<Comment>> getCommentsForPostList(List<Integer> postIds) {
		return commentRepository.findAllByPostId(postIds).stream()
				.collect(Collectors.groupingBy(comment -> comment.getPost().getPost_id()));
	}

	public Map<Integer, List<Like>> getLikesForPostList(List<Integer> postIds) {
		return likesRepository.findAllByPostId(postIds).stream()
				.collect(Collectors.groupingBy(like -> like.getPost().getPost_id()));
	}

	@Override
	public ResponseEntity<String> deletePost(Integer postId, Integer userId) {

		Optional<Post> post = postsRepository.findById(postId);
		if (post.isPresent()) {
			if (post.get().getUser().getUser_id() == userId.longValue()) {
				postsRepository.deleteById(postId);
				return new ResponseEntity<String>(SocialmediaConstant.POST_DELETED, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<String>(SocialmediaConstant.USER_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);

			}
		}
		return new ResponseEntity<String>(SocialmediaConstant.POST_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

	@Override
	public PostResponse getPostById(Integer postId) {

		Optional<Post> post = postsRepository.findById(postId);
		List<PostResponse> response;
		if (post.isPresent()) {
			Post result = post.get();
			List<Integer> postIds = Stream.of(result.getPost_id()).collect(Collectors.toList());
			Map<Integer, List<Comment>> commentsMap = getCommentsForPostList(postIds);
			Map<Integer, List<Like>> likesMap = getLikesForPostList(postIds);

			PostResponseInputs postsInput = PostResponseInputs.builder().commentList(commentsMap).likesList(likesMap)
					.postList(Stream.of(result).collect(Collectors.toList())).build();
			response = PostMapper.mapToPostResponses(postsInput);

		} else {
			throw new NotFoundException(SocialmediaConstant.POST_NOT_FOUND);
		}

		return response.get(0);
	}

	@Override
	public ResponseEntity<String> updatePost(Integer postId, PostRequest postRequest) {
		Optional<Post> post = postsRepository.findById(postId);
		Post updatedPost;
		if (post.isPresent()) {
			if (post.get().getUser().getUser_id() == postRequest.getUserId().longValue()) {
				updatedPost = post.get();
				updatedPost.setContent(postRequest.getContent());
				postsRepository.save(updatedPost);
				return new ResponseEntity<String>(SocialmediaConstant.POST_DELETED, HttpStatus.NO_CONTENT);
			} else {
				throw new UnauthorizedException(SocialmediaConstant.USER_UPDATE_UNAUTHORIZED);

			}
		}

		throw new NotFoundException(SocialmediaConstant.POST_NOT_FOUND);

	}

	@Override
	public String addComment(String postId, Comments comment) {

		Optional<Post> post = postsRepository.findById(comment.getPostId().intValue());
		post.ifPresentOrElse(p -> {
			User user = userRepository.findById(comment.getUserId().intValue()).get();
			Comment postComment = Comment.builder().content(comment.getValue()).post(p).user(user).build();
			commentRepository.save(postComment);
		}, () -> {
			throw new NotFoundException(SocialmediaConstant.POST_NOT_FOUND);
		});

		return SocialmediaConstant.COMMENT_ADDED;
	}

	@Override
	public ResponseEntity<String> addLike(Integer postId, Likes likes) {
		Optional<Post> post = postsRepository.findById(postId);

		if (post.isPresent()) {
			User user = userRepository.findById(likes.getUserId().intValue()).get();
			Optional<Like> postLike = likesRepository.findByPostId(postId);

			if (postLike.isPresent()) {
				throw new UnauthorizedException(SocialmediaConstant.LIKE_CONFLICT);

			} else {
				Like newLike = Like.builder().post(post.get()).user(user).build();
				likesRepository.save(newLike);
				return new ResponseEntity<String>(SocialmediaConstant.LIKE_ADDED, HttpStatus.CREATED);

			}

		}
		throw new NotFoundException(SocialmediaConstant.POST_NOT_FOUND);
	}

}
