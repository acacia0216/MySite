<%@ page import="kr.co.bit.vo.UserVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./assets/css/user.css" rel="stylesheet" type="text/css">
    <title>Insert title here</title>
</head>
<body>
<%
    UserVO userVO = (UserVO) session.getAttribute("userVO");
    System.out.println("수정페이지의 유저 정보");
    System.out.println(userVO.getName() + " " + userVO.getEmail());
    String no = userVO.getNo();
%>
<div id="container">

    <jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

    <div id="wrapper">
        <div id="content">
            <div id="user">

                <form id="join-form" name="joinForm" method="get" action="/UserController">

                    <input type="hidden" name="cmd" value="modify">
                    <input type="hidden" name="no" value="<%=no%>">
                    <label class="block-label" for="name">이름</label>
                    <input id="name" name="name" type="text" value=""<%=userVO.getName()%> />

                    <label class="block-label" for="email">이메일</label>
                    <strong></strong>

                    <label class="block-label">패스워드</label>
                    <input name="password" type="password" value=""<%=userVO.getPassword()%> />

                    <fieldset>
                        <legend>성별</legend>

                        <label>여</label> <input type="radio" name="gender"
                                                value="female"<%=userVO.getGender().equals("female")?"checked":""%> >
                        <label>남</label> <input type="radio" name="gender"
                                                value="male"<%=userVO.getGender().equals("male")?"checked":""%>>

                    </fieldset>

                    <input type="submit" value="수정완료">

                </form>
            </div><!-- /user -->
        </div><!-- /content -->
    </div><!-- /wrapper -->

    <jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

</div> <!-- /container -->

</body>
</html>
