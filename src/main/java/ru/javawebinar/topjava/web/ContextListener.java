package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    static ConfigurableApplicationContext appCtx;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        appCtx.close();
    }
}
