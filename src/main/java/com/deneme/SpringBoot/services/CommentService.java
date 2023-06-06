package com.deneme.SpringBoot.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deneme.SpringBoot.entities.Comment;
import com.deneme.SpringBoot.entities.Post;
import com.deneme.SpringBoot.entities.User;
import com.deneme.SpringBoot.repository.CommentRepository;
import com.deneme.SpringBoot.requests.CommentCreateRequest;
import com.deneme.SpringBoot.requests.CommentUpdateRequest;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	
	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
		
		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}else
			return commentRepository.findAll();
	}

	public Comment getOneComment(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public String deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		return "Comment is Deleted ! ";
		
	}

	public Comment createComment(CommentCreateRequest newComment) {
		User user = userService.getOneUser(newComment.getUserId());
		Post post = postService.getOnePostById(newComment.getPostId());
		if(user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(newComment.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(newComment.getText());
			return commentRepository.save(commentToSave);
		}else
			return null;
	}

	public Comment updateComment(Long commentId, CommentUpdateRequest updateComment) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(updateComment.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}
	
	 
}
