package com.lawcube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawcube.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}
