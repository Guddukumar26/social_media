package com.zosh.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.models.Comment;
import com.zosh.models.Post;
import com.zosh.models.User;
import com.zosh.repository.CommentRepository;
import com.zosh.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user=userService.findByUserId(userId);
		
		Post post=postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment=commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentbyId(Integer commentId) throws Exception {
		
		Optional<Comment> opt=commentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("comment not exit");
		}
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {

		Comment comment=findCommentbyId(commentId);
		User user=userService.findByUserId(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}
