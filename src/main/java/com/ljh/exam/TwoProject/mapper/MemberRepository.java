package com.ljh.exam.TwoProject.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.User;

@Repository
public interface MemberRepository extends JpaRepository<User,Integer> {

	Optional<User> findById(String id);

	
}

