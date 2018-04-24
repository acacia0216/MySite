package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void insert(UserVO vo){
        System.out.println("insert 들어옴");
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into users values(seq_user_no.nextval,?,?,?,?)";
        try {
            //pstmt준비
            preparedStatement = connection.prepareStatement(sql);
            //세팅
            preparedStatement.setString(1,vo.getName());
            preparedStatement.setString(2,vo.getEmail());
            preparedStatement.setString(3,vo.getPassword());
            preparedStatement.setString(4,vo.getGender());
            //excute
            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println(count+"회원정보 DB에 입력하기 성공");
            }else{
                System.out.println("안들어갔나본데;;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.connectClose(connection,preparedStatement,null);
        }
    }

    public UserVO getUser(String emailcheck, String passwordcheck) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserVO userVO = null;
        String sql = "select * from USERS where EMAIL = '"+emailcheck+"' and password='"+passwordcheck+"'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String no = resultSet.getString(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                String gender = resultSet.getString(5);

                userVO = new UserVO();
                userVO.setNo(no);
                userVO.setName(name);
                userVO.setEmail(email);
                userVO.setPassword(password);
                userVO.setGender(gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userVO;
    }

    public void modify(UserVO userVO, String no) {
        System.out.println("modify들어옴");
        System.out.println(userVO.getName()+" "+userVO.getPassword()+" "+userVO.getGender());
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        System.out.println(userVO.toString());
        System.out.println(no);
        String sql = "update USERS set name=?,PASSWORD=?,GENDER=? where NO='"+no+"'";
        try {
            System.out.println("modify안에있는 try들어옴");
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,userVO.getName());
            preparedStatement.setString(2,userVO.getPassword());
            preparedStatement.setString(3,userVO.getGender());

            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("회원정보 수정 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserVO getUpdateUser(String checkno) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserVO userVO = null;
        String sql = "select * from USERS where NO = '"+checkno+"'";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String no = resultSet.getString(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                String gender = resultSet.getString(5);

                userVO = new UserVO();
                userVO.setNo(no);
                userVO.setName(name);
                userVO.setEmail(email);
                userVO.setPassword(password);
                userVO.setGender(gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userVO;
    }
}
