-- Добавление в таблицы
INSERT INTO flashcard(flashCards_themes_id, question, answer, status_knowledge)
VALUES (?,?,?,?);

INSERT INTO flashcards_theme( set_name)
VALUES ('COLORS');

--Удаление строки из таблицы flashcards-themes
DELETE
FROM flashCards_theme
WHERE id = ?;

--Удаление строки из таблицы flashcards
DELETE
FROM flashcard
WHERE id = ?;

-- Список набора карточек flashcards_themes
SELECT id       AS id,
       set_name AS name
FROM flashCards_theme;

-- Список набора карточек flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcard;

--Редактирование списка карточки title
SELECT id       AS id,
       set_name as name
FROM flashCards_theme
WHERE id = ?;

--Редактироване списка карточки flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcard
WHERE flashCards_themes_id = ?;

--Список карточек


SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcard
WHERE flashcards_themes_id = ?
  AND NOT flashcard.status_knowledge
ORDER BY flashcard.id
LIMIT 1 OFFSET ?;

--Расчет количество изученных карточек из всего списка карточек

SELECT flashCards_theme.id                                              AS id,
       flashCards_theme.set_name                                        AS name,
       count(flashcard.id) FILTER ( WHERE flashcard.status_knowledge ) AS succsess,
       count(flashcard.id)                                              AS global
FROM flashCards_theme
            LEFT JOIN flashcard ON flashCards_theme.id = flashcard.flashCards_themes_id
GROUP BY flashCards_theme.id;


--изменение колонки status_knowledge при успешном выполнении
UPDATE flashcard
SET status_knowledge = true
WHERE flashcard.id = 1;





