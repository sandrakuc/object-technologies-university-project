package com.toprojekt.gets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetRepoImpl implements GetRepo {

    public ResultSet getAllFromTable(Connection connection, String tableName) throws SQLException {
        String getAll = String.format("SELECT * FROM %s", tableName);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAll);
        return resultSet;
    }

    public void printResult(ResultSet resultSet, List<String> columns) throws SQLException {
        for(String column: columns){
            System.out.print(column + "\t");
        }System.out.print("\n");
        while(resultSet.next()){
            for(String column: columns){
                String result = resultSet.getString(column);
                System.out.print(result + "\t");
            }System.out.println("\n");
        }

    }

    public ResultSet getFromTableWhereId(Connection connection, String tableName, String primaryKey, String id) throws SQLException {
        String getAll = String.format("SELECT * FROM %s WHERE %s = %s",tableName,primaryKey,id);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAll);
        return resultSet;
    }

    public void insertIntoTable(Connection connection, List<String> columns, List<String> values, String tableName) throws SQLException {
        String ins = String.format("INSERT INTO %s VALUES( ", tableName);
        for(int i=0; i<values.size(); i++){
            ins += values.get(i);
            if(i!=values.size()-1){
                ins += ", ";
            }else {
                ins += ")";
            }
        }
        Statement statement = connection.createStatement();
        statement.execute(ins);
    }

    public void deleteFromTable(Connection connection, String tableName, String primaryKey, String id) throws SQLException {
        String getAll = String.format("DELETE FROM %s WHERE %s = %s",tableName,primaryKey,id);
        Statement statement = connection.createStatement();
        statement.execute(getAll);
    }

    public void changeInTable(Connection connection, String tableName, List<String> columns, List<String> values, String primaryKey, String id) throws SQLException {
        String update = String.format("UPDATE %s SET ", tableName);
        for(int i=0; i<values.size(); i++){
            update += columns.get(i) + " = " + values.get(i);
            if(i!=values.size()-1){
                update += ", ";
            }
        }update += " WHERE " + primaryKey + " = " + id;
        System.out.println(update);
        Statement statement = connection.createStatement();
        statement.execute(update);
    }
}
