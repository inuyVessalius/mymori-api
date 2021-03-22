package mymori.mymoriapi;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import mymori.mymoriapi.models.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTests {
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testAddUserSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/user/";
    URI uri = new URI(baseUrl);
    User user = new User("Adam", "Gilly");

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(user), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteUserSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/userWithName/Adam";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testGetUserSuccess() throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user/";
    URI uri = new URI(baseUrl);
    User user = new User("Adam", "Gilly");

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(user), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());

    baseUrl = "http://localhost:" + randomServerPort + "/userWithName/Adam";
    uri = new URI(baseUrl);

    result = this.restTemplate.getForEntity(uri, String.class);

    // Verify request succeed
    Assertions.assertEquals(200, result.getStatusCodeValue());
    baseUrl = "http://localhost:" + randomServerPort + "/userWithName/Adam";
    uri = new URI(baseUrl);

    result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }
}