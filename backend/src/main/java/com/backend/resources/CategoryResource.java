package com.backend.resources;

import com.backend.entities.Category;
import com.backend.entities.User;
import com.backend.requests.LoginRequest;
import com.backend.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/categories")
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all()
    {
        return this.categoryService.allCategories();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Category find(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        if(name==null)
            return this.categoryService.findCategory(id);
        else
            return this.categoryService.findCategory(name);
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Category update(@PathParam("id") Integer id, @Valid Category category){return this.categoryService.updateCategory(id, category);}

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.categoryService.deleteCategory(id);
    }

}
