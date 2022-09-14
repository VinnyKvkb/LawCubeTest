package com.lawcube.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lawcube.Entity.Project;
import com.lawcube.Entity.User;
import com.lawcube.helper.JwtTokenUtil;
import com.lawcube.helper.PasswordHandler;
import com.lawcube.repository.ProjectRepo;
import com.lawcube.repository.UserRepo;
import com.lawcube.requestDto.LoginReqDto;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordHandler passwordHandler;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ProjectRepo projectRepo;

	public ResponseEntity<Object> register(User user) {
		User res = new User();
		;
		try {
			user.setPassword(passwordHandler.encryptPassword(user.getPassword()));
			res = userRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	public ResponseEntity<Object> login(LoginReqDto reqDto) {
		try {

			User user = userRepo.findByEmail(reqDto.getEmail());
			if (user == null) {
				return new ResponseEntity<Object>("User not found with Given email ", HttpStatus.BAD_REQUEST);
			}
			boolean isPasswordMatched = passwordHandler.decryptPassword(reqDto.getPassword(), user.getPassword());
			if (isPasswordMatched) {
				// create a cookie
				Integer maxAge = 5 * 24 * 3600 * 1000;
				String token = jwtTokenUtil.generateToken(user.getEmail());
				Cookie cookie = new Cookie("jwt", token);
				cookie.setMaxAge(maxAge);
				cookie.setHttpOnly(false);
				cookie.setPath("/");
				response.addCookie(cookie);

				List<Project> ownProjectsList = projectRepo.findAllByUserId(user.getId());
				List<Project> othersProjectsList = projectRepo.findAllByIsPublic(true);
				List<Project> othersProjectsFinalList = othersProjectsList.stream()
						.filter(x -> x.getUserId() != user.getId()).collect(Collectors.toList());

				Map<String, List<Project>> map = new HashMap<>();
				map.put("ownProjectsList", ownProjectsList);
				map.put("othersPublicProjectsList", othersProjectsFinalList);
				return new ResponseEntity<Object>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
