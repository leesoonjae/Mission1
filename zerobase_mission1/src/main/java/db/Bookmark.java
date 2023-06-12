package db;

import java.util.*;
import java.sql.*;

public class Bookmark {


    private String name;
    private int order;
    private int Id;
    private String regiDate;
    private String modiDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(String regiDate) {
        this.regiDate = regiDate;
    }

    public String getModiDate() {
        return modiDate;
    }

    public void setModiDate(String modiDate) {
        this.modiDate = modiDate;
    }


    public Bookmark() {};
    public Bookmark(String name, String order) {
        if (name != null && order != null) {
            this.name = name;
            this.order = Integer.parseInt(order);
            createBookMarkGroup(this.name, this.order);
        }

    }

    public Bookmark(String originalName, String originalOrder , String editName , String editOrder) {
        if (editName != null && editOrder != null && originalName != null && originalOrder != null) {
            editBookmarkGroup(originalName, originalOrder, editName, editOrder);
        }
    }


    // 북마크 그룹 추가
    private void createBookMarkGroup(String name, int order) {

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

            String sql = " INSERT INTO BOOKMARK_GROUP (\"NAME\", \"ORDER\", \"RG_DT\") "
                    + "	values (? , ? , datetime('now', 'localtime')); ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, order);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장에 성공하셨씁니다.");
            } else {
                System.out.println("저장에 실패하셨습니다.");
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
    // 북마크 그룹 리스트
    public List<Bookmark> bookMarkGroupList() {

        List<Bookmark> list = new ArrayList<>();

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


            String sql = " select * FROM BOOKMARK_GROUP order by \"order\" ASC ; ";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Bookmark bookMark = new Bookmark();

                bookMark.setId(resultSet.getInt("ID"));
                bookMark.setName(resultSet.getString("NAME"));
                bookMark.setOrder(resultSet.getInt("ORDER"));
                bookMark.setRegiDate(resultSet.getString("REGI_DATE"));
                bookMark.setModiDate(resultSet.getString("MODI_DATE"));

                list.add(bookMark);


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
    // 북마크 그룹 수정
    private void editBookmarkGroup (String originalName, String originalOrder , String editName , String editOrder) {

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


            String sql = " UPDATE BOOKMARK_GROUP set name = ? , \"ORDER\" = ? , ED_DT = DATETIME('now','localtime') WHERE NAME = ? and \"ORDER\" = ? ; ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, editName);
            preparedStatement.setString(2, editOrder);
            preparedStatement.setString(3, originalName);
            preparedStatement.setString(4, originalOrder);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("수정에 성공하셨습니다.");
            } else {
                System.out.println("수정에 실패하셨습니다.");
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
    // 북마크 그룹 삭제
    public void deleteBookmarkGroup (String name) {
        System.out.println(name);
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


            String sql = " DELETE FROM BOOKMARK_GROUP WHERE NAME = ? ; ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("삭제에 성공하셨습니다.");
            } else {
                System.out.println("삭제에 실패하셨습니다.");
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
}
