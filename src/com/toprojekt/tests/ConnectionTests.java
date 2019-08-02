package com.toprojekt.tests;

import com.toprojekt.gets.GetMetas;
import com.toprojekt.gets.GetRepo;
import com.toprojekt.gets.GetRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionTests{

    public static final String driverName = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/pojedzone";
    public static final String uid = "root";
    public static final String pwd = "";

    @Test
    public void shouldGetMetas() throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection(driverName, url, uid, pwd);
        String table = getMetas.getConcreteTable(0);
        Assertions.assertNotNull(table);
        Assertions.assertEquals("pieczywo", table);

        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        Assertions.assertEquals("id_pieczywa", columns.get(0));
        Assertions.assertEquals("nazwa", columns.get(1));

        String primaryKey = getMetas.getPrimaryKeyOfTheTable(table);
        Assertions.assertEquals("id_pieczywa", primaryKey);
    }

    @Test
    public void shouldSqlOperationsWork() throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection(driverName, url, uid, pwd);
        Connection connection = getMetas.getConnection();
        GetRepoImpl getRepo = new GetRepoImpl();
        String table = getMetas.getConcreteTable(0);
        ResultSet resultSet = getRepo.getAllFromTable(connection, table);
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        getRepo.printResult(resultSet, columns);
        List <String> values = new ArrayList<String>();
        values.add("6");
        values.add("'bagietka'");
        getRepo.insertIntoTable(connection, columns, values ,table);
        List <String> values2 = new ArrayList<String>();
        values2.add("7");
        values2.add("'rogal'");
        getRepo.insertIntoTable(connection, columns, values2 ,table);
        resultSet = getRepo.getAllFromTable(connection, table);
        getRepo.printResult(resultSet, columns);
        String primaryKey = getMetas.getPrimaryKeyOfTheTable(table);
        List<String> values3 = new ArrayList<String>();
        values3.add("7");
        values3.add("'kajzerka'");
        getRepo.changeInTable(connection, table, columns, values3, primaryKey, Long.toString(7));
        resultSet = getRepo.getAllFromTable(connection, table);
        getRepo.printResult(resultSet, columns);
        getRepo.deleteFromTable(connection,table,primaryKey,Long.toString(6));
        getRepo.deleteFromTable(connection,table,primaryKey,Long.toString(7));
        resultSet = getRepo.getAllFromTable(connection, table);
        getRepo.printResult(resultSet, columns);
    }


}
