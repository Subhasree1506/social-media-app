package com.api.socialmediaapp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.socialmediaapp.model.Comment;
import com.api.socialmediaapp.model.Like;
import com.api.socialmediaapp.model.Post;
import com.api.socialmediaapp.model.PostResponseInputs;
import com.socialmedia.posts.api.model.Comments;
import com.socialmedia.posts.api.model.Likes;
import com.socialmedia.posts.api.model.PostResponse;

public class PostMapper {

	private static Map<Integer, List<Comment>> commentsMap = new HashMap<>();
	private static Map<Integer, List<Like>> likeMap = new HashMap<>();

	public static List<PostResponse> mapToPostResponses(PostResponseInputs postsInput) {

		commentsMap = postsInput.getCommentList();
		likeMap = postsInput.getLikesList();
		return postsInput.getPostList().stream().map(PostMapper::mapToPostResponse).collect(Collectors.toList());
	}

	private static PostResponse mapToPostResponse(Post post) {

		return new PostResponse().postId(post.getPost_id().longValue()).content(post.getContent())
				.comments(mapCommentsToCommentResponses(commentsMap.get(post.getPost_id())))
				.likes(mapLikesToLikeResponses(likeMap.get(post.getPost_id())));
	}

	private static List<Comments> mapCommentsToCommentResponses(List<Comment> comments) {

		List<Comments> list = new ArrayList<>();

		if(comments!=null)
		{
		for (Comment comment : comments) {
			Comments postComments = new Comments();
			postComments.commentId(comment.getComment_id().longValue()).userId(comment.getUser().getUser_id())
					.value(comment.getContent()).postId(comment.getPost().getPost_id().longValue());
			list.add(postComments);
		}
		}
		return list;
	}

	private static List<Likes> mapLikesToLikeResponses(List<Like> likes) {
		List<Likes> list = new ArrayList<>();
		
		if(likes!=null)
		{
		for(Like like : likes)
		{
			Likes postLike = new Likes();
			postLike.postId(like.getPost().getPost_id().longValue())
			.userId(like.getUser().getUser_id()).likeId(like.getLike_id().longValue());
			list.add(postLike);
		}
		}
		return list;
	}

}
