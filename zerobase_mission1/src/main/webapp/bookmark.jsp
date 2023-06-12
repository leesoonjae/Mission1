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
        function deleteBookMark(e) {
            const table = document.getElementById ("bookTable");
            const i = e.parentElement.parentElement.rowIndex;

            document.getElementById("id").value = table.rows[i].cells[0].innerHTML;
            document.getElementById("bookmark_name").value = table.rows[i].cells[1].innerHTML;
            document.getElementById("wifi_name").value = table.rows[i].cells[2].innerHTML;
            document.getElementById("regi_dt").value = table.rows[i].cells[3].innerHTML;

            form.submit();
        }
    </script>

</head>
<%
	BookmarkGroup bookMarkList = new BookmarkGroup();
	List<BookmarkGroup> list = bookMarkList.showBookmarkGroup();
%>
<body>
	<h1> 북마크 목록 </h1>

		<a href="./">홈</a>
		<a href="./history.jsp">위치 히스토리 목록</a>
		<a href="./wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="./bookmark.jsp">북마크 보기</a>
		<a href="./bookmark_group.jsp">북마크 그룹 관리</a>

	<table id="bookTable">
		<thead>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">북마크 이름</th>
				<th style="text-align: center;">와이파이명</th>
				<th style="text-align: center;">등록일자</th>
				<th style="text-align: center;">비고</th>
			</tr>
		</thead>
		<tbody>

		<% for (BookmarkGroup bmg : list) {%>
			<tr class="markSelect">
				<td><%=bmg.getBookmarkID() %></td>
				<td><%=bmg.getBookmarkName() %></td>
				<td><%=bmg.getWifiName() %></td>
				<td><%=bmg.getOrderNum() %></td>
				<td align="center"><button type="button" onclick="deleteBookMark(this)">삭제</button></td>
			</tr>
		<% } %>

		</tbody>
	</table>

	<form id="form" action="bookmark-delete.jsp" method="POST">
		<input id="id" name="id" type="hidden" value=""/>
		<input id="bookmark_name" name="bookmark_name" type="hidden" value=""/>
		<input id="wifi_name" name="wifi_name" type="hidden" value=""/>
		<input id="regi_dt" name="regi_dt" type="hidden" value=""/>
	</form>

</body>
</html>