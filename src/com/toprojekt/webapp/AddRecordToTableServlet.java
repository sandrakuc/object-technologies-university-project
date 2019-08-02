package com.toprojekt.webapp;

import com.toprojekt.clients.GetDatasFromDataBaseClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToTableResult", urlPatterns = {"/AddToTableResult"})
public class AddRecordToTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        GetDatasFromDataBaseClient client = new GetDatasFromDataBaseClient();
        List<String> values = new ArrayList<String>();
        values.add("333");
        values.add("'example321'");
        List<String> columns = client.getTableColumnsNames("0");
        List<String> record = client.addValuesToTable("0", values);
        PrintWriter o = response.getWriter();
        o.println("<style>");
        o.println("body {background-color: cadetblue;}");
        o.println("table, th, td {border: 1px solid black;}");
        o.println("#content{background-color: white; width: 60%; height: 100%; margin-left: auto;margin-right: auto; border-style: solid; border-width: 3px;}");
        o.println("#title{background-color: palegreen; width: 100%; height: 50px; padding-top: 10px; padding-bottom: 10px; margin-top: 0px; border-style: solid; border-width: 3px;}");
        o.println("</style>");
        o.println("<div id='content'>");
        o.println("<div id='title'>");
        o.println("<h2 align='center' style='font-weight: bold; font-size: 20px;'>Dodano rekord: </h2>");
        o.println("</div>");
        o.println("<div style='margin-left:auto; margin-right: auto; margin-top: 50px;' align='center'>");
        o.println("<table>");
        o.println("<thead>");
        o.println("<tr>");
        for(String column: columns){
            o.println("<th>" + column + "</th>");
        }
        o.println("</tr>");
        o.println("</thead>");
        o.println("<tbody>");
        o.println("<tr>");
        for(String cell : record){
            o.println("<td>"+cell + "</td>");
        }
        o.println("</tr>");
        o.println("</tbody>");
        o.println("</table>");
        o.println("</div>");
        o.println("</div>");
    }
}
