package in.blog.service;

import java.util.List;

import in.blog.binding.userComments;

import in.blog.entity.comments;


public interface UserCommentService {

	public boolean saveComment(userComments userComment);
	
	

	public List<comments> findByPostId();
	public List<comments> findByPostId(Integer postId);
	public void deleteById(Integer postId);
}
