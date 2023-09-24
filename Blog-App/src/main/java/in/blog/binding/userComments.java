package in.blog.binding;

import in.blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class userComments {

	private String name;
	private String email;
	private String content;
	private Post post;
	
}
