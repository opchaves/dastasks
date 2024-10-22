package com.opchaves.dastasks.blog;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
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
  public Post findById(Long id) {
    return postService.findById(id);
  }

  @POST
  @Path("/")
  @Transactional
  public Response create(@Valid Post post) {
    Post newPost = postService.create(post);

    URI uri = URI.create("/api/posts/" + newPost.id);
    return Response.created(uri).entity(newPost).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Post update(Long id, @Valid Post post) {
    return postService.update(id, post);
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void delete(Long id) {
    postService.delete(id);
  }
}
