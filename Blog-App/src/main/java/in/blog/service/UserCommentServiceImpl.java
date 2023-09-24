package in.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.blog.binding.userComments;
import in.blog.entity.Post;
import in.blog.entity.comments;
import in.blog.repo.UserCommentsRepo;

@Service
public class UserCommentServiceImpl implements UserCommentService {

	@Autowired
	UserCommentsRepo userComments;

	@Autowired
	HttpSession session;

	@Override
	public boolean saveComment(userComments userComment) {
		// TODO Auto-generated method stub
		System.out.println("userComment");
		comments uc = new comments();

		BeanUtils.copyProperties(userComment, uc);

		comments save = userComments.save(uc);

		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<comments> findByPostId() {
		// TODO Auto-generated method stub
		Post post = (Post) session.getAttribute("postToComment");
		Integer postId = post.getPostId();
		List<comments> findByPostId = userComments.findByPostPostId(postId);

		return findByPostId;
	}

	

	@Override
	public List<comments> findByPostId(Integer postId) {
		// TODO Auto-generated method stub
		List<comments> findByPostId = userComments.findByPostPostId(postId);

		return findByPostId;
		
	}

	@Override
	@Transactional
	public void deleteById(Integer postId) {
		userComments.deleteById(postId);
	}

}
