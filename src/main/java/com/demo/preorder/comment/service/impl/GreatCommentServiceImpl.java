package com.demo.preorder.comment.service.impl;

import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreatCommentServiceImpl implements GreatCommentService {

    private final GreatCommentDao greatCommentDao;

    public GreatCommentServiceImpl(GreatCommentDao greatCommentDao) {
        this.greatCommentDao = greatCommentDao;
    }

    @Override
    public GreatComment saveGreatComment(Long userId, GreatCommentDto greatCommentDto) {
        return greatCommentDao.saveGreatComment(userId, greatCommentDto.getCommentId());
    }

    @Override
    public void deleteGreatComment(Long userId, GreatCommentDto greatCommentDto) {
        greatCommentDao.deleteGreatComment(userId, greatCommentDto.getGreatCommentId());
    }

    @Override
    public List<GreatComment> greatCommentList(GreatCommentDto greatCommentDto) {
        return greatCommentDao.greatCommentList(greatCommentDto.getCommentId());
    }
}
