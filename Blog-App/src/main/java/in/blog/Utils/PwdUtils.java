package in.blog.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {

	
	public static String generateRandomPwd() {
		String characters = "0123456789";
		String pwd = RandomStringUtils.random(6, characters);
		System.out.println( pwd);
		return pwd;
	}
}
