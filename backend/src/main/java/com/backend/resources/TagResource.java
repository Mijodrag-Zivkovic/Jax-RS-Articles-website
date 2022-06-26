package com.backend.resources;

import com.backend.entities.*;
import com.backend.services.ArticleService;
import com.backend.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tags")
public class TagResource {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> all()
    {
        return this.tagService.allTags();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) {
        return this.tagService.addTag(tag);
    }

    @GET
    @Path("/tag")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        if(name==null)
            return this.tagService.findTag(id);
        else
            return this.tagService.findTag(name);
    }

    @GET
    @Path("/articleId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> tagsByArticleId(@QueryParam("id") Integer id){
        return this.tagService.tagsByArticleId(id);
    }

    @DELETE
    @Path("/articleId")
    public void deleteByArticleId(@QueryParam("articleId") Integer articleId,@QueryParam("tagId") Integer tagId) {
        this.tagService.deleteByArticleId(articleId,tagId);
    }

    @POST
    @Path("/articleId")
    @Produces(MediaType.APPLICATION_JSON)
    public Articles_Tags articleTag(@Valid Articles_Tags articleTag){
        return this.tagService.articleTag(articleTag);
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Tag update(@PathParam("id") Integer id, @Valid Tag tag) {
        return this.tagService.updateTag(id,tag);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.tagService.deleteTag(id);
    }
}
