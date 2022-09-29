package com.envixo.challenge.api;

import com.envixo.challenge.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path(value = "/v1/api/product")
@Consumes(MediaType.APPLICATION_JSON)
public class ProductAPI {

    @Inject
    ProductService productService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(String body) {
        Gson gson = new Gson();
        JsonObject productResponse;
        try {
            productResponse = productService.addOrUpdateProduct(body);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok(gson.toJson(productResponse)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Gson gson = new Gson();
        JsonObject productResponse;
        try {
            productResponse = productService.findById(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok(gson.toJson(productResponse)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        Gson gson = new Gson();
        JsonObject productResponse;
        try {
            productResponse = productService.findAll();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok(gson.toJson(productResponse)).build();
    }
}
