package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.CommentDao;
import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.pojo.Comment;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.service.CommentService;
import com.example.tangboyang1.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public ResultDTO addComment(String comment,Integer id) {
        User user = SessionUtil.getUser();
        Comment comment1=new Comment();
        comment1.setUserId(user.getId());
        comment1.setContent(comment);
        comment1.setCreattime(new Date());
        comment1.setProductId(id);
        Integer integer = commentDao.addComment(comment1);
        if(integer==1){
            return ResultDTO.ok("添加成功");
        }else {
            throw new ErrorCodeException(CommonErrorCode.UNKOWN_ERROR);
        }
    }
}
