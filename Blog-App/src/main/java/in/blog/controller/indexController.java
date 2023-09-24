package in.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.blog.binding.SearchCriteria;
import in.blog.binding.userComments;
import in.blog.entity.Post;
import in.blog.entity.comments;
import in.blog.service.UserCommentService;
import in.blog.service.postService;

@Controller
public class indexController {
    
	@Autowired
	postService postservice;
	
	@Autowired
	UserCommentService usercommentservice;
	
	@Autowired
	HttpSession session;
	
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
	
	@PostMapping("/readmorepost")
	public String readMore2(@ModelAttribute("usercomment") userComments usercomments,Model model,RedirectAttributes redirectAttributes) {
		
		Post postById = (Post) session.getAttribute("postToComment");
		
		usercomments.setPost(postById);
		
		usercommentservice.saveComment(usercomments);
	   
		redirectAttributes.addAttribute("postID", postById.getPostId());

		return "redirect:readmore";
	}
	
	
	@GetMapping("/readmore")
	public String readMore(@RequestParam Integer postID,Model model) {
		
		System.out.println("READMORE CONTROLLERS");
		
		Post post = postservice.postById(postID);
		
		session.setAttribute("postToComment", post);
		
		userComments usercomment = new userComments();
		
		List<comments> findByPostId = usercommentservice.findByPostId();
		
		
		
		
		model.addAttribute("findByPostId",findByPostId);
		
		model.addAttribute("usercomment",usercomment);
		
		model.addAttribute("postById", post);
		
		return "detailPost";
	}
	
	@GetMapping("/comments")
	public String comments(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

		List<Post> postByUserId = postservice.dashboardresponse();
		
		ArrayList<Integer> postId = new ArrayList<>();
		
		for (Post post : postByUserId) {
			Integer postIds = post.getPostId();
			postId.add(postIds);
		}
		
		List<comments>Allcomments = new ArrayList<comments>();
		for (Integer integer : postId) {
			System.out.println(integer);
			List<comments> findByPostId = usercommentservice.findByPostId(integer);
			Allcomments.addAll(findByPostId);
		}
		for (comments comments : Allcomments) {
			System.out.println(comments.getId());
		}
		
		model.addAttribute("findByPostId",Allcomments);
		return "commets";
	}
	
	@GetMapping("/deletecomment")
	public String deleteByCommentId(@RequestParam Integer ID,Model model) {
		usercommentservice.deleteById(ID);
		return "redirect:comments";
	}
	
}
