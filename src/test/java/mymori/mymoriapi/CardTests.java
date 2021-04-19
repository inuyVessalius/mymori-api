package mymori.mymoriapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import mymori.mymoriapi.models.Card;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardTests {
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testCardCreation() {
    Card card = new Card();
    card.setQuestion("Question");
    card.setAnswer("Answer");

    Assertions.assertEquals("Question", card.getQuestion());
    Assertions.assertEquals("Answer", card.getAnswer());
    Assertions.assertEquals("Card[id=0, question='Question', answer='Answer']", card.toString());
  }

  @Test
  public void testCardCreationFromOtherCard() {
    Card card = new Card();
    card.setQuestion("Question");
    card.setAnswer("Answer");

    Card newCard = new Card(card);

    Assertions.assertEquals("Question", newCard.getQuestion());
    Assertions.assertEquals("Answer", newCard.getAnswer());
  }

  @Test
  public void testSimpleGetCardSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card/1";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGetCardFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card/0";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGetsCardSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/cards";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGetsCardFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(405, result.getStatusCodeValue());
  }

  @Test
  public void testSimpleGetWithQuestionCardFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testAddCardsSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/cards/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setQuestion("Question1");
    card.setAnswer("Answer1");
    Card card1 = new Card();
    card1.setQuestion("Question2");
    card1.setAnswer("Answer2");

    List<Card> list = new ArrayList<>();

    list.add(card);
    list.add(card1);

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(list), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testAddCardSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setQuestion("Question");
    card.setAnswer("Answer");

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(card), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testAddCardFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setQuestion(null);
    card.setAnswer(null);

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(card), String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteCardsSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/cards/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setId(2l);
    card.setQuestion("Question1");
    card.setAnswer("Answer1");
    Card card1 = new Card();
    card.setId(3l);
    card1.setQuestion("Question2");
    card1.setAnswer("Answer2");

    List<Card> list = new ArrayList<>();

    list.add(card);
    list.add(card1);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(400, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteCardSuccess() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/Question";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testDeleteCardFailed() throws URISyntaxException {
    final String baseUrl = "http://localhost:" + randomServerPort + "/card/0";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testGetCardSuccess() throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/card/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setQuestion("Question");
    card.setAnswer("Answer");

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(card), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());

    baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/Question";
    uri = new URI(baseUrl);

    result = this.restTemplate.getForEntity(uri, String.class);

    // Verify request succeed
    Assertions.assertEquals(200, result.getStatusCodeValue());
    baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/Question";
    uri = new URI(baseUrl);

    result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  public void testGetCardFailed() throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/card/";
    URI uri = new URI(baseUrl);
    Card card = new Card();
    card.setQuestion("null");
    card.setAnswer("null");

    ResponseEntity<String> result = restTemplate
        .exchange(RequestEntity.put(uri).header("X-COM-PERSIST", "true").body(card), String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());

    baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/";
    uri = new URI(baseUrl);

    result = this.restTemplate.getForEntity(uri, String.class);

    // Verify request succeed
    Assertions.assertEquals(404, result.getStatusCodeValue());
    baseUrl = "http://localhost:" + randomServerPort + "/cardWithQuestion/";
    uri = new URI(baseUrl);

    result = restTemplate.exchange(RequestEntity.delete(uri).build(), String.class);

    Assertions.assertEquals(404, result.getStatusCodeValue());
  }
}