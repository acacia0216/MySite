package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.BoardVO;

import javax.management.remote.JMXConnectionNotification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
    public void insert(BoardVO boardVO) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into BOARD  values (SEQ_BOARD_NO.nextval, ?, ?, 0, sysdate, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, boardVO.getTitle());
            preparedStatement.setString(2, boardVO.getContent());
            preparedStatement.setString(3, boardVO.getUser_no());

            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("게시글 DB입력 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, null);
        }

    }

    public ArrayList<BoardVO> search10(String no1) {//10개씩 끊어오기
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int num1 = Integer.parseInt(no1)*10;
        int num2 = num1-9;
        ArrayList<BoardVO> boardVOS = new ArrayList<>();
        BoardVO boardVO = null;
        String sql = "select rownum rnum, temp.* from (select b.no, b.title, b.content, b.hit, b.reg_date, u.NAME, b.USER_NO from board b, USERS u where b.user_no=u.NO order by no desc) temp where rownum between ? and ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,num2);
            preparedStatement.setInt(2,num1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boardVO = new BoardVO();
                boardVO.setNo(resultSet.getString(2));
                boardVO.setTitle(resultSet.getString(3));
                boardVO.setName(resultSet.getString(7));
                boardVO.setHit(resultSet.getString(5));
                boardVO.setReg_date(resultSet.getString(6));
                boardVO.setUser_no(resultSet.getString(8));


                boardVOS.add(boardVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, resultSet);
        }
        return boardVOS;
    }

    public BoardVO searchContent(String no) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BoardVO boardVO = null;
        String sql = "select * from BOARD where NO='" + no + "'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boardVO = new BoardVO();
                boardVO.setNo(resultSet.getString(1));
                boardVO.setTitle(resultSet.getString(2));
                boardVO.setContent(resultSet.getString(3));
                boardVO.setHit(resultSet.getString(4));
                boardVO.setReg_date(resultSet.getString(5));
                boardVO.setUser_no(resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, resultSet);
        }
        return boardVO;
    }

    public void update(String title, String content, String no) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update BOARD set title=?, content=? where no=" + no;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);

            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("게시글 수정 완료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, null);
        }
    }

    public ArrayList<BoardVO> searchKWD(String kwd) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<BoardVO> list = new ArrayList<>();
        String sql = "select b.NO, b.TITLE, b.CONTENT, b.HIT, b.REG_DATE, u.name  from BOARD b, USERS u where u.no = b.USER_NO and b.TITLE like '%" + kwd + "%'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BoardVO boardVO = new BoardVO();
                boardVO.setNo(resultSet.getString(1));
                boardVO.setTitle(resultSet.getString(2));
                boardVO.setContent(resultSet.getString(3));
                boardVO.setHit(resultSet.getString(4));
                boardVO.setReg_date(resultSet.getString(5));
                boardVO.setUser_no(resultSet.getString(6));
                list.add(boardVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public void count(String no) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update BOARD set HIT=hit+1 where no='" + no + "'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("카운팅 완료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String no) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from BOARD where no ='" + no + "'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<BoardVO> searchAll() {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<BoardVO> boardVOS = new ArrayList<>();
        BoardVO boardVO = null;
        String sql = "select * from BOARD";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boardVO = new BoardVO();
                boardVO.setNo(resultSet.getString(1));
                boardVO.setTitle(resultSet.getString(2));
                boardVO.setName(resultSet.getString(3));
                boardVO.setHit(resultSet.getString(4));
                boardVO.setReg_date(resultSet.getString(5));


                boardVOS.add(boardVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection, preparedStatement, resultSet);
        }
        return boardVOS;
    }
}
