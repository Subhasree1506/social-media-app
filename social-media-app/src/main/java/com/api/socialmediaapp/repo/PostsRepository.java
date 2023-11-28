package com.api.socialmediaapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.socialmediaapp.model.Post;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer>{

	Optional<Post> findById(Integer postId);

}
