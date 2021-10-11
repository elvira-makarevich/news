package by.htp.news.dao;

import java.util.List;

import by.htp.news.entity.News;

public interface NewsDAO {
	
	public List<News> listNews(int page);

	public void addNews(News theNews);

	public News getNews(int theId);

	public void deleteNews(int theId);

	public int newsCount();

	public void updateNews(News theNews);

}
