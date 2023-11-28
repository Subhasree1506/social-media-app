package com.api.socialmediaapp.model;

import java.util.List;
import java.util.Map;

import com.socialmedia.posts.api.model.Likes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseInputs {
	
	List<Post> postList;
	Map<Integer,List<Comment>> commentList;
	Map<Integer,List<Like>> likesList;

}
