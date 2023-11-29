package com.api.socialmediaapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.socialmediaapp.model.Like;

public interface LikesRepository extends JpaRepository<Like, Integer> {

	@Query("SELECT l FROM Like l WHERE l.post.post_id in :postIds")
	List<Like> findAllByPostId(@Param("postIds") List<Integer> postIds);

	@Query("SELECT l FROM Like l WHERE l.post.post_id = :postId")
	Optional<Like> findByPostId(Integer postId);


}
