package com.demo.service.comment;

import java.util.List;
import java.util.Map;

import com.demo.dto.CommentDto;
import com.demo.entity.Comment;


public interface CommentService {
	void makeComment(CommentDto comment) throws Exception;
	Map<String,Object> listComments(CommentDto comment) throws Exception;
}
