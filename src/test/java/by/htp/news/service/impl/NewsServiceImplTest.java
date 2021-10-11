package by.htp.news.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.htp.news.dao.NewsDAO;
import by.htp.news.entity.News;


public class NewsServiceImplTest {
		
	@Mock
	private NewsDAO newsDAO;
	
	@InjectMocks
	private NewsServiceImpl newsService;
		
	@SuppressWarnings("deprecation")
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void testMockCreation()  {
        assertNotNull(newsDAO);
    }
	
	@Test
    public void testNewsCount()  {
		
		when(newsService.newsCount()).thenReturn(30);
	    assertEquals(30,newsService.newsCount()); 
	    
	    verify(newsDAO, atLeastOnce()).newsCount();
	    verify(newsDAO, never()).addNews(new News());
	    verifyNoMoreInteractions(newsDAO);
    }
	
	@Test
    public void testGetNews()  {
		
		when(newsDAO.getNews(1)).thenReturn(new News("MOCKITO", "Mockito for testing"));
	   
		News expected = new News("MOCKITO", "Mockito for testing");
		News actual = newsService.getNews(1);
		assertEquals(expected,actual); 
	    
	    verify(newsDAO, atLeastOnce()).getNews(1);
	    verify(newsDAO, never()).getNews(2);
	   
    }

}
