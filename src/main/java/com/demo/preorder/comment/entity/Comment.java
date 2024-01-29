package com.demo.preorder.comment.entity;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.post.entity.Post;
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
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User useId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    private String content;

    private Integer CommentDepth;

    private Integer commentGroup;

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
