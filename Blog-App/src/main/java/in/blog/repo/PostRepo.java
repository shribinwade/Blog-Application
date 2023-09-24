package in.blog.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.blog.entity.Post;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	@Query("SELECT e FROM Post e WHERE e.user.userId = :userId")
	public  List<Post> findByUserId(Integer userId);
	
	public List<Post> findAllByTitleContaining(String title);
	
	
}
