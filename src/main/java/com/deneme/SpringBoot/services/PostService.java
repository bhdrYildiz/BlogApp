package com.deneme.SpringBoot.services;

import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deneme.SpringBoot.entities.Post;
import com.deneme.SpringBoot.entities.User;
import com.deneme.SpringBoot.repository.PostRepository;
import com.deneme.SpringBoot.requests.PostCreateRequest;
import com.deneme.SpringBoot.requests.PostUpdateRequest;
import com.deneme.SpringBoot.responses.PostResponses;


@Service
public class PostService {

	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<PostResponses> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}else {
			list = postRepository.findAll();	
		}
		return list.stream().map(p -> new PostResponses(p)).collect(Collectors.toList());
			
	}

	
	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUser(newPostRequest.getUserId());
		if(user == null) {
			return null;
		}else{
			Post toSave = new Post();
			toSave.setId(newPostRequest.getId());
			toSave.setText(newPostRequest.getText());
			toSave.setTitle(newPostRequest.getTitle());
			toSave.setUser(user);
			return postRepository.save(toSave);
		}
		
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getText());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePost(Long postId) {
		postRepository.deleteById(postId);
		
	}
	
}
