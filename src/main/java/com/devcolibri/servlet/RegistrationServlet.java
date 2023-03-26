package com.devcolibri.servlet;

import model.UserProfile;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserProfile user = new UserProfile(login, password, email);
        if (!accountService.checkUser(login, password)){
            accountService.addUser(user);
            accountService.addSession(user, req.getSession());
        } else {
            req.setAttribute("error", "user already exist");
            resp.sendRedirect("http://localhost:8080/login");
        }
        redirect(user, req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private void redirect(UserProfile user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("http://localhost:8080/ "+ "?path=" + user.getRootDirectory());
    }
}
