package in.blog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.blog.Utils.EmailUtils;
import in.blog.Utils.PwdUtils;
import in.blog.binding.userForgot;
import in.blog.binding.userLogin;
import in.blog.binding.userSignUp;
import in.blog.entity.User;
import in.blog.repo.userRepo;
import in.blog.binding.unlockUser;

@Service
public class userSignUpImpl implements userService {

	@Autowired
	private userRepo userrepo;

	@Autowired
	private EmailUtils emailutils;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean saveUser(userSignUp userSignUp) {

		// check email exits
		String userEmail = userSignUp.getEmail();
		User findByEmail = userrepo.findByEmail(userEmail);
		if(findByEmail != null) {
			return false;
		}
		// =========================================================================================
		// Data binding object to entity
		User entity = new User();
		BeanUtils.copyProperties(userSignUp, entity);

		// generate random pwd
		String generateRandomPwd = PwdUtils.generateRandomPwd();

		// setting random pwd to entity pwd
		entity.setPwd(generateRandomPwd);

		// setting accstatus locked
		entity.setAccStatus("LOCKED");

		// saving record
		userrepo.save(entity);

		// ============================================================================================

		// sending email to unlocked account
		String to = userEmail;

		String subject = "Unlock your Account";

		StringBuffer body = new StringBuffer();
		body.append("<h1>Use temporary password to unlock your account</h1><br>");
		body.append("Temporay password: " + generateRandomPwd + "<br>");
		body.append("<a href=\"https://gadgettechsagablogs.up.railway.app/unlock?email=" + to + "\">Click here to unlock your Account/>");

		boolean sendEmail = emailutils.sendEmail(to, subject, body.toString());

		return (sendEmail) ? true : false;

	}

	@Override
	public boolean forgotUser(userForgot userforgot) {
		// To do
		// get email
		String email = userforgot.getEmail();
		// retrive password using email
		User findByEmail = userrepo.findByEmail(email);
		if (null == findByEmail) {
			return false;
		}
		String pwd = findByEmail.getPwd();
		// send email with pass

		String to = email;

		String subject = "Password Recovery";

		StringBuffer body = new StringBuffer();
		body.append("<h1>User password to Login </h1>" + "<br>");
		body.append("Your Password: " + pwd);
		body.append("<a <a href=\\\"http://localhost:8080/login/>");

		boolean sendEmail = emailutils.sendEmail(to, subject, body.toString());

		return (sendEmail) ? true : false;

	}

	@Override
	public String unlockUser(unlockUser unlockuser) {
		// to do

		if (unlockuser.getNewPwd().equals(unlockuser.getConfirmPwd())) {

			User entity = userrepo.findByEmail(unlockuser.getEmail());

			if (entity.getPwd().equals(unlockuser.getTempPwd())) {
				entity.setPwd(unlockuser.getConfirmPwd());
				entity.setAccStatus("UNLOCKED");
				userrepo.save(entity);
			} else {
				return "Temp password Incorrect";
			}
		} else {
			return "Passwords not match Retry";
		}

		return "Successfully Updated";
	}

	@Override
	public String loginUser(userLogin userlogin) {
	      
		User findByEmailAndPwd = userrepo.findByEmailAndPwd(userlogin.getUserEmail(), userlogin.getUserPass());
		if(findByEmailAndPwd == null) {
			return "Invalid Credentials or Email not Found";
		}
		if(findByEmailAndPwd.getAccStatus().equals("LOCKED")) {
			return "Account is locked unlocked it first";
		}
		//creating session
		Integer userId = findByEmailAndPwd.getUserId();
		System.out.println(userId);
		session.setAttribute("userId", userId);
		System.out.println(findByEmailAndPwd);
		return "success";
	}

}
