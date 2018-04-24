package kr.co.bit.controller;

import kr.co.bit.dao.BoardDAO;
import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.util.WebUtil;
import kr.co.bit.vo.BoardVO;
import kr.co.bit.vo.GuestBookVO;
import kr.co.bit.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;
        if (cmd.equals("")) {
            WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
