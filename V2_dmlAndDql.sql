-- Добавление в таблицы
INSERT INTO flashcards(flashCards_themes_id, question, answer, status_knowledge)
VALUES (?,?,?,?);

INSERT INTO flashcards_themes( set_name)
VALUES (?);

--Удаление строки из таблицы flashcards-themes
DELETE
FROM flashCards_themes
WHERE id = ?;

--Удаление строки из таблицы flashcards
DELETE
FROM flashcards
WHERE id = ?;

-- Список набора карточек flashcards_themes
SELECT id       AS id,
       set_name AS name
FROM flashCards_themes;

-- Список набора карточек flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards;

--Редактирование списка карточки title
SELECT id       AS id,
       set_name as name
FROM flashCards_themes
WHERE id = ?;

--Редактироване списка карточки flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards
WHERE flashCards_themes_id = ?;

--Список карточек


SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards
WHERE flashcards_themes_id = ?
  AND NOT flashcards.status_knowledge
ORDER BY flashcards.id
LIMIT 1 OFFSET ?;

--Расчет количество изученных карточек из всего списка карточек

SELECT flashCards_themes.id                                              AS id,
       flashCards_themes.set_name                                        AS name,
       count(flashcards.id) FILTER ( WHERE flashcards.status_knowledge ) AS succsess,
       count(flashcards.id)                                              AS global
FROM flashCards_themes
            LEFT JOIN flashcards ON flashCards_themes.id = flashcards.flashCards_themes_id
GROUP BY flashCards_themes.id;


--изменение колонки status_knowledge при успешном выполнении
UPDATE flashcards
SET status_knowledge = true
WHERE flashcards.id = 1;





