CREATE TABLE flashсard_theme
(
    id       BIGSERIAL PRIMARY KEY,
    set_name TEXT NOT NULL
);

CREATE TABLE flashсard
(
    id                   BIGSERIAL PRIMARY KEY,
    flashCards_themes_id BIGINT  NOT NULL REFERENCES flashсard_theme ON DELETE CASCADE,
    question             TEXT    NOT NULL,
    answer               TEXT    NOT NULL,
    status_knowledge     BOOLEAN NOT NULL
);