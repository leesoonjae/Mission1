package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class History {

    private int ID;
    private String X;
    private String Y;
    private LocalDateTime checkDate;
    private Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String Y) { Y = Y; }

    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDateTime checkDate) {
        this.checkDate = checkDate;
    }
    public History() {}

    public History(String X, String Y) {
        this.X = X;
        this.Y = Y;

        if (this.X != null && this.Y != null) {
            historyTable(Double.parseDouble(this.X), Double.parseDouble(this.Y));
        }
    }

    public void delete(String ID) {
        if (ID != null) {
            deleteHistory (Integer.parseInt(ID));
        }
    }

    // 히스토리 목록
    public List<History> historyList() {

        List<History> list = new ArrayList<>();

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


            String sql = " select * from HISTORY order by ID desc; ";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                History history = new History();

                history.setX(resultSet.getString("X"));
                history.setY(resultSet.getString("Y"));
                history.setID(resultSet.getInt("ID"));
                history.setCheckDate(resultSet.getTimestamp("CHECK_DATE").toLocalDateTime());

                list.add(history);

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

    // 히스토리 저장
    private void historyTable (double dX, double dY) {

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

            boolean tableExists = checkTableExists(connection);

            if (!tableExists) {

                createTable(connection);
            }

            String sql = " INSERT into HISTORY (X, Y, CHECK_DATE) "
                    + "	values (?, ?, DateTime('now','localtime')); "
                    ;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, dX);
            preparedStatement.setDouble(2, dY);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
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

    // 히스토리 테이블 존재 유무 확인
    private boolean checkTableExists(Connection connection) throws Exception {


        String query = " SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='HISTORY'; ";

        Statement statement = connection.createStatement();
        boolean tableExists = false;

        // 쿼리 실행 및 확인
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            tableExists = count > 0;
        }

        resultSet.close();
        statement.close();

        return tableExists;
    }

    // 히스토리 테이블 생성
    private void createTable(Connection connection) throws Exception {
        // 테이블 생성 쿼리
        String query = "CREATE TABLE \"HISTORY\" ( "
                + "	\"ID\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "	\"X\" real, "
                + "	\"Y\" real, "
                + "	\"INQ_DT\" text ); ";

        Statement statement = connection.createStatement();

        // 쿼리 실행
        statement.executeUpdate(query);

        statement.close();
    }

    // 히스토리 삭제
    private void deleteHistory (int deleteid) {

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


            String sql = " DELETE FROM HISTORY WHERE ID = ?; ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, deleteid);

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


}
