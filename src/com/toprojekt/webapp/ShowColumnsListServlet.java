package com.toprojekt.webapp;

import com.toprojekt.clients.GetDatasFromDataBaseClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowColumnsList", urlPatterns = {"/ShowColumnsList"})
public class ShowColumnsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        GetDatasFromDataBaseClient client = new GetDatasFromDataBaseClient();
        List<String> columns = client.getTableColumnsNames("0");
        PrintWriter o = response.getWriter();
        o.println("<style>");
        o.println("body {background-color: cadetblue;}");
        o.println("#content{background-color: white; width: 60%; height: 100%; margin-left: auto;margin-right: auto; border-style: solid; border-width: 3px;}");
        o.println("#title{background-color: palegreen; width: 100%; height: 50px; padding-top: 10px; padding-bottom: 10px; margin-top: 0px; border-style: solid; border-width: 3px;}");
        o.println("</style>");
        o.println("<div id='content'>");
        o.println("<div id='title'>");
        o.println("<h2 align='center' style='font-weight: bold; font-size: 20px;'>Nazwy kolumn tabeli</h2>");
        o.println("</div>");
        o.println("<div style='margin-left:auto; margin-right: auto; margin-top: 50px;' align='center'>");
        o.println("<ul>");
        for(String column: columns){
            o.println("<li>" + column + "</li>");
        }
        o.println("</ul>");
        o.println("</div>");
        o.println("</div>");
    }
}
