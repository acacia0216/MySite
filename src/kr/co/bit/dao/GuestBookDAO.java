package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.GuestBookVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestBookDAO {
    public void insert(GuestBookVO vo) {
        System.out.println("insert 들어옴");
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into GUESTBOOK (NO, NAME, PASSWORD, CONTENT, REG_DATE) values (SEQ_GUESTBOOK_NO.nextval,?,?,?,sysdate)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,vo.getName());
            preparedStatement.setString(2,vo.getPassword());
            preparedStatement.setString(3,vo.getContent());

            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("DB 삽입 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,null);
        }

    }

    public void delete(String no) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from GUESTBOOK where NO="+no;
        try {
            preparedStatement = connection.prepareStatement(sql);

            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("DB 게시물 삭제 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,null);
        }

    }

    public ArrayList<GuestBookVO> searchAll() {
        ArrayList<GuestBookVO> list = null;
        GuestBookVO vo = null;
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from GUESTBOOK order by no desc";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while(resultSet.next()) {
                vo = new GuestBookVO();

                vo.setNo(resultSet.getString(1));
                vo.setName(resultSet.getString(2));
                vo.setPassword(resultSet.getString(3));
                vo.setContent(resultSet.getString(4));
                vo.setDate(resultSet.getString(5));

                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,resultSet);
        }
        return list;
    }

    public GuestBookVO searchContent(String no){
        GuestBookVO vo = new GuestBookVO();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Select * from GUESTBOOK where NO = '"+no+"'";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            System.out.println("vo에 세팅하러 옴");
            while(resultSet.next()) {
                vo.setNo(resultSet.getString(1));
                vo.setName(resultSet.getString(2));
                vo.setPassword(resultSet.getString(3));
                vo.setContent(resultSet.getString(4));
                vo.setDate(resultSet.getString(5));
                System.out.println(vo.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vo;
    }
}
