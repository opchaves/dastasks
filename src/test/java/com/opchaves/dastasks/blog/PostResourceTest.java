package com.opchaves.dastasks.blog;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.opchaves.dastasks.blog.client.PostClient;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
class PostResourceTest {

  // TODO: unit tests. create examples to mock at the bottom
  // follow rest-fights example for unit and IT tests

  @InjectMock
  PostService postService;

  @BeforeAll
  static void beforeAll() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void testFindAllEndpoint() {
    given()
        .when().get("/api/posts")
        .then()
        .statusCode(200)
        .body(is("[]"));
  }

  @Test
  void testCreateEndpoint() {
    Post post = createValidPost();
    PostClient postBody = new PostClient("Post title", "post-title", "Post content", "Author");

    when(postService.create(any(Post.class))).thenReturn(post);

    given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body(postBody)
        .when().post("/api/posts").then()
        .log().all(true)
        .statusCode(Status.CREATED.getStatusCode())
        .contentType(ContentType.JSON)
        .body("id", is(post.id.toHexString()))
        .body("title", is(post.title));
  }

  @Test
  void testCreateShouldFailWhenMissingTitle() {
    PostClient postBody = new PostClient(null, "post-title", "Post content", "Author");

    given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body(postBody)
        .when().post("/api/posts").then()
        .log().all(true)
        .statusCode(Status.BAD_REQUEST.getStatusCode());
  }

  private static Post createValidPost() {
    Post post = new Post();
    post.id = new ObjectId();
    post.title = "Post title";
    post.slug = "post-title";
    post.content = "Post content";
    post.author = "Author";
    post.excerpt = "Post excerpt";
    return post;
  }
}
