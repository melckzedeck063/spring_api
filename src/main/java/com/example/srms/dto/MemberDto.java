package com.example.srms.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private String phone;
    @Transient
    private Integer age;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
