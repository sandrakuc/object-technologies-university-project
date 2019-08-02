package com.toprojekt.tests;

import com.toprojekt.services.MetasRestServices;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RestIT extends JerseyTest {
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(MetasRestServices.class);
    }

    @Test
    public void shouldGetFromTable() {
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId);
        String s = webTarget.request().get(String.class);
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(s.contains("1"));
        Assert.assertTrue(s.contains("Chleb pszenny"));
    }

    @Test
    public void shouldGetFromTableById() {
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        String primaryKeyValue = "1";
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId).path("findElement").queryParam("primaryKeyValue", primaryKeyValue);
        String s = webTarget.request().get(String.class);
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(s.contains("1"));
        Assert.assertTrue(s.contains("Chleb pszenny"));
    }

    @Test
    public void shouldAddToTableById(){
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        List<String> values = new ArrayList<String>();
        values.add("101");
        values.add("'example1'");
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("addElement").queryParam("values", values.get(0)).queryParam("values", values.get(1));
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        WebTarget webTarget2 = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId);
        String s = webTarget2.request().get(String.class);
        Assert.assertTrue(s.contains("101"));
        Assert.assertTrue(s.contains("example1"));
    }

    @Test
    public void shouldUpdateInTableById(){
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        List<String> values = new ArrayList<String>();
        values.add("101");
        values.add("'example2'");
        String primaryKeyValue = "101";
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("updateElement").queryParam("primaryKeyValue", primaryKeyValue).queryParam("values", values.get(0)).queryParam("values", values.get(1));
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        WebTarget webTarget2 = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId);
        String s = webTarget2.request().get(String.class);
        Assert.assertTrue(s.contains("101"));
        Assert.assertTrue(s.contains("example2"));
    }

    @Test
    public void shouldDeleteFromTableById(){
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        String primaryKeyValue = "101";
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas/").path(tableId).path("deleteElement").queryParam("primaryKeyValue", primaryKeyValue);
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        WebTarget webTarget2 = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/metas").path(tableId);
        String s = webTarget2.request().get(String.class);
        Assert.assertFalse(s.contains("101"));
        Assert.assertFalse(s.contains("example2"));
        Assert.assertFalse(s.contains("example1"));
    }

    @Test
    public void shouldGetDatabaseTables(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/structure");
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        String s = webTarget.request().get(String.class);
        Assert.assertTrue(s.contains("pieczywo"));
    }

    @Test
    public void shouldGetDatabaseColumns(){
        Client client = ClientBuilder.newClient();
        String tableId = "0";
        WebTarget webTarget = client.target("http://localhost:8080/opt-1.0-SNAPSHOT/structure").path(tableId);
        Response response = webTarget.request().get();
        Assert.assertEquals(200, response.getStatus());
        String s = webTarget.request().get(String.class);
        Assert.assertTrue(s.contains("id_pieczywa"));
        Assert.assertTrue(s.contains("nazwa"));
    }


}
