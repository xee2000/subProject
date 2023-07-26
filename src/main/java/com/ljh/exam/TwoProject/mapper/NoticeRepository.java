package com.ljh.exam.TwoProject.mapper;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

	Page<Notice> findAll(Pageable pageable);

	Page<Notice> findAll(Pageable pageable, String searchKeyword);


	
}
