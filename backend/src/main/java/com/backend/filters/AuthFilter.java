package com.backend.filters;

import com.backend.resources.CategoryResource;
import com.backend.resources.UserResource;
import com.backend.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }
            List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
            if (!this.userService.isAuthorized(token, matchedResources)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }
        //else if(req.getUriInfo().getPath().contains("articles"))
        else if (req.getMethod().equals("POST") && req.getUriInfo().getPath().contains("comments"))
        {
            return false;
        }
        else if(req.getMethod().equals("POST") || req.getMethod().equals("DELETE") || req.getMethod().equals("PUT"))
        {
            return true;
        }

        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            //System.out.println( matchedResource.getClass().getName());
            if (matchedResource instanceof UserResource) {
                return true;
            }
        }

        return false;
    }
}
