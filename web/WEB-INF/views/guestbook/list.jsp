<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>
	<div id="container">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		<div id="wrapper">
			<div id="contents">
				<div id="guestbook">
					
					<form action="/GuestbookController" method="get">
                        <input type="hidden" name ="cmd" value = "insert">
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" value="${sessionScope.userVO.name}"/></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
							<table>
								<%--반복문--%>
								<c:forEach items="${requestScope.list}" var="vo">
								<tr>
                                    <td>[${vo.no}]</td>
                                    <td>${vo.name}</td>
                                    <td>${vo.date}</td>
									<td><a href="/GuestbookController?cmd=delete_form&no=${vo.no}">삭제</a></td>
								</tr>
								<tr>
                                    <td colspan=4>${vo.content}</td>
								</tr>
								<%--반복끝--%>
								</c:forEach>
							</table>
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
	</div> <!-- /container -->

</body>
</html>