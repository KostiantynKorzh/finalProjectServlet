package me.project.controller.listener;


import me.project.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private String lang;
    private User user;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext = servletContextEvent.getServletContext();

        lang = "en";
        user = null;

        servletContext.setAttribute("lang", lang);
        servletContext.setAttribute("user", user);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
