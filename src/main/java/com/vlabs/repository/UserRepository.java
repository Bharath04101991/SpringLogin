package com.vlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vlabs.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
