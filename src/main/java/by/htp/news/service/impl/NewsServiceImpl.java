package by.htp.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.news.dao.NewsDAO;
import by.htp.news.entity.News;
import by.htp.news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	//public NewsServiceImpl(NewsDAO newsDAO) {
	//	this.newsDAO = newsDAO;
	//}

	@Override
	@Transactional
	public List<News> listNews(int page) {
		return newsDAO.listNews(page);
	}

	@Override
	@Transactional
	public void addNews(News theNews) {
		// check
		newsDAO.addNews(theNews);

	}

	@Override
	@Transactional
	public News getNews(int theId) {

		return newsDAO.getNews(theId);
	}

	@Override
	@Transactional
	public void deleteNews(int theId) {
		newsDAO.deleteNews(theId);

	}

	@Override
	@Transactional
	public int newsCount() {
		return newsDAO.newsCount();
	}

	@Override
	@Transactional
	public void updateNews(News theNews) {
		newsDAO.updateNews(theNews);

	}

}
