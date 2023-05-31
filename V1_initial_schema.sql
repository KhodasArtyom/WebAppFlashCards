CREATE TABLE flashCards_theme
(
    id       BIGSERIAL PRIMARY KEY,
    set_name TEXT NOT NULL
);

CREATE TABLE flashCard
(
    id                   BIGSERIAL PRIMARY KEY,
    flashCards_themes_id BIGINT  NOT NULL REFERENCES flashCards_theme ON DELETE CASCADE,
    question             TEXT    NOT NULL,
    answer               TEXT    NOT NULL,
    status_knowledge     BOOLEAN NOT NULL
);