package com.lawcube.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name", length=50, nullable=false, unique=false)
    private String name;	
	@Column(name="gender", nullable=true, unique=false)
	private String gender;
	@Column(name="address", nullable=true)
	private String address;
	
	@Column(name="email", length=50, nullable=false, unique=true)
	private String email;
	@JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@Column(name="password", nullable=false)
	private String password;

}
