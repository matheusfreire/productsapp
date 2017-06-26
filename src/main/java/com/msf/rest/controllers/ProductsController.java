package com.msf.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.msf.rest.dao.ProductsDAO;
import com.msf.rest.models.Product;

@Path("products")
public class ProductsController {
	
	private ProductsDAO dao;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> allProducts() {
		Product p = new Product();
		p.setName("Teste");
		List<Product> products = new ArrayList<>();
		products.add(p);
		return products;
    }
	
	@POST
	@Path("create")
	public Response createNewProduct(@FormParam("name") String name, @FormParam("description") String description){
		Product p = new Product();
		p.setDescription(description);
		p.setName(name);
		getDao().persist(p);
		String output = "Product create successfully";
		return Response.status(200).entity(output).build();
	}
	
	@PUT
	@Path("{id}")
	public Product updateProduct(@PathParam("id")long id){
		
		return new Product();
	}

	
	@DELETE
	@Path("{id}")
	public Response deleteProduct(@PathParam("id") long id){
		return Response.status(200).entity(id).build();
	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product recoverProduct(@PathParam("id") long id){
		Product p = new Product();
		p.setName("Teste");
		return p;
	}
	
	@GET
	@Path("{id}/products")
	@Produces(MediaType.APPLICATION_JSON)
	public Product recoverChildrenProducts(@PathParam("id") long id){
		Product p = new Product();
		p.setName("Teste");
		return p;
	}
	
	@GET
	@Path("{id}/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Product recoverChildrenImages(@PathParam("id") long id){
		Product p = new Product();
		p.setName("Teste");
		return p;
	}

	public ProductsDAO getDao() {
		if(dao == null){
			dao = new ProductsDAO();
		}
		return dao;
	}

	public void setDao(ProductsDAO dao) {
		this.dao = dao;
	}
}