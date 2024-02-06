package com.demo.preorder.newsfeed.entity;

import com.demo.preorder.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsfeedFollower {
    @Id
    @Column(name="newsfeed_follower_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id")
    private User followerId;

    private Long targetId;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "created_date", nullable = true, insertable = true, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void setDate() {
        this.setCreatedDate(LocalDateTime.now());
    }
}
