package by.htp.news.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.news.dao.CommentDAO;
import by.htp.news.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	public static final String QUERY_PARAMETER_NEWS_ID = "newsId";
	public static final String QUERY_GET_COMMENTS = "from Comment where id_news=:newsId order by date";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addComment(Comment theComment) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theComment);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Comment> getComments(int theNewsId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery(QUERY_GET_COMMENTS);
		theQuery.setParameter(QUERY_PARAMETER_NEWS_ID, theNewsId);
		List<Comment> theComment = theQuery.getResultList();

		return theComment;
	}

}
