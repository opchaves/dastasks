package com.opchaves.dastasks.blog;

import java.time.LocalDateTime;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;

@MongoEntity(collection = "posts")
public class Post extends PanacheMongoEntity {
  @NotBlank
  public String title;

  @NotBlank
  public String slug;

  public String excerpt;

  @NotBlank
  public String content;

  public String imageUrl;

  @NotBlank
  public String author;

  public boolean draft;

  public LocalDateTime publishedAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Post() {
  }
}
