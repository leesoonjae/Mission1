package db;

import java.util.*;
import java.sql.*;

public class BookmarkList {

    private int bookmarkID;
    private String bookmarkName;
    private String wifiName;
    private String regiDate;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public int getBookmarkID() {
        return bookmarkID;
    }

    public void setBookmarkID(int bookmarkID) {
        this.bookmarkID = bookmarkID;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(String regiDate) {
        this.regiDate = regiDate;
    }

    public BookmarkList() {};

    public BookmarkList(String bookmarkName, String wifiName, String check) {
        if (bookmarkName != null && wifiName != null && "click".equals(check)) {
            addBookMark(bookmarkName, wifiName);
        }
    }

    public BookmarkList(String check, String deleteId) {
        if ("click".equals(check) && deleteId != null) {
            deleteBookMark (Integer.parseInt(deleteId));
        }
    }

    //북마크 추가
    private void addBookMark(String bookmarkName, String wifiName) {



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

            String sql = " INSERT INTO BOOKMARK (BOOKMARK_NAME , WIFI_NAME, REGI_DATE) "
                    + "	values ( ? , ? , datetime('now','localtime')); ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setString(2, wifiName);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장애 성공하셨습니다. ");
            } else {
                System.out.println(" 저장에 실패하셨습니다. ");
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

    }

    // 북마크 삭제
    private void deleteBookMark (int bookmarkID) {

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


            String sql = " DELETE FROM BOOKMARK WHERE ID = ? ; ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bookmarkID);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
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

    }

    //북마크 체크 리스트
    public List<BookmarkList> showBookMarkList() {

        List<BookmarkList> list = new ArrayList<>();

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


            String sql = " SELECT * from BOOKMARK ; ";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                BookmarkList bookMarkList = new BookmarkList();

                bookMarkList.setBookmarkID(resultSet.getInt("BOOKMARK_ID"));
                bookMarkList.setBookmarkName(resultSet.getString("BOOKMARK_NAME"));
                bookMarkList.setWifiName(resultSet.getString("WIFI_NAME"));
                bookMarkList.setRegiDate(resultSet.getString("REGI_DATE"));

                list.add(bookMarkList);


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
