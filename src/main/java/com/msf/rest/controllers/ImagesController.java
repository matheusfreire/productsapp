package com.msf.rest.controllers;

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

import com.msf.rest.dao.ImageDAO;
import com.msf.rest.models.Image;

public class ImagesController {

	private ImageDAO dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> allImages() {
		return getDao().recoverAll("from Image");
	}

	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewImage(@QueryParam("type") String type) {
		try {
			Image image = new Image();
			image.setType(type);
			getDao().persist(image);
			return Response.status(Status.OK).entity("Image created successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}

	@PUT
	@Path("{id}")
	public Response updateImage(@PathParam("id") int id, @QueryParam("type") String type) {
		try {
			Image i = getDao().find(id);
			i.setType(type);
			return Response.status(Status.OK).entity("Image updated successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteImage(@PathParam("id") int id) {
		try {
			Image i = getDao().find(id);
			getDao().delete(i);
			return Response.status(Status.OK).entity("Image deleted successfully").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Something went wrong").build();
		}
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image recoverImage(@PathParam("id") int id) {
		try {
			return getDao().find(id);
		} catch (Exception e) {
			return null;
		}
	}

	public ImageDAO getDao() {
		if (dao == null) {
			dao = new ImageDAO();
		}
		return dao;
	}

}