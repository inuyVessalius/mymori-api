CREATE SCHEMA mymori;

CREATE TABLE IF NOT EXISTS mymori.users
(
    id         uuid NOT NULL,
    PRIMARY KEY (id),
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS mymori.games
(
    id         uuid NOT NULL,
    PRIMARY KEY (id),
    user_id uuid NOT NULL,

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES mymori.users(id)
);

CREATE TABLE IF NOT EXISTS mymori.cards
(
    id         uuid NOT NULL,
    PRIMARY KEY (id),
    question TEXT NOT NULL,
    answer  TEXT NOT NULL
);