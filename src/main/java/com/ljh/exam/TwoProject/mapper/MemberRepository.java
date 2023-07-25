package com.ljh.exam.TwoProject.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.User;

@Repository
public interface MemberRepository extends JpaRepository<User,Integer> {

	void save(String id);

	 long countById(String id);


	
}

