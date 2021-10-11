package by.htp.news.service;

import java.util.List;

import by.htp.news.entity.Comment;

public interface CommentService {
	
	public void addComment(Comment theComment);
	public List<Comment> getComments(int theNewsId);

}
