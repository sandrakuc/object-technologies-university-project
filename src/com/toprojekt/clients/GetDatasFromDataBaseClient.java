package com.toprojekt.clients;

import com.toprojekt.gets.OneBigStringToListOfStringsConverter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class GetDatasFromDataBaseClient {

    public List<String> getAllValuesFromTable(String tableId){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId);
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }

    public List<String> getAllVsluesFromTableByPrimaryKeyValue(String tableId, String primaryKeyValue){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId).path("findElement").queryParam("primaryKeyValue", primaryKeyValue);
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }

    public List<String> addValuesToTable(String tableId, List<String> values){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("addElement");
        for(String value: values){
            webTarget = webTarget.queryParam("values", value);
        }
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }

    public List<String> updateToTable(String tableId, String primaryKeyValue, List<String> values){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("updateElement").queryParam("primaryKeyValue", primaryKeyValue);
        for(String value: values){
            webTarget = webTarget.queryParam("values", value);
        }
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }

    public String deleteFromTable(String tableId, String primaryKeyValue){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("deleteElement").queryParam("primaryKeyValue", primaryKeyValue);
        String s = webTarget.request().get(String.class);
        return s;
    }

    public List<String> getDatabaseTables(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/structure");
        Response response = webTarget.request().get();
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }

    public List<String> getTableColumnsNames(String tableId){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/structure").path(tableId);
        String s = webTarget.request().get(String.class);
        return OneBigStringToListOfStringsConverter.INSTANCE.convert(s);
    }
}
