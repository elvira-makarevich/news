package by.htp.news.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.news.dao.NewsDAO;
import by.htp.news.entity.News;

@Repository
public class NewsDAOImpl implements NewsDAO {

	public static final String QUERY_LIST_NEWS = "from News order by id";
	public static final String QUERY_DELETE_NEWS = "delete from News where id=:theNewsId";
	public static final String QUERY_COUNT_NEWS = "select count(*) from News";
	public static final String QUERY_UPDATE_NEWS = "update News set title = :titleParam, brief = :briefParam, content = :contentParam where id = :theNewsId";
	public static final String QUERY_PARAMETER_NEWS_ID = "theNewsId";
	public static final String QUERY_PARAMETER_TITLE = "titleParam";
	public static final String QUERY_PARAMETER_BRIEF = "briefParam";
	public static final String QUERY_PARAMETER_CONTENT = "contentParam";
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<News> listNews(int page) {

		Session currentSession = sessionFactory.getCurrentSession();

		List<News> theNews = currentSession.createQuery(QUERY_LIST_NEWS).setFirstResult(5 * (page - 1)).setMaxResults(5)
				.list();

		return theNews;
	}

	@Override
	public void addNews(News theNews) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theNews);

	}

	@Override
	public News getNews(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		News theNews = currentSession.get(News.class, theId);
		return theNews;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteNews(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery(QUERY_DELETE_NEWS);
		theQuery.setParameter(QUERY_PARAMETER_NEWS_ID, theId);

		theQuery.executeUpdate();
	}

	@Override
	public int newsCount() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(QUERY_COUNT_NEWS, Number.class).getSingleResult().intValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateNews(News theNews) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(QUERY_UPDATE_NEWS);

		query.setParameter(QUERY_PARAMETER_NEWS_ID, theNews.getId());
		query.setParameter(QUERY_PARAMETER_TITLE, theNews.getTitle());
		query.setParameter(QUERY_PARAMETER_BRIEF, theNews.getBrief());
		query.setParameter(QUERY_PARAMETER_CONTENT, theNews.getContent());

		query.executeUpdate();

	}

}
