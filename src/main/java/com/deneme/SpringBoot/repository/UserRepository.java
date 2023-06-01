package com.deneme.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.deneme.SpringBoot.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
