package com.ljh.exam.TwoProject.mapper;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljh.exam.TwoProject.entity.NoticeFile;

@Repository
public interface NoticefileRepository extends JpaRepository<NoticeFile,Integer>{



}

