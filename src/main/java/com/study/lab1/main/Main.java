package com.study.lab1.main;

import com.study.lab1.servlets.AllRequestsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.alex.ua.alex.dao.DaoFactory;
import ua.alex.ua.alex.dao.UserDao;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        DefaultServlet defaultServlet = new DefaultServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        DaoFactory daoFactory=DaoFactory.getDaoFactory(DaoFactory.H2);
        daoFactory.init();
        UserDao userDao = daoFactory.getUserDao();

        userDao.createUser("firstName1","lastName1",120.0, LocalDate.of(1999,01,10));
        userDao.createUser("firstName2","lastName2",1200.0, LocalDate.of(1999,12,20));

        context.setResourceBase("webapp");
        context.addServlet(new ServletHolder(allRequestsServlet), "/users");
        context.addServlet(new ServletHolder(defaultServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
