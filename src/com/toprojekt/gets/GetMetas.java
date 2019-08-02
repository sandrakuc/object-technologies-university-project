package com.toprojekt.gets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetMetas {
    private String driverName;

    private String url;

    private String uid;

    private String password;

    private Connection connection;

    public GetMetas(){

    }

    public Connection getConnection(){
        return this.connection;
    }

    public void setConnection(String driverName, String url, String uid, String password) throws ClassNotFoundException, SQLException {
        this.driverName = driverName;
        this.url = url;
        this.uid = uid;
        this.password = password;
        Class.forName(this.driverName);
        this.connection = DriverManager.getConnection(url, uid, password);

    }

    public List<String> getAllTables() throws SQLException, ClassNotFoundException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables;
        if(driverName.equals("oracle.jdbc.driver.OracleDriver")){
            Statement statement = connection.createStatement();
            tables = statement.executeQuery("select table_name from user_tables");
        }else{
            tables = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
        }
        List<String> tableNames = new ArrayList<String>();
        while (tables.next()) {
            tableNames.add(tables.getString("TABLE_NAME"));
        }return tableNames;
    }

    public List<String> getAllColumsOfTheTable(String tableName) throws ClassNotFoundException, SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null);
        List<String> columnNames = new ArrayList<String>();
        while (columns.next()) {
            columnNames.add(columns.getString("COLUMN_NAME"));
        }return columnNames;
    }

    public String getPrimaryKeyOfTheTable(String tableName) throws ClassNotFoundException, SQLException{
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName);
        List<String> primaryKeyNames = new ArrayList<String>();
        while(primaryKeys.next()){
            primaryKeyNames.add(primaryKeys.getString("COLUMN_NAME"));
        }
        String primary = primaryKeyNames.get(0);
        return primary;
    }

    public String getConcreteTable(int index) throws SQLException, ClassNotFoundException {
        List<String> tableNames = getAllTables();
        return tableNames.get(index);
    }
}
