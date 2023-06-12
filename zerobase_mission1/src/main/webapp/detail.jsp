<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" href="style.css" />
</head>

<body>
	<h1>와이파이 정보 구하기</h1>

	    <a href="index.jsp">홈</a>
        <a href="history.jsp">위치 히스토리 목록</a>
        <a href="wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="bookmark.jsp">북마크 보기</a>
        <a href="bookmark_group.jsp">북마크 그룹 관리</a>

	<%
		request.setCharacterEncoding("UTF-8");

		String wifiNo = request.getParameter("wifiNo");
		String wifiDis = request.getParameter("wifiDis");
		String bookMarkGroupName = request.getParameter("bookMarkGroupName");
		String wifiName = request.getParameter("wifiName");
		String check = request.getParameter("check");

		BookMark bookMark = new BookMark();
		List<BookMark> bookMarkGroupList = bookMark.bookMarkGroupList();

		Wifi wifi = new Wifi();
		List<Wifi> list = wifi.detailList(wifiNo);

		new BookMarkList(bookMarkGroupName, wifiName, check);

		if(check != null){
			response.sendRedirect("bookmark-list.jsp");
		}
	%>

	<form id="addBookMarkForm" method="POST">
		<select name="bookmarkGroup">
			<option label="북마크 그룹 이름 선택" />

			<% for (BookMark bmk : bookMarkGroupList) { %>

			<option><%=bmk.getName()%></option>

			<% } %>

		</select>

		<input type="hidden" id="bookMarkGroupName" name="bookMarkGroupName"></input>
		<input type="hidden" id="wifiName" name="wifiName"></input>
		<input type="hidden" id="check" name="check"></input>

		<button id="addBookMarkButton" type="button" onclick="getGroupName()">북마크 추가하기</button>
	</form>

	<table>
		<tbody>

			<% for (Wifi wf : list) { %>

			<tr>
				<th>거리(Km)</th>
				<td><%=wifiDis%></td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td><%=wf.getX_SWIFI_MGR_NO()%></td>
			</tr>
			<tr>
				<th>자치구</th>
				<td><%=wf.getX_SWIFI_WRDOFC()%></td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td id="Name"><%=wf.getX_SWIFI_MAIN_NM()%></td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td><%=wf.getX_SWIFI_ADRES1()%></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><%=wf.getX_SWIFI_ADRES2()%></td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td><%=wf.getX_SWIFI_INSTL_FLOOR()%></td>
			</tr>
			<tr>
				<th>설치유형</th>
				<td><%=wf.getX_SWIFI_INSTL_TY()%></td>
			</tr>
			<tr>
				<th>설치기관</th>
				<td><%=wf.getX_SWIFI_INSTL_MBY()%></td>
			</tr>
			<tr>
				<th>서비스구분</th>
				<td><%=wf.getX_SWIFI_SVC_SE()%></td>
			</tr>
			<tr>
				<th>망종류</th>
				<td><%=wf.getX_SWIFI_CMCWR()%></td>
			</tr>
			<tr>
				<th>설치년도</th>
				<td><%=wf.getX_SWIFI_CNSTC_YEAR()%></td>
			</tr>
			<tr>
				<th>실내외구분</th>
				<td><%=wf.getX_SWIFI_INOUT_DOOR()%></td>
			</tr>
			<tr>
				<th>WIFI접속환경</th>
				<td><%=wf.getConnection()%></td>
			</tr>
			<tr>
				<th>X좌표</th>
				<td><%=wf.getLAT()%></td>
			</tr>
			<tr>
				<th>Y좌표</th>
				<td><%=wf.getLNT()%></td>
			</tr>
			<tr>
				<th>작업일자</th>
				<td><%=wf.getWORK_DTTM()%></td>
			</tr>

			<% } %>

		</tbody>
	</table>