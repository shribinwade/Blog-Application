package in.blog.service;

import java.util.List;

import in.blog.binding.addPost;
import in.blog.entity.Post;

public interface postService {
  
	public boolean savePost(addPost addpost);
	
	public List<Post> dashboardresponse ();
	public List<Post> allposts();
	public List<Post> scposts(String posttitle);
	public boolean delete(Integer postId);

	public Post postById(Integer postId);

	
}
