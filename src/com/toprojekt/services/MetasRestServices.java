package com.toprojekt.services;

import com.toprojekt.gets.GetMetas;
import com.toprojekt.gets.GetRepo;
import com.toprojekt.gets.GetRepoImpl;
import com.toprojekt.gets.ListOfStringsToStringToTokenizeConverter;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Path("/metas")
public class MetasRestServices {
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    @GET
    @Path("/{tableId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFromTable(@PathParam("tableId") String tableId) throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        int id = Integer.parseInt(tableId);
        String table = getMetas.getConcreteTable(id);
        Connection connection = getMetas.getConnection();
        GetRepoImpl getRepo = new GetRepoImpl();
        ResultSet resultSet = getRepo.getAllFromTable(connection, table);
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        ArrayList<String> results = new ArrayList<String>();
        while(resultSet.next()){
            String result = "";
            int i = 0;
            for(String column: columns){
                result += resultSet.getString(column);
                if(i != columns.size()-1)
                    result += ",";
                i++;
            }results.add(result);
        }
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(results);
    }

    @GET
    @Path("{tableId}/findElement")
    @Produces("text/plain")
    public String getFromTableWhereId(@PathParam("tableId") String tableId, @QueryParam("primaryKeyValue") String primaryKeyValue) throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        String table = getMetas.getConcreteTable(Integer.parseInt(tableId));
        String primaryKey = getMetas.getPrimaryKeyOfTheTable(table);
        Connection connection = getMetas.getConnection();
        GetRepoImpl getRepo = new GetRepoImpl();
        ResultSet resultSet = getRepo.getFromTableWhereId(connection, table, primaryKey, primaryKeyValue);
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        ArrayList<String> results = new ArrayList<String>();
        while(resultSet.next()){
            String result = "";
            int i = 0;
            for(String column: columns){
                result += resultSet.getString(column);
                if(i != columns.size()-1)
                    result += ",";
                i++;
            }results.add(result);
        }
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(results);
    }

    @GET
    @Path("{tableId}/addElement")
    @Produces(MediaType.TEXT_PLAIN)
  //  @Consumes(MediaType.TEXT_PLAIN)
    public String insertIntoTable(@PathParam("tableId") String tableId, @NotNull @QueryParam("values") List<String> values) throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        String table = getMetas.getConcreteTable(Integer.parseInt(tableId));
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        Connection connection = getMetas.getConnection();
        List<String> valuesToInsert = new ArrayList<String>();
        for(String value: values)
            valuesToInsert.add(value);
        GetRepo getRepo = new GetRepoImpl();
        getRepo.insertIntoTable(connection, columns, valuesToInsert, table);
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(values);
    }

    @GET
    @Path("{tableId}/deleteElement")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFromTable(@PathParam("tableId") String tableId,
                                @QueryParam("primaryKeyValue") String primaryKeyValue) throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        String table = getMetas.getConcreteTable(Integer.parseInt(tableId));
        String primaryKey = getMetas.getPrimaryKeyOfTheTable(table);
        Connection connection = getMetas.getConnection();
        GetRepoImpl getRepo = new GetRepoImpl();
        getRepo.deleteFromTable(connection, table, primaryKey, primaryKeyValue);
        return "Usunięto wartość o ID: " + primaryKeyValue;
    }

    @GET
    @Path("{tableId}/updateElement")
    @Produces(MediaType.TEXT_PLAIN)
    public String changeInTable(@PathParam("tableId") String tableId,
                              @QueryParam("primaryKeyValue") String primaryKeyValue,
                              @QueryParam("values") List<String> values) throws SQLException, ClassNotFoundException{
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        String table = getMetas.getConcreteTable(Integer.parseInt(tableId));
        String primaryKey = getMetas.getPrimaryKeyOfTheTable(table);
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        Connection connection = getMetas.getConnection();
        List<String> valuesToInsert = new ArrayList<String>();
        for(String value: values){
            valuesToInsert.add(value);
        }
        GetRepo getRepo = new GetRepoImpl();
        getRepo.changeInTable(connection, table, columns, valuesToInsert, primaryKey, primaryKeyValue);
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(values);
    }
}
