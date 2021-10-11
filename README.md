# news
News portal (Spring)

The application is a news portal. For development were used: Spring MVC, Spring Security, Hibernate, Hibernate Validator, MySQL Workbench, Servlets, Java Server Pages (JSP), Java Server Pages Standard Tag Library (JSTL).

The actions that can be performed on the site depend on the user's role (administrator, user, guest). Access to the execution of any operation is regulated by Spring Security. BCryptPasswordEncoder is used to hash passwords.

The application interface supports English and Russian localization. 5 news items are displayed on the main page using paging. There are buttons for registration, authorization, changing the locale, add, update, delete news.

When trying to perform any action, a user with the "guest" role will be directed to the authorization page. Upon successful authorization, a user with role "user" can read or comment news, a user with the "admin" role, in addition to the above operations, can update, delete, add news.

The following functionality has been implemented:

- viewing all news (paging applied - 5 news on one page);
- add news (only for administrator);
- read news;
- update and delete news (only for the administrator);
- comment news.
