package in.blog.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.blog.binding.SearchCriteria;
import in.blog.entity.Post;
import in.blog.service.postService;

@Controller
public class indexController {
    
	@Autowired
	postService postservice;
	
	@GetMapping("/")
	public String index(@ModelAttribute("sc")SearchCriteria sc ,Model model) {
		List<Post> allposts = postservice.allposts();
		model.addAttribute("allposts",allposts);
		return "index";
	}
	
	@GetMapping("/searchtitle")
	public String searchtitle(Model model) {
		
		SearchCriteria sc = new SearchCriteria();
	    model.addAttribute("sc",sc);
		return "index";
	}

	
	@PostMapping("/searchtitle")
	public String searchtitle1(@ModelAttribute("sc") SearchCriteria sc ,Model model) {
		
		String posttitle = sc.getTitle();
		List<Post> scposts = postservice.scposts(posttitle);
		model.addAttribute("allposts",scposts);
		return "index";
	}
}
