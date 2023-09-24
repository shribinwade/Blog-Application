package in.blog.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.blog.binding.addPost;
import in.blog.entity.Post;
import in.blog.entity.User;
import in.blog.repo.PostRepo;
import in.blog.repo.userRepo;

@Service
public class postServiceImpl implements postService {

	@Autowired
	PostRepo postrepo;

	@Autowired
	userRepo userrepo;

	@Autowired
	HttpSession session;

	@Override
	public boolean savePost(addPost addpost) {
		// TODO Auto-generated method stub
		Post postentity = new Post();
        
		BeanUtils.copyProperties(addpost, postentity);
          
		Integer userId = (Integer) session.getAttribute("userId");

		User user = userrepo.findById(userId).get();
		
		
		postentity.setUser(user);
		
		Post save = postrepo.save(postentity);

		if (null != save) {
			return true;
		}
		return false;
	}

	@Override
	public List<Post> dashboardresponse() {
		// TODO Auto-generated method stub
	      Integer userId = (Integer) session.getAttribute("userId");
		  List<Post> findByUserId = postrepo.findByUserId(userId);
		
		  return findByUserId;
		
		
	}

	@Override
	public List<Post> allposts() {
		// TODO Auto-generated method stub
		  List<Post> findAll = postrepo.findAll();
		return findAll;
	}

	@Override
	public List<Post> scposts(String title) {
		List<Post> findAllByTitle = postrepo.findAllByTitleContaining(title);
		return findAllByTitle;
	}
    @Override
	public boolean delete(Integer postId) {
		 postrepo.deleteById(postId);
		return true;
	}
	@Override
	public Post postById(Integer postId){
		Optional<Post> postById = postrepo.findById(postId);
		if(postById.isPresent()) {
			Post post = postById.get();
			return post;
		}
		return null;
		
	}

}
