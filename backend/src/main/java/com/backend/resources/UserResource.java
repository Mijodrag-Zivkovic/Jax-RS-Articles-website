package com.backend.resources;

import com.backend.entities.User;
import com.backend.requests.LoginRequest;
import com.backend.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> all()
    {
        return this.userService.allUsers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {
        return this.userService.addUser(user);
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@QueryParam("id") Integer id, @QueryParam("email") String email) {
        if(email==null)
            return this.userService.findUser(id);
        else
            return this.userService.findUser(email);
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.userService.deleteUser(id);
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User update(@PathParam("id") Integer id, @Valid User user){return this.userService.updateUser(id, user);}

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
}
