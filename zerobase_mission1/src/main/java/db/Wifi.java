package db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Wifi {

    private Double distance; // 거리(Km)
    private String X_SWIFI_MGR_NO; // 관리번호
    private String X_SWIFI_WRDOFC; // 자치구
    private String X_SWIFI_MAIN_NM; // 와이파이명
    private String X_SWIFI_ADRES1; // 도로명주소
    private String X_SWIFI_ADRES2; // 상세주소
    private String X_SWIFI_INSTL_FLOOR; // 설치위치(층)
    private String X_SWIFI_INSTL_TY; // 설치유형
    private String X_SWIFI_INSTL_MBY; // 설치기관
    private String X_SWIFI_SVC_SE; // 서비스구분
    private String X_SWIFI_CMCWR; // 망종류
    private String X_SWIFI_CNSTC_YEAR; // 설치년도
    private String X_SWIFI_INOUT_DOOR; // 실내외 구분
    private String connection; // wifi 접속환경
    private Double LAT; // x좌표
    private Double LNT; // y좌표
    private String WORK_DTTM; // 작업일자


    public Double getdistance() { return distance; }

    public void setdistance(Double distance) { this.distance = distance;}
    public String getX_SWIFI_MGR_NO() {
        return X_SWIFI_MGR_NO;
    }

    public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
        X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
    }

    public String getX_SWIFI_WRDOFC() {
        return X_SWIFI_WRDOFC;
    }

    public void setX_SWIFI_WRDOFC(String x_SWIFI_WRDOFC) {
        X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
    }

    public String getX_SWIFI_MAIN_NM() {
        return X_SWIFI_MAIN_NM;
    }

    public void setX_SWIFI_MAIN_NM(String x_SWIFI_MAIN_NM) {
        X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
    }

    public String getX_SWIFI_ADRES1() {
        return X_SWIFI_ADRES1;
    }

    public void setX_SWIFI_ADRES1(String x_SWIFI_ADRES1) {
        X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
    }

    public String getX_SWIFI_ADRES2() {
        return X_SWIFI_ADRES2;
    }

    public void setX_SWIFI_ADRES2(String x_SWIFI_ADRES2) {
        X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
    }

    public String getX_SWIFI_INSTL_FLOOR() {
        return X_SWIFI_INSTL_FLOOR;
    }

    public void setX_SWIFI_INSTL_FLOOR(String x_SWIFI_INSTL_FLOOR) {
        X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
    }

    public String getX_SWIFI_INSTL_TY() {
        return X_SWIFI_INSTL_TY;
    }

    public void setX_SWIFI_INSTL_TY(String x_SWIFI_INSTL_TY) {
        X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
    }

    public String getX_SWIFI_INSTL_MBY() {
        return X_SWIFI_INSTL_MBY;
    }

    public void setX_SWIFI_INSTL_MBY(String x_SWIFI_INSTL_MBY) {
        X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
    }

    public String getX_SWIFI_SVC_SE() {
        return X_SWIFI_SVC_SE;
    }

    public void setX_SWIFI_SVC_SE(String x_SWIFI_SVC_SE) {
        X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
    }

    public String getX_SWIFI_CMCWR() {
        return X_SWIFI_CMCWR;
    }

    public void setX_SWIFI_CMCWR(String x_SWIFI_CMCWR) {
        X_SWIFI_CMCWR = x_SWIFI_CMCWR;
    }

    public String getX_SWIFI_CNSTC_YEAR() {
        return X_SWIFI_CNSTC_YEAR;
    }

    public void setX_SWIFI_CNSTC_YEAR(String x_SWIFI_CNSTC_YEAR) {
        X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
    }

    public String getX_SWIFI_INOUT_DOOR() {
        return X_SWIFI_INOUT_DOOR;
    }

    public void setX_SWIFI_INOUT_DOOR(String x_SWIFI_INOUT_DOOR) {
        X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        connection = connection;
    }

    public Double getLAT() {
        return LAT;
    }

    public void setLAT(Double LAT) {
        this.LAT = LAT;
    }

    public Double getLNT() {
        return LNT;
    }

    public void setLNT(Double LNT) {
        this.LNT = LNT;
    }

    public String getWORK_DTTM() {
        return WORK_DTTM;
    }

    public void setWORK_DTTM(String WORK_DTTM) {
        this.WORK_DTTM = WORK_DTTM;
    }


    public int totalCount = 0;
    static private int page = 1;
    static private int count = 1000;
    static private int totalList = 0;


    public void url() throws IOException {
        while (true) {

            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" +  URLEncoder.encode("4a6343756d74656e353561616b6a7a","UTF-8") );
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(page),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(count),"UTF-8"));


            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            Wifi test = new Wifi();
            totalCount += test.parsing(sb.toString());

            page += 1000;
            count += 1000;
            if (page > totalList) {
                break;
            }

        }

    }

    public int parsing(String result) {

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(result);
        totalList = ((JsonObject)jsonObject.get("TbPublicWifiInfo")).get("list_total_count").getAsInt();
        JsonArray arr = ((JsonObject) jsonObject.get("TbPublicWifiInfo")).get("row").getAsJsonArray();

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            String dbFile = "/opt/homebrew/bin/wifi";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

            for (JsonElement jsonElement : arr) {
                JsonObject tmp = jsonElement.getAsJsonObject();


                String sql = " insert into WIFI_LIST (NO, GU, WIFI_NAME, DORO_ADDRESS, DETAIL_ADDRESS, INSTALL_LOCATION, INSTALL_TYPE, INSTALL_AGENCY, DIVISION, NETWORK_TYPE, INSTALL_YEAR, IN_OUT, CONNECTION, X, Y, DT) "
                        + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); "
                        ;

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, tmp.get("X_SWIFI_MGR_NO").toString().replace("\"", ""));
                preparedStatement.setString(2, tmp.get("X_SWIFI_WRDOFC").toString().replace("\"", ""));
                preparedStatement.setString(3, tmp.get("X_SWIFI_MAIN_NM").toString().replace("\"", ""));
                preparedStatement.setString(4, tmp.get("X_SWIFI_ADRES1").toString().replace("\"", ""));
                preparedStatement.setString(5, tmp.get("X_SWIFI_ADRES2").toString().replace("\"", ""));
                preparedStatement.setString(6, tmp.get("X_SWIFI_INSTL_FLOOR").toString().replace("\"", ""));
                preparedStatement.setString(7, tmp.get("X_SWIFI_INSTL_TY").toString().replace("\"", ""));
                preparedStatement.setString(8, tmp.get("X_SWIFI_INSTL_MBY").toString().replace("\"", ""));
                preparedStatement.setString(9, tmp.get("X_SWIFI_SVC_SE").toString().replace("\"", ""));
                preparedStatement.setString(10, tmp.get("X_SWIFI_CMCWR").toString().replace("\"", ""));
                preparedStatement.setString(11, tmp.get("X_SWIFI_CNSTC_YEAR").toString().replace("\"", ""));
                preparedStatement.setString(12, tmp.get("X_SWIFI_INOUT_DOOR").toString().replace("\"", ""));
                preparedStatement.setString(13, tmp.get("X_SWIFI_REMARS3").toString().replace("\"", ""));
                preparedStatement.setDouble(14, tmp.get("LAT").getAsDouble());
                preparedStatement.setDouble(15, tmp.get("LNT").getAsDouble());
                preparedStatement.setString(16, tmp.get("WORK_DTTM").toString().replace("\"", ""));

                int affected = preparedStatement.executeUpdate();

                if (affected > 0) {
                    totalCount++;
                } else {
                    System.out.println(" 저장 실패 ");
                }


            }


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return totalCount;
    }

    public List<Wifi> detailList(String wifiNo) {
        List<Wifi> list = new ArrayList<Wifi>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            String dbFile = "/opt/homebrew/bin/wifi";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);


            String sql = " SELECT * from WIFI_LIST where no = ? ; ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiNo);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Wifi wifi = new Wifi();

                wifi.setX_SWIFI_MGR_NO(resultSet.getString("NO"));
                wifi.setX_SWIFI_WRDOFC(resultSet.getString("GU"));
                wifi.setX_SWIFI_MAIN_NM(resultSet.getString("WIFI_NAME"));
                wifi.setX_SWIFI_ADRES1(resultSet.getString("DORO_ADDRESS"));
                wifi.setX_SWIFI_ADRES2(resultSet.getString("DETAIL_ADDRESS"));
                wifi.setX_SWIFI_INSTL_FLOOR(resultSet.getString("INSTALL_LOCATION"));
                wifi.setX_SWIFI_INSTL_TY(resultSet.getString("INSTALL_TYPE"));
                wifi.setX_SWIFI_INSTL_MBY(resultSet.getString("INSTALL_AGENCY"));
                wifi.setX_SWIFI_SVC_SE(resultSet.getString("DIVISION"));
                wifi.setX_SWIFI_CMCWR(resultSet.getString("NETWORK_TYPE"));
                wifi.setX_SWIFI_CNSTC_YEAR(resultSet.getString("INSTALL_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(resultSet.getString("IN_OUT"));
                wifi.setConnection(resultSet.getString("CONNECTION"));
                wifi.setLAT(resultSet.getDouble("LAT"));
                wifi.setLNT(resultSet.getDouble("LNT"));
                wifi.setWORK_DTTM(resultSet.getString("DT"));


                list.add(wifi);

            }



        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return list;
    }

}
