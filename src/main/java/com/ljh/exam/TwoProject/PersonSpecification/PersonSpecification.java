package com.ljh.exam.TwoProject.PersonSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.ljh.exam.TwoProject.entity.Notice;


public class PersonSpecification {

    public static Specification<Notice> equalTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Notice> equalContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), "%" + content.toLowerCase() + "%");
    }

    public static Specification<Notice> equalWriter(String writer) {
        if (StringUtils.isEmpty(writer)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("writer")), "%" + writer.toLowerCase() + "%");
    }
}

