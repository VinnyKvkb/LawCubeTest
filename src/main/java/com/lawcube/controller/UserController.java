package com.lawcube.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawcube.Entity.User;
import com.lawcube.requestDto.LoginReqDto;
import com.lawcube.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/UserController")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody @Valid User user) {
		log.info("==============Reg==========");
		ResponseEntity<Object> resEntity = userService.register(user);
		return resEntity;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody @Valid LoginReqDto reqDto) {
		log.info("==============login==========");
		ResponseEntity<Object> resEntity = userService.login(reqDto);
		return resEntity;
	}
}
