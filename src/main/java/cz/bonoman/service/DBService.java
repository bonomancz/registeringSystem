package cz.bonoman.service;

import java.sql.*;
import java.util.*;

public class DBService {
    private String DBHOST, DB, DBUSER, DBPASSWORD;
    private Connection connection;

    public DBService(){
        this.DBHOST = "192.168.240.50";
        this.DB = "registeringSystem";
        this.DBUSER = "jdbc";
        this.DBPASSWORD = "jdbc20240623";
    }

    public boolean dbConnect() throws SQLException{
        boolean isConnected = true;
        try{
            if(this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + DBHOST + ":3306/" + DB, DBUSER, DBPASSWORD);
            }
        }catch(SQLException e){
            isConnected = false;
            this.dbClose();
            throw(new SQLException("dbConnect(): " + e.getMessage()));
        }
        return isConnected;
    }

    public List<Map<String, Object>> execQuery(String query) throws DatabaseException{
        List<Map<String, Object>> sqlTable = new ArrayList<>();
        try(Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while(rs.next()){
                Map<String, Object> row = new HashMap<>();
                for(int i = 1; i <= columnCount; i++){
                    String columName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columName, value);
                }
                sqlTable.add(row);
            }
        }catch (SQLException e){
            throw new DatabaseException("execQuery(): " + e.getMessage());
        }
        return sqlTable;
    }

    public void execUpdate(String updateQuery) throws DatabaseException{
        try(Statement stmt = this.connection.createStatement()){
            int rowsAffected = stmt.executeUpdate(updateQuery);
            if(rowsAffected < 1){
                throw new DatabaseException("execUpdate(): Query didn't affected any row.");
            }
        }catch (SQLException e){
            throw new DatabaseException("execUpdate(): " + e.getMessage());
        }
    }

    public boolean dbClose() throws SQLException{
        boolean isClosed = true;
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }catch(SQLException e){
            isClosed = false;
            throw(new SQLException("dbClose(): " + e.getMessage()));
        }
        return isClosed;
    }


    public void setDbHost(String input){ this.DBHOST = input;}
    public void setDb(String input){ this.DB = input;}
    public void setDbUser(String input){ this.DBUSER = input;}
    public void setDbPassword(String input){ this.DBPASSWORD = input;}
}
