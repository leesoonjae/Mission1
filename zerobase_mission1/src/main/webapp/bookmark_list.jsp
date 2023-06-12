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
</head>
<%
	BookmarkList bookMarkList = new BookmarkList();
	List<BookmarkList> list = bookMarkList.showBookMarkList();
%>
<body>
	<h1> 북마크 목록 </h1>

	    <a href="index.jsp">홈</a>
        <a href="history.jsp">위치 히스토리 목록</a>
        <a href="wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="bookmark.jsp">북마크 보기</a>
        <a href="bookmark_group.jsp">북마크 그룹 관리</a>

	<table id="bookTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>

		<% for (BookmarkList b : list) {%>
			<tr class="select">
				<td><%=b.getBookmarkID() %></td>
				<td><%=b.getBookmarkName() %></td>
				<td><%=b.getWifiName() %></td>
				<td><%=b.getRegiDate() %></td>
				<td align="center"><button type="button" onclick="deleteButton(this)">삭제</button></td>
			</tr>
		<% } %>

		</tbody>
	</table>

	<form id="form" action="bookmark.jsp" method="POST">
		<input id="id" name="id" type="hidden" value=""/>
		<input id="bookmark-name" name="bookmark-name" type="hidden" value=""/>
		<input id="wifi-name" name="wifi-name" type="hidden" value=""/>
		<input id="rg-dt" name="rg-dt" type="hidden" value=""/>
	</form>

	<script>
		function deleteButton(e) {
			const table = document.getElementById ("bookTable");
			const i = e.parentElement.parentElement.rowIndex;

			document.getElementById("ID").value = table.rows[i].cells[0].innerHTML;
			document.getElementById("bookmark_name").value = table.rows[i].cells[1].innerHTML;
			document.getElementById("wifi_name").value = table.rows[i].cells[2].innerHTML;
			document.getElementById("regi_dt").value = table.rows[i].cells[3].innerHTML;

			form.submit();
		}
	</script>

</body>
</html>