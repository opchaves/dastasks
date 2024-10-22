package com.opchaves.dastasks.blog;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "posts")
public class Post extends PanacheEntity {
  @NotBlank
  @Column(nullable = false)
  public String title;

  @NotBlank
  @Column(unique = true, nullable = false)
  public String slug;

  public String excerpt;

  @NotBlank
  @Column(columnDefinition = "TEXT", nullable = false)
  public String content;

  @Column(name = "image_url")
  public String imageUrl;

  @NotBlank
  @Column(nullable = false)
  public String author;

  public boolean draft;

  @Column(name = "published_at")
  public LocalDateTime publishedAt;

  @CreationTimestamp
  @Column(name = "created_at")
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  public LocalDateTime updatedAt;

  public Post() {
  }
}
