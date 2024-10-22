package com.opchaves.dastasks.blog;

import java.util.List;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PostService {
  private final PostMapper postMapper;

  public PostService(PostMapper postMapper) {
    this.postMapper = postMapper;
  }

  public List<Post> findAll() {
    return Post.listAll();
  }

  private Post findPost(Long id) {
    return Post.<Post>findByIdOptional(id)
        .orElseThrow(() -> new WebApplicationException("Post not found", 404));
  }

  public Post findById(Long id) {
    return this.findPost(id);
  }

  public Post findBySlug(String slug) {
    return Post.<Post>find("slug", slug)
        .firstResultOptional()
        .orElseThrow(() -> new WebApplicationException("Post not found", 404));
  }

  public Post create(Post post) {
    Post newPost = postMapper.toEntity(post);
    Post.persist(newPost);

    Log.info("New post created: " + newPost.id);

    return newPost;
  }

  public Post update(Long id, Post post) {
    Post aPost = this.findPost(id);
    postMapper.partialUpdate(post, aPost);
    aPost.persist();

    return aPost;
  }

  public void delete(Long id) {
    Post aPost = this.findPost(id);
    aPost.delete();
  }
}
