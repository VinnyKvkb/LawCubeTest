package com.lawcube.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lawcube.Entity.Project;
import com.lawcube.Entity.User;
import com.lawcube.helper.CurrentRequestData;
import com.lawcube.repository.ProjectRepo;
import com.lawcube.repository.UserRepo;
import com.lawcube.requestDto.UpdateProjectReqDto;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo projectRepo;
	@Autowired
	UserRepo userRepo;

	public ResponseEntity<Object> createProject(Project project, Integer userId) {
		Project res = new Project();
		try {
			project.setUserId(userId);
			res = projectRepo.save(project);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateProject(UpdateProjectReqDto reqDto) {
		int res = 0;
		try {
			String email = CurrentRequestData.getCurrentUser();
			System.out.println("getCurrentUser-->" + email);
			User user = userRepo.findByEmail(email);
			Date date = new Date();
			res = projectRepo.updateProject(reqDto.getDescription(), reqDto.getIsPublic(), reqDto.getName(), date,
					reqDto.getId(), user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	public ResponseEntity<Object> deleteProject(Integer projectId) {
		int res = 0;
		try {

			String email = CurrentRequestData.getCurrentUser();
			System.out.println("getCurrentUser-->" + email);
			User user = userRepo.findByEmail(email);
			res = projectRepo.deleteProject(projectId, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);

	}
}
