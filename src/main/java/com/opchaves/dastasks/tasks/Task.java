package com.opchaves.dastasks.tasks;

import java.time.LocalDateTime;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "tasks")
public class Task extends PanacheMongoEntity {
  public String title;
  public String description;
  public boolean done;
  public LocalDateTime createdAt;

  public Task() {
  }
}
