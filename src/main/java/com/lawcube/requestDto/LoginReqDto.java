package com.lawcube.requestDto;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginReqDto {
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;
	@NotBlank(message = "Password is mandatory")
	private String password;
}
