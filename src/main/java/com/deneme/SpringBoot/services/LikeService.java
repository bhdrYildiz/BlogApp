package com.deneme.SpringBoot.services;

import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.deneme.SpringBoot.entities.Like;
import com.deneme.SpringBoot.entities.Post;
import com.deneme.SpringBoot.entities.User;
import com.deneme.SpringBoot.repository.LikeRepository;
import com.deneme.SpringBoot.requests.LikeCreateRequest;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService, 
			PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			return likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return likeRepository.findByPostId(postId.get());
		}else
			return likeRepository.findAll();
	}

	public Like createOneLike(LikeCreateRequest request) {
		User user = userService.getOneUser(request.getUserId());
		Post post = postService.getOnePostById(request.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(request.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else 
			return null;
		
	}

	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}

}
