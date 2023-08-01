package com.ljh.exam.TwoProject.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.User;

@Repository
public interface MemberRepository extends JpaRepository<User,Integer> {


	 long countById(String id);

	User findById(String id);
	
}

