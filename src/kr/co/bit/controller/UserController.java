package kr.co.bit.controller;

import kr.co.bit.dao.UserDAO;
import kr.co.bit.util.WebUtil;
import kr.co.bit.vo.UserVO;
import org.apache.tomcat.util.buf.UDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;

        if (cmd.equals("joinform")) {//회원가입 폼
            WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
        } else if (cmd.equals("join")) {//회원정보 DB저장
            //받아오고
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            //세팅하고
            UserVO userVO = new UserVO();
            userVO.setName(name);
            userVO.setEmail(email);
            userVO.setPassword(password);
            userVO.setGender(gender);

            System.out.println(userVO.toString());
            //다오생성, 인서트호출
            UserDAO userDAO = new UserDAO();
            userDAO.insert(userVO);
            WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
        } else if (cmd.equals("loginform")) {
            WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
        } else if (cmd.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDAO dao = new UserDAO();
            UserVO userVO = dao.getUser(email, password);

            if (userVO == null) {
                System.out.println("로그인 실패");
                WebUtil.redirect(request, response, "/UserController?cmd=loginform&result=fail");
            } else {
                System.out.println("로그인 성공");

                HttpSession session = request.getSession();
                session.setAttribute("userVO", userVO);
                WebUtil.redirect(request, response, "/Controller");
            }

        } else if (cmd.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("userVO");
            session.invalidate();
            WebUtil.redirect(request, response, "/Controller");
        } else if (cmd.equals("modifyform")) {
            WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
        } else if (cmd.equals("modify")) {
            //회원정보 수정
            System.out.println("모디파이로가고싶다");
            UserDAO userDAO = new UserDAO();
            UserVO userVO = new UserVO();
            userVO.setName(request.getParameter("name"));
            userVO.setPassword(request.getParameter("password"));
            userVO.setGender(request.getParameter("gender"));

            HttpSession session = request.getSession();
            UserVO noConfirm = (UserVO) session.getAttribute("userVO");
            noConfirm.setName(request.getParameter("name"));

//            String no = noConfirm.getNo();
            String no = request.getParameter("no");
            userDAO.modify(userVO, no);
//            UserVO newUserVO = userDAO.getUpdateUser(no);
//            session.setAttribute("userVO", newUserVO);

            WebUtil.redirect(request, response, "/Controller");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
