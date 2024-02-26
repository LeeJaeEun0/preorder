package com.demo.preorder.profile.entity;

import com.demo.preorder.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String image;

    private String greeting;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "created_date", nullable = true, insertable = true, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "lastmodified_date", nullable = true, insertable = true, updatable = true)
    private LocalDateTime lastmodifiedDate;

    @PrePersist
    public void setDate() {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastmodifiedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void modifiedDate() {
        this.setLastmodifiedDate(LocalDateTime.now());
    }


}
