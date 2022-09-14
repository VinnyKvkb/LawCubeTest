package com.lawcube.requestDto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class UpdateProjectReqDto {
	
	private int id;
	private String name;

	private String description;

	private Boolean isPublic;
	
	 private LocalDateTime createdDateTime; 
}
