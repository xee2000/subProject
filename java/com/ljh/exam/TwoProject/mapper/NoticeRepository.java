package com.ljh.exam.TwoProject.mapper;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer>, JpaSpecificationExecutor<Notice>{








}

