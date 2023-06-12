<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<link rel="stylesheet" href="style.css" />

    <script>
        function add(event) {
            event.preventDefault();

            const name = document.getElementById("name").value;
            const order = document.getElementById("order").value;

            document.getElementById("bookmarkadd").submit();
        }
    </script>
</head>
<%
	String name = request.getParameter("name");
	String order = request.getParameter("order");

	BookMark bookMark = new BookMark(name, order);

	if(name != null && order != null){
		response.sendRedirect("bookmark_group.jsp");
	}
%>
<body>
	<h1> 북마크 그룹 추가 </h1>

		<a href="./">홈</a>
		<a href="./history.jsp">위치 히스토리 목록</a>
		<a href="./wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="./bookmark.jsp">북마크 보기</a>
		<a href="./bookmark_group.jsp">북마크 그룹 관리</a>

	<form id="bookmarkadd">
		<table>
			<thead>
				<tr>
					<th>북마크 이름</th>
					<td>
						<input id="name" name="name" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input id="order" name="order" type="text" value="" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button onclick="add(event)">추가</button></td>
				</tr>
			</thead>
		</table>
	</form>

</body>
</html>