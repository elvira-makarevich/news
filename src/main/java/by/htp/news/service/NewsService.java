package by.htp.news.service;

import java.util.List;

import by.htp.news.entity.News;

public interface NewsService {

	public List<News> listNews(int page);

	public void addNews(News theNews);

	public News getNews(int theId);

	public void deleteNews(int theId);

	int newsCount();

	public void updateNews(News theNews);

}
