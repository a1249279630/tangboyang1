package com.example.tangboyang1.service;

import com.example.tangboyang1.dto.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    ResultDTO addComment(String comment,Integer id);

}
