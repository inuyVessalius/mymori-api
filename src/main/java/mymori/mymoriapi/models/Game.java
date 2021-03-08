package mymori.mymoriapi.models;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "user_id")
    private long userId;
    // TODO relationship with cards

    protected Game() {
    }

    public Game(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("Game[id=%d, user='%s']", id, userId);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
