package com.intracom.tuto;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/main")
public class SampleResource {
    @Resource(name="hsqlDs", lookup="java:jboss/datasources/ExampleDS")
    DataSource dataSource;

    @GET
    @Path("/elements")
    @Produces(MediaType.APPLICATION_JSON)
    public String getElements() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootObj = mapper.createObjectNode();
        ArrayNode elementsArray = rootObj.putArray("elements");
        elementsArray.toString();
        return rootObj.toString();
    }
}
