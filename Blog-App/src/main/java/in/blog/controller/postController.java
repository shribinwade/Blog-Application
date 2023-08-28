package in.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.blog.binding.SearchCriteria;
import in.blog.binding.addPost;
import in.blog.entity.Post;
import in.blog.service.postService;
@Controller
public class postController {
   
	@Autowired
	postService postservice;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId==null) {
			return "";
		}
		List<Post> dashboardresponse = postservice.dashboardresponse();
		model.addAttribute("dashboard",dashboardresponse);
		return "dashboard";
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/addpost")
	public String AddPost(Model model) {
		addPost addpost = new addPost();
		model.addAttribute("addpost",addpost);
		return "addpost";
	}
	
	@PostMapping("/addpost")
	public String Addpost1(@ModelAttribute("addpost") addPost addpost, Model model) {
		System.out.println(addpost);
		boolean savePost = postservice.savePost(addpost);
		if(savePost) {
			model.addAttribute("succMsg","Success");
		}else {
			model.addAttribute("errorMsg","failed");
		}
		return "addpost";
	}
	
	
}
