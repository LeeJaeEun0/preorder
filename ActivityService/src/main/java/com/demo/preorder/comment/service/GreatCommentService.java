package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import java.util.List;

public interface GreatCommentService {
    GreatComment saveGreatComment(Long userId, GreatCommentDto greatCommentDto);

    void deleteGreatComment(Long userId, Long greatCommentId);

    List<GreatComment> greatCommentList(Long greatCommentId);
}
