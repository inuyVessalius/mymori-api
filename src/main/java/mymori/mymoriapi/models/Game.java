package mymori.mymoriapi.models;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "score")
    private long score;

    protected Game() {
    }

    public Game(Game game) {
        this.id = game.getId();
        this.userId = game.getUserId();
        this.score = game.getScore();
    }

    @Override
    public String toString() {
        return String.format("{id=%s, userId='%s', score=%s}", id, userId, score);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
