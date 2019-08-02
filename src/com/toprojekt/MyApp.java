package com.toprojekt;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.toprojekt.services.DataBaseStructureRestServices;
import com.toprojekt.services.MetasRestServices;
import com.toprojekt.webapp.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApp extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(MetasRestServices.class );
        h.add(DataBaseStructureRestServices.class);
        h.add(AddRecordToTableServlet.class);
        h.add(DeleteRecordFromTableServlet.class);
        h.add(ShowColumnsListServlet.class);
        h.add(ShowRecordByIdServlet.class);
        h.add(ShowTableContentServlet.class);
        h.add(ShowTablesListServlet.class);
        h.add(UpdateRecordInTableServlet.class);
        h.add(JacksonFeatures.class);
        return h;
    }
}
