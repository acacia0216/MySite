package kr.co.bit.controller;

import kr.co.bit.dao.BoardDAO;
import kr.co.bit.util.WebUtil;
import kr.co.bit.vo.BoardVO;
import kr.co.bit.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;
        if (cmd.equals("")) {
            WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
        } else if (cmd.equals("board")) {
            ArrayList<BoardVO> boardVOS = new ArrayList<>();
            String no1 = request.getParameter("page");
            BoardDAO boardDAO = new BoardDAO();
            boardVOS = boardDAO.search10(no1);//10개호출
            ArrayList<BoardVO> boardVO = new ArrayList<>();
            boardVO = boardDAO.searchAll();//전체호출
            request.setAttribute("allPage",boardVO);
            request.setAttribute("list", boardVOS);
            WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
        } else if (cmd.equals("write_page")) {
            HttpSession session = request.getSession();
            UserVO vo = (UserVO) session.getAttribute("userVO");
            if (vo == null) {
                WebUtil.redirect(request, response, "/UserController?cmd=loginform");
            } else {
                WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
            }
        } else if (cmd.equals("write_insert")) {
            HttpSession session = request.getSession();
            UserVO userVO = (UserVO) session.getAttribute("userVO");

            BoardVO boardVO = new BoardVO();
            boardVO.setTitle(request.getParameter("title"));
            boardVO.setContent(request.getParameter("content"));
            boardVO.setUser_no(userVO.getNo());

            BoardDAO boardDAO = new BoardDAO();
            boardDAO.insert(boardVO);
            WebUtil.redirect(request, response, "/BoardController?cmd=board");
        } else if (cmd.equals("board_view")) {
            String no = request.getParameter("no");
            System.out.println("숫자 가져왔니?" + no);
            BoardDAO boardDAO = new BoardDAO();
            BoardVO boardVO = boardDAO.searchContent(no);
            boardDAO.count(no);
            request.setAttribute("contents", boardVO);
            WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
        } else if (cmd.equals("board_modifyform")) {
            String contentNo = request.getParameter("contentNo");
            System.out.println("가지고나왔다 숫자" + contentNo);
            BoardDAO boardDAO = new BoardDAO();
            BoardVO boardVO = boardDAO.searchContent(contentNo);
            request.setAttribute("content", boardVO);
            WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
        } else if (cmd.equals("board_modify")) {
            //다오 불러서 글수정해주기
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String no = request.getParameter("no");

            BoardDAO boardDAO = new BoardDAO();
            boardDAO.update(title, content, no);

            WebUtil.redirect(request, response, "/BoardController?cmd=board");
        } else if (cmd.equals("searchContent")) {
            String kwd = request.getParameter("kwd");
            BoardDAO boardDAO = new BoardDAO();
            ArrayList<BoardVO> list = new ArrayList<>();
            list = boardDAO.searchKWD(kwd);
            request.setAttribute("list", list);
            WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
        } else if (cmd.equals("board_delete")) {
            String no = request.getParameter("no");
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.delete(no);
            WebUtil.redirect(request, response, "/BoardController?cmd=board");
        }else if(cmd.equals("")){

        }
    }
}
