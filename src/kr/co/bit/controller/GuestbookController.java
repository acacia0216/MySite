package kr.co.bit.controller;

import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.util.WebUtil;
import kr.co.bit.vo.GuestBookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GuestbookController")
public class GuestbookController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;
        if (cmd.equals("")) {
            WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
        } else if (cmd.equals("guestbook")) {
            ArrayList<GuestBookVO> list = new ArrayList<>();
            GuestBookDAO guestBookDAO = new GuestBookDAO();
            list = guestBookDAO.searchAll();
            request.setAttribute("list", list);
            WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
        } else if (cmd.equals("insert")) {
            System.out.println("cmd값은 insert");
            GuestBookDAO dao = new GuestBookDAO();
            GuestBookVO vo = new GuestBookVO();

            vo.setName(request.getParameter("name"));
            vo.setPassword(request.getParameter("password"));
            vo.setContent(request.getParameter("content"));

            System.out.println(vo.toString());
            dao.insert(vo);
            WebUtil.redirect(request, response, "/GuestbookController?cmd=guestbook");

        } else if (cmd.equals("delete_form")) {
            WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
        } else if (cmd.equals("delete")) {
            System.out.println("delete 들어옴");
            GuestBookDAO dao = new GuestBookDAO();
            String passwordConfirm = request.getParameter("password");
            String no = request.getParameter("no");
            System.out.println(request.getParameter("password"));
            System.out.println(no);

            GuestBookVO vo = dao.searchContent(no);
            System.out.println(vo.getPassword());

            if (passwordConfirm.equals(vo.getPassword())) {
                dao.delete(vo.getNo());
                System.out.println("데이터삭제 완료");
            } else {
                System.out.println("패스워드 불일치");
            }
            WebUtil.redirect(request, response, "/GuestbookController?cmd=guestbook");
        }
    }
}
