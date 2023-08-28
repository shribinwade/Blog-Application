package in.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.blog.entity.User;
@Repository
public interface userRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	public User findByEmailAndPwd(String email,String pwd);
	
}
