package com.ashokIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokIt.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

}
