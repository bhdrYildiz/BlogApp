package com.deneme.SpringBoot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.SpringBoot.entities.Comment;
import com.deneme.SpringBoot.requests.CommentCreateRequest;
import com.deneme.SpringBoot.requests.CommentUpdateRequest;
import com.deneme.SpringBoot.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
			@RequestParam Optional<Long> postId){
		return commentService.getAllComments(userId,postId);
	}
	
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneComment(commentId);
	}
	
	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest newComment) {
		return commentService.createComment(newComment);
	}
	
	@PutMapping("update/{commentId}")
	public Comment updateComment(@PathVariable Long commentId,
			@RequestBody CommentUpdateRequest updateComment) {
		return commentService.updateComment(commentId,updateComment);
	}
	
	@PostMapping("delete /{commentId")
	public String deleteComment(@PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return "Comment Deleted !";
	}	
	
}
