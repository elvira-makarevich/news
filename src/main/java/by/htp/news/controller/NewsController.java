package by.htp.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.htp.news.entity.Comment;
import by.htp.news.entity.News;
import by.htp.news.service.CommentService;
import by.htp.news.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	public static final String LIST_NEWS_PAGE = "list-news";
	public static final String MAV_OBJ_PAGE = "page";
	public static final String MAV_OBJ_NEWS = "news";
	public static final String MAV_OBJ_ID_NEWS = "newsId";
	public static final String MAV_OBJ_NEWS_COUNT = "newsCount";
	public static final String MAV_OBJ_PAGES_COUNT = "pagesCount";
	public static final String MV_COMMENTS_NEWS = "commentsNews";
	public static final String MV_ATT_COMMENT = "comment";
	public static final String ADD_PAGE = "add";
	public static final String UPDATE_COMMAND = "update";
	public static final String NEWS_FORM_PAGE = "news-form";
	public static final String READ_FORM_PAGE = "read-form";
	public static final String MAV_ATT_COMMAND = "command";
	public static final String REDIRECT_NEWS_PAGE = "redirect:/news/list?messageUpdateNewsOK=1";
	public static final String REDIRECT_READ_NEWS_ERROR = "redirect:/news/read?errorComment=1";
	public static final String REDIRECT_READ_NEWS = "redirect:/news/read";
	public static final String REDIRECT_DELETE_NEWS = "redirect:/news/list?messageDeleteNewsOK=1";

	@Autowired
	private NewsService newsService;

	@Autowired
	private CommentService commentService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/list")
	public ModelAndView listNews(@RequestParam(defaultValue = "1") int page) {

		List<News> news = newsService.listNews(page);
		int newsCount = newsService.newsCount();
		int pagesCount = (newsCount + 4) / 5;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_NEWS_PAGE);
		modelAndView.addObject(MAV_OBJ_PAGE, page);
		modelAndView.addObject(MAV_OBJ_NEWS, news);
		modelAndView.addObject(MAV_OBJ_NEWS_COUNT, newsCount);
		modelAndView.addObject(MAV_OBJ_PAGES_COUNT, pagesCount);
		return modelAndView;

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		News theNews = new News();
		String command = ADD_PAGE;
		theModel.addAttribute(MAV_ATT_COMMAND, command);
		theModel.addAttribute(MAV_OBJ_NEWS, theNews);
		return NEWS_FORM_PAGE;
	}

	@PostMapping("/add")
	public ModelAndView saveNews(@Valid @ModelAttribute("news") News theNews, BindingResult theBindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (theBindingResult.hasErrors()) {
			modelAndView.setViewName(NEWS_FORM_PAGE);
		} else {
			newsService.addNews(theNews);
			modelAndView.setViewName(REDIRECT_NEWS_PAGE);
		}

		return modelAndView;

	}

	@PostMapping("/update")
	public ModelAndView updateNews(@Valid @ModelAttribute("news") News theNews, BindingResult theBindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (theBindingResult.hasErrors()) {
			modelAndView.setViewName(NEWS_FORM_PAGE);
		} else {
			newsService.updateNews(theNews);
			modelAndView.setViewName(REDIRECT_NEWS_PAGE);
		}

		return modelAndView;

	}

	@PostMapping("/addComment")
	public ModelAndView addComment(@Valid @ModelAttribute("comment") Comment theComment,
			BindingResult theBindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		int newsId = theComment.getNewsId();
		modelAndView.addObject(MAV_OBJ_ID_NEWS, newsId);

		if (theBindingResult.hasErrors()) {
			modelAndView.setViewName(REDIRECT_READ_NEWS_ERROR);
		} else {
			commentService.addComment(theComment);
			System.out.println(newsId);
			modelAndView.setViewName(REDIRECT_READ_NEWS);
		}

		return modelAndView;

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("newsId") int theId, Model theModel) {

		News theNews = newsService.getNews(theId);
		String command = UPDATE_COMMAND;
		theModel.addAttribute(MAV_ATT_COMMAND, command);
		theModel.addAttribute(MAV_OBJ_NEWS, theNews);
		return NEWS_FORM_PAGE;
	}

	@GetMapping("/read")
	public String readNews(@RequestParam("newsId") int theNewsId, Model theModel) {

		News theNews = newsService.getNews(theNewsId);
		Comment theComment = new Comment();
		theModel.addAttribute(MAV_OBJ_NEWS, theNews);
		List<Comment> theNewsComments = commentService.getComments(theNewsId);
		theModel.addAttribute(MV_COMMENTS_NEWS, theNewsComments);
		theModel.addAttribute(MV_ATT_COMMENT, theComment);

		return READ_FORM_PAGE;

	}

	@GetMapping("/delete")
	public String deleteNews(@RequestParam("newsId") int theId) {

		newsService.deleteNews(theId);
		return REDIRECT_DELETE_NEWS;
	}

}