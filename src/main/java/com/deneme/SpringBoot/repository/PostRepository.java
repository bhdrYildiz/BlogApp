package com.deneme.SpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.SpringBoot.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

	List<Post> findByUserId(Long userId);

}
