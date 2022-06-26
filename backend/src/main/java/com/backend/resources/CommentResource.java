package com.backend.resources;

import com.backend.entities.Article;
import com.backend.entities.Comment;
import com.backend.entities.Tag;
import com.backend.entities.User;
import com.backend.services.CommentService;
import com.backend.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> all()
    {
        return this.commentService.allComments();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

    @GET
    @Path("/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment find(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        if(name==null)
            return this.commentService.findComment(id);
        else
            return this.commentService.findComment(name);
    }

    @GET
    @Path("/articleId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> commentsByarticleId(@QueryParam("id") Integer id){
        return this.commentService.commentsByArticleId(id);
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Comment update(@PathParam("id") Integer id, @Valid Comment comment){return this.commentService.updateComment(id, comment);}

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.commentService.deleteComment(id);
    }

}
