<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.*" %>
<%

	String ID = request.getParameter();

	History history = new History();
	List<History> list = history.historyList();
	history.delete(ID);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>히스토리</title>
	<link rel="stylesheet" href="style.css" />

    <script>
        function getRow() {

            let historyTable = document.getElementById("historyTable");
            let deleteId = "";

            for (let i = 1; i < historyTable.rows.length; i++) {
                historyTable.rows[i].cells[4].onclick=function () {
                    deleteId = historyTable.rows[i].cells[0].innerText;
                    console.log(deleteId);
                    historyTable.deleteRow(i);

                     $.ajax({
                            url: 'history.jsp',
                            type: 'POST',
                            data: { id: deleteId },
                            success: function(response) {
                              console.log('데이터 삭제 성공');
                            },
                            error: function(xhr, status, error) {
                              console.error('데이터 삭제 실패:', error);
                            }

                    });

                }

            }

        }
    </script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1> 위치 히스토리 목록 </h1>

		<a href="./">홈</a>
		<a href="./history.jsp">위치 히스토리 목록</a>
		<a href="./wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>

	<table id="historyTable">
		<tbody>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>

		<%
			for (int i = 0; i < list.size(); i++) {
				History h = list.get(i);
		%>
			<tr>

				<td><%=h.getID() %></td>
				<td><%=h.getX() %></td>
				<td><%=h.getY() %></td>
				<td><%=h.getDistance().toString() %></td>
				<td id="deleteHistory" align="center">
					<button onclick="getRow()" >삭제</button>
				</td>

			</tr>
		<% } %>

		</tbody>
	</table>


</body>
</html>