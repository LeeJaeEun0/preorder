package com.demo.preorder.follow.dto;

import com.demo.preorder.member.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class FollowDto {
    private Long userId;

    private Long followingId;
}
