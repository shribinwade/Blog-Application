package in.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.blog.binding.unlockUser;
import in.blog.binding.userForgot;
import in.blog.binding.userLogin;
import in.blog.binding.userSignUp;
import in.blog.service.userService;

@Controller
public class userdtlsController {

	@Autowired
	private userService userservice;
	
	@PostMapping("/login")
	public String Login1(@ModelAttribute("userObj") userLogin userObj, Model model) {
		System.out.println(userObj);
		String loginUser = userservice.loginUser(userObj);
		if(loginUser.contains("success")) {
		    return "redirect:/dashboard";	
		}
		model.addAttribute("error",loginUser);
		return "LoginPage";
	}
	
	@GetMapping("/login")
	public String Login(Model model) {
		
		userLogin userObj = new userLogin();
		model.addAttribute("userObj",userObj);
		return "LoginPage";
	}
	
	@GetMapping("/signup")
	public String signup(Model model){
		userSignUp signUpObj = new userSignUp();
		model.addAttribute("signUpObj",signUpObj);
		return "signup";
	}
                            
	@PostMapping("/signup")
	public String signup1(@ModelAttribute("signUpObj") userSignUp signUpObj,  Model model){
		
		boolean saveUser = userservice.saveUser(signUpObj);
		if(saveUser) {
			model.addAttribute("succMsg","Account created check email to unlock account");
		}else {
			model.addAttribute("errorMsg","Email already taken try using other email");
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlock(@RequestParam String email,Model model) {
		
		unlockUser unlockform = new unlockUser();
		
		unlockform.setEmail(email);
		
		model.addAttribute("email",email);
		
		model.addAttribute("unlockForm", unlockform);
		
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlock1(@ModelAttribute("unlockForm")unlockUser unlockform  ,Model model ) {
		System.out.println(unlockform);
		String unlockUser = userservice.unlockUser(unlockform);
		model.addAttribute("Msg",unlockUser);
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgot(Model model) {
		
		userForgot userforgot = new userForgot();
		model.addAttribute("userforgot",userforgot);
		
		return "forgot";
	}
	
	@PostMapping("/forgot")
	public String forgot1(@ModelAttribute("userforgot") userForgot userforgot ,Model model) {
		
		System.out.println(userforgot);
		boolean forgotUser = userservice.forgotUser(userforgot);
		if(forgotUser) {
			model.addAttribute("succMsg", "Password send to your mail");
		}else {
			model.addAttribute("errorMsg", "Incorrect email");
		}
		return "forgot";
	}
	
}
