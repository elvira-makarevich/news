package by.htp.news.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import by.htp.news.entity.User;
import by.htp.news.service.UserService;

@Controller
public class UserController {

	public static final String MESSAGE_EMAIL_LOGIN_EXISTS = "messageErrorLoginOrEmailExists";
	public static final String ANSWER_EMAIL_LOGIN_EXISTS = "Login or email exists!! Try again.";
	public static final String LOGIN_PAGE = "login";
	public static final String REGISTRATION_PAGE = "registration";
	public static final String ACCESS_DENIED_PAGE = "accessDenied";
	public static final String REDIRECT_LOGIN_PAGE = "redirect:/showLoginPage?messageRegistrationOK=1";

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder bcryptBean;

	@GetMapping("/showLoginPage")
	public String showLoginPage() {

		return LOGIN_PAGE;
	}

	@GetMapping("/showRegistrationPage")
	public String showRegistrationPage() {

		return REGISTRATION_PAGE;

	}

	@PostMapping("/register")
	public ModelAndView registerNewUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult) {

		ModelAndView modelAndView = new ModelAndView();

		if (theBindingResult.hasErrors()) {
			modelAndView.setViewName(REGISTRATION_PAGE);
		} else {
			theUser.setPassword(bcryptBean.encode(theUser.getPassword()));
			userService.register(theUser);
			modelAndView.setViewName(REDIRECT_LOGIN_PAGE);
		}

		return modelAndView;
	}

	@GetMapping("/accessDenied")
	public String showAccessDenied() {

		return ACCESS_DENIED_PAGE;

	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ModelAndView handleDatabaseException(SQLIntegrityConstraintViolationException ex) {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put(MESSAGE_EMAIL_LOGIN_EXISTS, ANSWER_EMAIL_LOGIN_EXISTS);
		return new ModelAndView(REGISTRATION_PAGE, model);

	}
}