package com.lawcube.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "name", length = 50, nullable = false, unique = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "isPublic", nullable = true)
	private Boolean isPublic=false;
	
	@CreationTimestamp
	@Column(name = "createdDateTime", nullable = true)
    private Date createdDateTime; 
	
//	@ManyToOne
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	private User user;
	
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	@ManyToOne(targetEntity = User.class)
	private User user;

	@Column(name = "userId")
	private int userId;
}
