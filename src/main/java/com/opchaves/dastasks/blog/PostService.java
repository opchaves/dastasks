package com.opchaves.dastasks.blog;

import java.util.List;

import org.bson.types.ObjectId;

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

  private Post findPost(String id) {
    return Post.<Post>findByIdOptional(new ObjectId(id))
        .orElseThrow(() -> new WebApplicationException("Post not found", 404));
  }

  public Post findById(String id) {
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

  public Post update(String id, Post post) {
    Post aPost = this.findPost(id);
    postMapper.partialUpdate(post, aPost);
    aPost.persist();

    return aPost;
  }

  public void delete(String id) {
    Post aPost = this.findPost(id);
    aPost.delete();
  }
}
