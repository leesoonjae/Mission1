<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="db.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
    <link rel="stylesheet" href="style.css" />
<style>

</style>

<script>
    function myLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showLocation);
        } else {
            alert("지리정보가 없습니다.");
        }
    }

    function showLocation(position) {
        var latval = position.coords.latval;
        var lntval = position.coords.lntval;
        document.getElementById("latval").value = latval;
        document.getElementById("lntval").value = lntval;
    }

    const input_lat = document.querySelector("#lat");
    const input_lnt = document.querySelector("#lnt");
</script>
</head>

    <%

        Double latValue = request.getParameter("X");
        Double lntValue = request.getParameter("Y");

        History hist = new History(latValue, lntValue);

        if(latValue != null && lntValue != null)
            double LAT = Double.valueOf(latValue);
            double LNT = Double.valueOf(lntValue);
    %>

<body>

    <h1>와이파이 정보 구하기</h1>
    <a href="index.jsp">홈</a>
    <a href="history.jsp">위치 히스토리 목록</a>
    <a href="wifi_openAPI.jsp">Open API 와이파이 정보 가져오기</a>
    <a href="bookmark.jsp">북마크 보기</a>
    <a href="bookmark_group.jsp">북마크 그룹 관리</a>

    <form action="./index.jsp" method="GET">
        LAT : <input type="text" id="LAT" name="LAT" value="0.0">
        LNT : <input type="text" id="LNT" name="LNT" value="0.0">
        <button type="button" onclick="myLocation()">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 보기</button>
    </form>

    <table id="wifi_table">
    <thead>
        <tr>
		    <th style="text-align: center;">거리(Km)</th>
		    <th style="text-align: center;">관리번호</th>
		    <th style="text-align: center;">자치구</th>
		    <th style="text-align: center;">와이파이명</th>
		    <th style="text-align: center;">도로명주소</th>
		    <th style="text-align: center;">상세주소</th>
		    <th style="text-align: center;">설치위치(층)</th>
		    <th style="text-align: center;">설치유형</th>
		    <th style="text-align: center;">설치기관</th>
		    <th style="text-align: center;">서비스구분</th>
		    <th style="text-align: center;">망종류</th>
		    <th style="text-align: center;">설치년도</th>
		    <th style="text-align: center;">실내외구분</th>
		    <th style="text-align: center;">wifi접속환경</th>
		    <th style="text-align: center;">X좌표</th>
		    <th style="text-align: center;">Y좌표</th>
		    <th style="text-align: center;">작업일자</th>
        </tr>
    </thead>

    <%
        Wifi wifi = new Wifi();
        List<Wifi> wifiList = null;
    %>

    <tbody>
        <%
            for(Wifi wf : wifiList) {
        %>
            <tr>
            <td><%=wf.getdistance() %></td>
            <td><%=wf.getX_SWIFI_MGR_NO() %></td>
            <td><%=wf.getX_SWIFI_WRDOFC() %></td>
            <td><a href="detail.jsp?X_SWIFI_MAIN_NM=<%=wf.getX_SWIFI_MAIN_NM()%>"><%=wf.getX_SWIFI_MAIN_NM()%></a></td>
            <td><%=wf.getX_SWIFI_ADRES1() %></td>
            <td><%=wf.getX_SWIFI_ADRES2() %></td>
            <td><%=wf.getX_SWIFI_INSTL_FLOOR() %></td>
            <td><%=wf.getX_SWIFI_INSTL_TY() %></td>
            <td><%=wf.getX_SWIFI_INSTL_MBY() %></td>
            <td><%=wf.getX_SWIFI_SVC_SE() %></td>
            <td><%=wf.getX_SWIFI_CMCWR() %></td>
            <td><%=wf.getX_SWIFI_CNSTC_YEAR() %></td>
            <td><%=wf.getX_SWIFI_INOUT_DOOR() %></td>
            <td><%=wf.getConnection() %></td>
            <td><%=wf.getLAT() %></td>
            <td><%=wf.getLNT() %></td>
            <td><%=wf.getWORK_DTTM() %></td>
            </tr>

        <%
            }
        %>
</body>
</html>