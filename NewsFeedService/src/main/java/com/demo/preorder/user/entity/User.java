package com.demo.preorder.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String refreshToken;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();


    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "created_date", nullable = true, insertable = true, updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "lastmodified_date", nullable = true, insertable = true, updatable = true)
    private LocalDate lastmodifiedDate;

    @PrePersist
    public void setDate() {
        this.setCreatedDate(LocalDate.now());
        this.setLastmodifiedDate(LocalDate.now());
    }

    @PreUpdate
    public void modifiedDate() {
        this.setLastmodifiedDate(LocalDate.now());
    }

}
