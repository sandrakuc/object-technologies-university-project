package com.toprojekt.services;

import com.toprojekt.gets.GetMetas;
import com.toprojekt.gets.ListOfStringsToStringToTokenizeConverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/structure")
public class DataBaseStructureRestServices {
    @GET
    @Produces("text/plain")
    public String getTables() throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        List<String> tables = getMetas.getAllTables();
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(tables);
    }

    @GET
    @Path("{tableId}")
    @Produces("text/plain")
    public String getTables(@PathParam("tableId") String tableId) throws SQLException, ClassNotFoundException {
        GetMetas getMetas = new GetMetas();
        getMetas.setConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/pojedzone", "root", "");
        String table = getMetas.getConcreteTable(Integer.parseInt(tableId));
        List<String> columns = getMetas.getAllColumsOfTheTable(table);
        return ListOfStringsToStringToTokenizeConverter.INSTANCE.convert(columns);
    }
}
