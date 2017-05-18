package com.demo.controller.comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.controller.BaseController;
import com.demo.dto.CommentDto;
import com.demo.service.comment.CommentService;
@Controller
@RequestMapping(value="comment")
public class CommentController extends BaseController
{
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(value = "/makeComment", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void  makeComment(CommentDto comment)
    {
    	comment.setOwner_id(new Random().nextInt(7));
    	try {
			commentService.makeComment(comment);
		} catch (Exception e) {
			
			logger.debug("{}",e.getMessage());
		}
    }
    @RequestMapping(value = "/listComments", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object>  listComments(CommentDto comment)
    {
    	Map<String,Object> commentData = new HashMap<>();
    	try {
    		commentData = commentService.listComments(comment);
    		
		} catch (Exception e) {
			logger.debug("{}",e.getMessage());
		}
    	
    	return commentData;
    }
}
