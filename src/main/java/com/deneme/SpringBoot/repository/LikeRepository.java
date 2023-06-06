package com.deneme.SpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.SpringBoot.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{

	List<Like> findByUserIdAndPostId(Long userId, Long postId);

	List<Like> findByUserId(Long userId);

	List<Like> findByPostId(Long postId);
	
}
