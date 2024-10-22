package com.opchaves.dastasks.blog;

import java.net.URI;
import java.util.List;

import io.quarkus.logging.Log;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/posts")
public class PostResource {
  private final PostService postService;

  public PostResource(PostService postService) {
    this.postService = postService;
  }

  @GET
  @Path("/")
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GET
  @Path("/{id}")
  public Post findById(String id) {
    return postService.findById(id);
  }

  @GET
  @Path("/{slug}")
  public Post findBySlug(String slug) {
    return postService.findBySlug(slug);
  }

  @POST
  @Path("/")
  public Response create(@Valid Post post) {
    Post newPost = postService.create(post);

    URI uri = URI.create("/api/posts/" + newPost.id);
    return Response.created(uri).entity(newPost).build();
  }

  @PUT
  @Path("/{id}")
  public Post update(String id, @Valid Post post) {
    return postService.update(id, post);
  }

  @DELETE
  @Path("/{id}")
  public void delete(String id) {
    postService.delete(id);
  }
}
