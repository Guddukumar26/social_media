package com.zosh.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.models.Post;
import com.zosh.models.User;
import com.zosh.repository.PostRepository;
import com.zosh.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user=userService.findByUserId(userId);
		
		Post newPost=new Post();
		 
		newPost.setImage(post.getImage());
		newPost.setCaption(post.getCaption());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(user);
		
		postRepository.save(newPost);
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findByUserId(userId);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("You can't delete another user post");
		}
		postRepository.delete(post);
		return "post deleted sucessfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {

		Optional<Post> opt=postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post not found with id:"+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) throws Exception {

		Post post=findPostById(postId);
		User user=userService.findByUserId(userId);
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else user.getSavedPost().add(post);
		
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findByUserId(userId);
		
		if(post.getLiked().contains(post)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);
	}

}
