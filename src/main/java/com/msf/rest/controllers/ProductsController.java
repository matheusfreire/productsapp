package com.msf.rest.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.msf.rest.models.Product;

@Path("products")
public class ProductsController {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
		Product p = new Product();
		p.setName("Teste");
		return Response.status(Status.OK).entity(p).build();
    }

}
