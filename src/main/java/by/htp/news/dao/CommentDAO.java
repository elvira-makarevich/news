package by.htp.news.dao;

import java.util.List;

import by.htp.news.entity.Comment;

public interface CommentDAO {
	
	public void addComment(Comment theComment);

	public List<Comment> getComments(int theNewsId);
}
