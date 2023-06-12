<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<link rel="stylesheet" href="style.css" />
</head>
<%


	String name = request.getParameter("name");
	String order = request.getParameter("order");
	String originalName = request.getParameter("original_name");
	String originalOrder = request.getParameter("original_order");
	String editName = request.getParameter("editName");
	String editOrder = request.getParameter("editOrder");
	String edit = request.getParameter("edit");

	Bookmark bookMark = new Bookmark(originalName, originalOrder, editName, editOrder);

	if(edit != null){
		response.sendRedirect("bookmark_group.jsp");
	}
%>
<body>
	<h1> 북마크 그룹 수정 </h1>

		<a href="./">홈</a>
		<a href="./history.jsp">위치 히스토리 목록</a>
		<a href="./wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
		<a href="./bookmark.jsp">북마크 보기</a>
		<a href="./bookmark_group.jsp">북마크 그룹 관리</a>

	<form id="bookMarkEditForm" action="./bookmark_group_edit.jsp" method="POST">
		<table>
			<thead>
				<tr>
					<th>북마크 이름</th>
					<td>
						<input id="editName" name="editName" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input id="editOrder" name="editOrder" type="text" value="" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="./bookmark_group.jsp">돌아가기</a> | <button onclick="edit()">수정</button>
					</td>
				</tr>
			</thead>
		</table>
	</form>

	<script>

		const urlParams = new URL(location.href).searchParams;
		const original_name = urlParams.get('name');
		const original_order = urlParams.get('order');

		document.getElementById("editName").value = original_name;
		document.getElementById("editOrder").value = original_order;

		function edit() {
			event.preventDefault();
			const form = document.getElementById("bookMarkEditForm");

			const hiddenInput = document.createElement("input");
			hiddenInput.setAttribute("type", "hidden");
			hiddenInput.setAttribute("name", "original_name");
			hiddenInput.setAttribute("value", original_name);
			form.appendChild(hiddenInput);

			const hiddenInput2 = document.createElement("input");
			hiddenInput2.setAttribute("type", "hidden");
			hiddenInput2.setAttribute("name", "original_order");
			hiddenInput2.setAttribute("value", original_order);
			form.appendChild(hiddenInput2);

			const hiddenInput3 = document.createElement("input");
			hiddenInput3.setAttribute("type", "hidden");
			hiddenInput3.setAttribute("name", "edit");
			hiddenInput3.setAttribute("value", "edit");
			form.appendChild(hiddenInput3);

			form.submit();
		}

	</script>
</body>
</html>