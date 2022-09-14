package com.lawcube.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler {

	public String encryptPassword(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		//System.out.println("hashedPassword----->" + hashedPassword);

		return hashedPassword;
	}

	public boolean decryptPassword(String password, String encryptedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean isPasswordMatched = encoder.matches(password, encryptedPassword);
		System.out.println("isPasswordMatched----->" + isPasswordMatched);
		return isPasswordMatched;
	}
	  public boolean passwordvalidation(String psd) {

	      String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	      System.out.println(psd.matches(pattern));
		return psd.matches(pattern);
	   
	}
}

