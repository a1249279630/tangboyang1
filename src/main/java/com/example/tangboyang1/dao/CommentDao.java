package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.CommentMapper;
import com.example.tangboyang1.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {
    @Autowired
    private CommentMapper commentMapper;

    public Integer addComment(Comment comment1) {
        return commentMapper.insert(comment1);
    }
}
