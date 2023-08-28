package in.blog.binding;

import lombok.Data;

@Data
public class unlockUser {
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;
}
