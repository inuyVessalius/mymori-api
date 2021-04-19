package mymori.mymoriapi.models;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;

    public Card() {
    }

    public Card(Card card) {
        this.id = card.id;
        this.question = card.question;
        this.answer = card.answer;
    }

    @Override
    public String toString() {
        return String.format("Card[id=%d, question='%s', answer='%s']", id, question, answer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
