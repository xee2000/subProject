package com.ljh.exam.TwoProject.PersonSpecification;

import org.springframework.data.jpa.domain.Specification;

import com.ljh.exam.TwoProject.entity.Notice;


public class PersonSpecification {
	
    public static Specification<Notice> equalTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Notice> equalContent(String content) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("content"), content);
    }

    public static Specification<Notice> equalWriter(String writer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("writer"), writer);
    }
}