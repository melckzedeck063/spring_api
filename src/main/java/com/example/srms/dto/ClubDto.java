package com.example.srms.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClubDto {
    private int id;
    private String title;
    private String photUrl;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
