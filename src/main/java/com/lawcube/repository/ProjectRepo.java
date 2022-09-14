package com.lawcube.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.lawcube.Entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer>{
	

	List<Project> findAllByUserId(int userId);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value=" update Project u set u.description=?1, u.isPublic=?2, u.name=?3,u.createdDateTime=?4 where u.id=?5 and u.userId=?6")
	int updateProject(String description, Boolean boolean1,String name, Date date, int id,int userId);
	
	List<Project> findAllByIsPublic(boolean isPublic);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value="delete from Project p where p.id=?1 and p.userId=?2")
	int deleteProject(Integer projectId, int id);
}
