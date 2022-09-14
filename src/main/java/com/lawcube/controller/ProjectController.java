package com.lawcube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawcube.Entity.Project;
import com.lawcube.requestDto.UpdateProjectReqDto;
import com.lawcube.service.ProjectService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping(value = "/ProjectController")
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value = "/createProject/{userId}", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Object> createProject(@RequestBody Project project,@PathVariable Integer userId) {
		log.info("==============createProject==========");
		ResponseEntity<Object> resEntity = projectService.createProject(project,userId);
		return resEntity;
	}
	@RequestMapping(value = "/updateProject", method = RequestMethod.PUT,consumes = "application/json")
	public ResponseEntity<Object> updateProject(@RequestBody UpdateProjectReqDto project) {
		log.info("==============updateProject==========");
		ResponseEntity<Object> resEntity = projectService.updateProject(project);
		return resEntity;
	}
	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProject(@PathVariable Integer projectId) {
		log.info("==============deleteProject==========");
		ResponseEntity<Object> resEntity = projectService.deleteProject(projectId);
		return resEntity;
	}
}
