package com.toprojekt.gets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GetRepo {
    ResultSet getAllFromTable(Connection connection, String tableName) throws SQLException;
    ResultSet getFromTableWhereId(Connection connection, String tableName, String primaryKey, String id) throws SQLException;
    void insertIntoTable(Connection connection, List<String> columns, List<String> values, String tableName) throws SQLException;
    void deleteFromTable(Connection connection, String tableName, String primaryKey, String id) throws SQLException;
    void changeInTable(Connection connection, String tableName, List<String> columns, List<String> values, String primaryKey, String id) throws SQLException;
}
