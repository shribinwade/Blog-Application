package in.blog.service;

import in.blog.binding.userForgot;
import in.blog.binding.userLogin;
import in.blog.binding.userSignUp;
import in.blog.binding.unlockUser;
public interface userService {

	public boolean saveUser(userSignUp userSignUp);
	public boolean forgotUser(userForgot userforgot);
	public String unlockUser(unlockUser unlockuser);
	public String loginUser(userLogin userlogin);
}
