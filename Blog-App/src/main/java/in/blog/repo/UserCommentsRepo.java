package in.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.blog.entity.comments;
@Repository
public interface UserCommentsRepo extends JpaRepository<comments, Integer> {

	
	List<comments> findByPostPostId(Integer postId);
	

}
