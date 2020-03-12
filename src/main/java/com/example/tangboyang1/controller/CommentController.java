package com.example.tangboyang1.controller;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CommentController")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping(value = "add/comment")
    public ResultDTO addComment(String comment,Integer productid){
        return commentService.addComment(comment,productid);
    }
}
