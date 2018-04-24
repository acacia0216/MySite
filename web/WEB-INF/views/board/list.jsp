<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">

    <jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

    <div id="content">
        <div id="board">
            <form id="search_form" action="/BoardController?cmd=searchContent" method="post">
                <input type="text" id="kwd" name="kwd" value="">
                <input type="submit" value="찾기">
            </form>
            <table class="tbl-ex" style=TABLE-layout:fixed;>

                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr
                <%--for문 시작--%>
                <c:forEach items="${requestScope.list}" var="vo">
                <tr>
                    <td>${vo.no}</td>
                    <td style="text-overflow : ellipsis;overflow : hidden;">
                        <nobr><a href="/BoardController?cmd=board_view&no=${vo.no}">${vo.title}</a></nobr>
                    </td>
                    <td>${vo.name}</td>
                    <td>${vo.hit}</td>
                    <td>
                            ${vo.reg_date}
                    </td>
                    <td>
                            <%--<c:choose>--%>
                        <c:if test="${userVO.no eq vo.user_no}">
                            <a href="/BoardController?cmd=board_delete&no=${vo.no}" class="del">삭제</a>
                        </c:if>
                    </td>
                        <%--<c:otherwise>--%>
                        <%--<td></td>--%>
                        <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <%--</tr>--%>
                        <%--for문 끝--%>
                </tr>
                    </c:forEach>

            </table>
            ${requestScope.allPage.size()/10}
            <div class="pager">
                <ul>
                    <%--<c:if test="${5개 페이지보다 작은 페이지가 있으면}">--%>
                        <%--<li><a href="">◀</a></li>--%>
                    <%--</c:if>--%>
                    <c:forEach items="${requestScope.allPage.size()/10}" var="number">
                        <c:if test="${number}">
                            <li><a class="selected">${number}</a></li>
                        </c:if>
                        <c:if test="${number}">
                            <li><a href="/BoardController?cmd=board&page=${number}">${number}</a></li>
                        </c:if>
                    </c:forEach>
                    <%--<c:if test="${5개 페이지보다 큰 페이지가 있으면}">--%>
                        <%--<li><a href="">▶</a></li>--%>
                    <%--</c:if>--%>
                </ul>
            </div>
            <div class="bottom">
                <a href="/BoardController?cmd=write_page" id="new-book">글쓰기</a>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

</div>
</body>
</html>