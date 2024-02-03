package com.demo.preorder.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userRoleId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value =EnumType.STRING)
    private Role role;

//    @Builder
//    public UserRole(User user ,Role role){
//        this.user = user;
//        this.role = role;
//    }
}