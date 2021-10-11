package by.htp.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.news.dao.CommentDAO;
import by.htp.news.entity.Comment;
import by.htp.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Transactional
	@Override
	public void addComment(Comment theComment) {
		commentDAO.addComment(theComment);
	}
	
	@Transactional
	@Override
	public List<Comment> getComments(int theNewsId) {
		
		return commentDAO.getComments(theNewsId);
	}

}
