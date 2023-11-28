package com.api.socialmediaapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.socialmediaapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	 @Query("SELECT c FROM Comment c WHERE c.post.post_id in :postId")
	    List<Comment> findAllByPostId(@Param("postId") List<Integer> postIds);
   // List<Comment> findAllByPost_PostId(Integer post_Id);

	//List<Comment> findAllByPost_PostId(List<Integer> post_Ids);
}

