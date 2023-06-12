<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<link rel="stylesheet" href="style.css" />

    <script>
        function getValue(e) {
            event.preventDefault();

            const name = e.parentNode.parentNode.cells[1].innerHTML;//name
            const order = e.parentNode.parentNode.cells[2].innerHTML;//order

            location.href="./bookmark_group_edit.jsp?name=" + name + "&order=" + order;
        }

        function deleteRow(e) {
            event.preventDefault();

            const idx = e.parentNode.parentNode.rowIndex;
            const name = e.parentNode.parentNode.cells[1].innerHTML;//name
            const myTable = document.getElementById('bookTable');

            myTable.deleteRow(idx);

            const hiddenInput = document.createElement("input");

            hiddenInput.setAttribute("type", "hidden");
            hiddenInput.setAttribute("name", "name");
            hiddenInput.setAttribute("value", name);

            form.appendChild(hiddenInput);

            form.submit();
        }
    </script>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	BookMark bookMark = new BookMark();
	String name = request.getParameter("name");

	if (name != null) {
		bookMark.deleteBookMarkGroup(name);
	}

	List<BookMark> list = bookMark.bookMarkGroupList();
%>
<body>
	<h1> 북마크 그룹 </h1>

		<a href="./">홈</a>
		<a href="./history.jsp">위치 히스토리 목록</a>
		<a href="./wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="./bookmark.jsp">북마크 보기</a>
		<a href="./bookmark_group.jsp">북마크 그룹 관리</a>

	<a onclick="location.href='bookmark_group_add.jsp'"><button>북마크 그룹 이름 추가</button></a>

	<form id="form" method="POST">
		<table id="bookTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th>비고</th>
				</tr>
			</thead>

			<tbody>
			<% for (BookMark b : list) { %>
				<tr>
					<td><%=b.getId() %></td>
					<td><%=b.getName() %></td>
					<td><%=b.getOrder() %></td>
					<td><%=b.getRegiDate() %></td>
					<td><%=b.getModiDate() %></td>
					<td align="center">
						<button id="editButton" onclick="getValue(this)">수정</button> <button onclick="deleteRow(this)">삭제</button>
					</td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</form>

</body>
</html>