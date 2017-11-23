package com.intracom.tuto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/main")
public class SampleResource {
    @GET
    @Path("/elements")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGreedings() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootObj = mapper.createObjectNode();
        ArrayNode elementsArray = rootObj.addArray();
        return rootObj.toString();
    }
}
