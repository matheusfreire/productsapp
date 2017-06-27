package com.msf.rest.controllers;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.msf.rest.dao.ProductsDAO;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

@Path("products")
public class ProductsController {
	
	private ProductsDAO dao;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> allProducts() {
		return getDao().recoverAll("from Product");
    }
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewProduct(@FormParam("name") String name, @FormParam("description") String description){
		try{
			Product p = new Product();
			p.setDescription(description);
			p.setName(name);
			getDao().persist(p);
			return Response.status(Status.OK).entity("Product created successfully").build();			
		} catch (Exception e){
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}
	
	@PUT
	@Path("{id}")
	public Response updateProduct(@PathParam("id")int id,@QueryParam ("name") String name, @QueryParam ("description") String description){
		try{
			Product p = getDao().find(id);
			p.setDescription(description);
			p.setName(name);
			return Response.status(Status.OK).entity("Product updated successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}

	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id") int id){
		try{
			Product p = getDao().find(id);
			getDao().delete(p);
			return Response.status(Status.OK).entity("Product deleted successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product recoverProduct(@PathParam("id") int id){
		try{
			return getDao().find(id);
		} catch (Exception e) {
			return null;	
		}
	}
	
	@GET
	@Path("{id}/child")
	@Produces(MediaType.APPLICATION_JSON)
	public Product recoverChildrenProducts(@PathParam("id") int id){
		Product p = new Product();
		p.setName("Teste");
		return p;
	}
	
	@GET
	@Path("{id}/images")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> recoverChildrenImages(@PathParam("id") int id){
		return getDao().getImageByProduct(getDao().find(id));
	}

	public ProductsDAO getDao() {
		if(dao == null){
			dao = new ProductsDAO();
		}
		return dao;
	}
}