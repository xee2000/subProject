package com.ljh.exam.TwoProject.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class MemberVO {

    @Id // Specify primary key
    private String id;
    private String pwd;
    private String phone;

    // Codes such as constructors, getters, and setters are omitted.
}
