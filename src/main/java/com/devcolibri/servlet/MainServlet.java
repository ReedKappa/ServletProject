package com.devcolibri.servlet;

import model.UserProfile;
import service.AccountService;
import service.DirectoryService;
import model.FileStructure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryService dw = new DirectoryService();
        String path;
        String uri = req.getQueryString();
        if (uri != null && uri.contains("path")) {
            path = req.getParameter("path");
        } else {
            path = "/";
        }
        AccountService accountService = new AccountService();
        UserProfile user = accountService.getBySession(req.getSession().getId());
        if (accountService.hasActiveSession() || accountService.getBySession(req.getSession().getId()) == null) {
            resp.sendRedirect("http://localhost:8080/login");
        }
        if (!path.contains(user.getLogin()) || path.contains("/..")) {
            path = user.getRootDirectory();
            resp.sendRedirect("http://localhost:8080/?path=" + path);
        }
        if (path.matches("[A-Z]:")) {
            path = File.listRoots()[0].getPath();
        }
        String absolutePath = new File(path).getAbsolutePath();
        List<FileStructure> content;
        content = dw.getList(path);

        req.setAttribute("content", content);
        req.setAttribute("path", absolutePath.replace("\\", "/"));
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }
}
