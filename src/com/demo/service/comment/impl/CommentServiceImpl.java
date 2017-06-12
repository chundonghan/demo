package com.demo.service.comment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.DaoSupport;
import com.demo.dto.CommentDto;
import com.demo.dto.PageDto;
import com.demo.entity.Comment;
import com.demo.service.comment.CommentService;

@Service(value="commentService")
public class CommentServiceImpl implements CommentService{
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Override
	public void makeComment(CommentDto comment) throws Exception {
		
		long id = -1;
		try{
			id = comment.getId();
		}catch(NumberFormatException nfc){
			System.out.println(nfc.getMessage());
		}
		String preId = "";
		if(id != -1&&id != 0){
			preId = daoSupport.findForObject("CommentMapper.findPreId", id).toString();
			if("".equals(preId)){
				comment.setPre_id(id+"");
			}else{
				comment.setPre_id(preId+","+id);
			}
			
		}else{
			comment.setPre_id(preId);
		}
		daoSupport.save("CommentMapper.makeComment", comment);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> listComments(CommentDto comment) throws Exception {
		Map<String,Object> commentData = new HashMap<>();
		comment.setPagesize(PageDto.DEFAULT_PAGE_SIZE);
		List<Comment> commentList =  List.class.cast(daoSupport.findForList("CommentMapper.listComments", comment));
		String preId = null;
		for(Comment c:commentList){
			preId = c.getPre_id();
			List<Comment> preCommentList = List.class.cast(daoSupport.findForList("CommentMapper.listPreComments", preId));
			c.setCommentList(preCommentList);
		}
		commentData.put("comments", commentList);
		int totalNum = (int) daoSupport.findForObject("CommentMapper.commentCount", null);
		commentData.put("totalNum", totalNum);
		int totalPage = (totalNum + PageDto.DEFAULT_PAGE_SIZE - 1) / PageDto.DEFAULT_PAGE_SIZE;
		commentData.put("totalPage", totalPage);
		return commentData;
	}
	
    
	
}
