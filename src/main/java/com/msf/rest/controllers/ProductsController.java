package com.msf.rest.controllers;

import static com.msf.rest.util.TagUtil.*;

import java.util.List;

import javax.ws.rs.DELETE;
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
    public Response allProducts() {
		try{
			StringBuilder s = new StringBuilder(OPEN_BRACKETS);
			List<Product> products = getDao().recoverAll("from Product where parent_product_id is null");
			for(int i = 0; i<products.size();i++){
				s.append(getDao().findComplete(new Integer(products.get(i).getId())).toJsonBasic());
				if(i != products.size() -1){
					s.append(COMMA);
				}
			}
			return Response.status(Status.OK).entity(s.append(CLOSE_BRACKETS).toString()).build();			
		} catch (Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
    }
	
	@GET
	@Path("detailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allProductsDetailed() {
		try{
			StringBuilder s = new StringBuilder(OPEN_BRACKETS);
			List<Product> products = getDao().recoverAll("from Product where parent_product_id is null");
			for(int i = 0; i<products.size();i++){
				s.append(getDao().findComplete(new Integer(products.get(i).getId())).toJson());
				if(i != products.size() -1){
					s.append(COMMA);
				}
			}
			return Response.status(Status.OK).entity(s.append(CLOSE_BRACKETS).toString()).build();			
		} catch (Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
    }
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewProduct(@QueryParam("name") String name, @QueryParam("description") String description){
		try{
			Product p = new Product();
			p.setDescription(description);
			p.setName(name);
			getDao().persist(p);
			return Response.status(Status.OK).entity("Product created successfully").build();			
		} catch (Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}
	
	@POST
	@Path("{id}/newChild")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewChildProduct(@PathParam("id")int id,@QueryParam("name") String name, @QueryParam("description") String description){
		try{
			Product p = new Product();
			p.setDescription(description);
			p.setName(name);
			p.setParentProduct(getDao().find(id));
			getDao().persist(p);
			return Response.status(Status.OK).entity("Product created successfully").build();			
		} catch (Exception e){
			e.printStackTrace();
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
			getDao().update(p, id);
			return Response.status(Status.OK).entity("Product updated successfully").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}

	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id") int id){
		try{
			getDao().delete(getDao().findComplete(id));
			return Response.status(Status.OK).entity("Product deleted successfully").build();
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
			return null;	
		}
	}
	
	@GET
	@Path("{id}/detailed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recoverProductComplete(@PathParam("id") int id){
		try{
			return Response.ok(getDao().findComplete(id).toJson()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}
	
	@POST
	@Path("{id}/newImage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewImage(@PathParam("id") int id,@QueryParam("type") String type) {
		try {
			Image image = new Image();
			image.setType(type);
			image.setProduct(getDao().find(id));
			getDao().persistImage(image);
			return Response.status(Status.OK).entity("Image created successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}
	
	
	@GET
	@Path("{id}/products")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> recoverChildrenProducts(@PathParam("id") int id){
		try{
			Product p = getDao().find(id);
			return getDao().recoverAllChildProducts(p);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
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