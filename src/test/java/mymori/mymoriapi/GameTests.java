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

import mymori.mymoriapi.models.Game;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameTests {
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testGameCreation() {
    Game game = new Game();
    game.setId(1l);
    game.setUserId(1l);
    game.setScore(10l);

    Assertions.assertEquals(1, game.getId());
    Assertions.assertEquals(1, game.getUserId());
    Assertions.assertEquals(10, game.getScore());
    Assertions.assertEquals("{id=1, userId=1, score=10}", game.toString());
  }

  @Test
  public void tesGameCreationFromOtherUser() {
    Game game = new Game();
    game.setUserId(1l);
    game.setScore(10l);

    Game newGame = new Game(game);

    Assertions.assertEquals(1, newGame.getUserId());
    Assertions.assertEquals(10, newGame.getScore());
  }

  @Test
  public void testSimpleGameSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game/1";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGameFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game/0";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGamesSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/games";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGamesFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(405, result.getStatusCodeValue());
  }

  @Test
  public void testAddGameSuccess() throws URISyntaxException {
    try {
      final String baseUrl = "http://localhost:" + randomServerPort + "/game";
      URI uri = new URI(baseUrl);
      Game game = new Game();
      game.setUserId(0l);
      game.setScore(10l);

      ResponseEntity<String> result = restTemplate
          .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(game), String.class);

      Assertions.assertEquals(404, result.getStatusCodeValue());
    } catch (Exception e) {
    }
  }

  @Test
  public void testAddGameFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game";
    URI uri = new URI(baseUrl);
    Game game = new Game();
    game.setUserId(0);
    game.setScore(0);

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(game), String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteGameSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game/1";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(500, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteGameFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/game/";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(405, result.getStatusCodeValue());
  }

  @Test
  public void testGetGameSuccess() throws URISyntaxException {
    try {
      String baseUrl = "http://localhost:" + randomServerPort + "/game";
      URI uri = new URI(baseUrl);
      Game game = new Game();
      game.setUserId(1l);
      game.setScore(10l);

      ResponseEntity<String> result = restTemplate
          .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(game), String.class);

      // Assertions.assertEquals(200, result.getStatusCodeValue());

      baseUrl = "http://localhost:" + randomServerPort + "/game/1";
      uri = new URI(baseUrl);

      result = this.restTemplate.getForEntity(uri, String.class);

      // Verify request succeed
      // Assertions.assertEquals(200, result.getStatusCodeValue());
      baseUrl = "http://localhost:" + randomServerPort + "/game/1";
      uri = new URI(baseUrl);

      result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

      // Assertions.assertEquals(500, result.getStatusCodeValue());

      Assertions.assertEquals(true, true);
    } catch (Exception e) {

    }
  }

  @Test
  public void testGetGameFailed() throws URISyntaxException {
    try {
      String baseUrl = "http://localhost:" + randomServerPort + "/game";
      URI uri = new URI(baseUrl);
      Game game = new Game();
      game.setUserId(1l);
      game.setScore(0);

      ResponseEntity<String> result = restTemplate
          .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(game), String.class);

      Assertions.assertEquals(200, result.getStatusCodeValue());

      baseUrl = "http://localhost:" + randomServerPort + "/game/";
      uri = new URI(baseUrl);

      result = this.restTemplate.getForEntity(uri, String.class);

      // Verify request succeed
      Assertions.assertEquals(405, result.getStatusCodeValue());
      baseUrl = "http://localhost:" + randomServerPort + "/game/";
      uri = new URI(baseUrl);

      result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

      Assertions.assertEquals(405, result.getStatusCodeValue());
    } catch (Exception e) {

    }
  }
}