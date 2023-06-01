package com.deneme.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.SpringBoot.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
	
}
