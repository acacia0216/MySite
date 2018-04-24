<%@ page import="kr.co.bit.dao.BoardDAO" %>
<%@ page import="kr.co.bit.vo.BoardVO" %>
<%@ page import="kr.co.bit.vo.UserVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="./assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">

					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${requestScope.contents.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
                                ${requestScope.contents.content}
							</div>
						</td>
					</tr>

				</table>
				<div class="bottom">
					<a href="/BoardController?cmd=board">글목록</a>
					<c:if test="${requestScope.contents.user_no eq sessionScope.userVO.no}">
					<a href="/BoardController?cmd=board_modifyform&contentNo=${requestScope.contents.no}">글수정</a>
                    </c:if>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>