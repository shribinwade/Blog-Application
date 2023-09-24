package in.blog.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class addPost {
         
	private String title;
    
	private String description;
	
	private String content;
	
	private Integer postId;
	
	private LocalDate createdOn;
}
