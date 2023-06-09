-- Добавление в таблицы
INSERT INTO flashcard(flashCards_themes_id, question, answer, status_knowledge)
VALUES (1,'BLACK','Чёрный',false);

INSERT INTO flashcard_theme( set_name)
VALUES ('COLORS');

--Удаление строки из таблицы flashcards-themes
DELETE
FROM flashCard_theme
WHERE id = ?;

--Удаление строки из таблицы flashcards
DELETE
FROM flashcard
WHERE id = ?;

-- Список набора карточек flashcards_themes
SELECT id       AS id,
       set_name AS name
FROM flashCard_theme;

-- Список набора карточек flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcard;

--Редактирование списка карточки title
SELECT id       AS id,
       set_name as name
FROM flashCard_theme
WHERE id = ?;

--Редактироване списка карточки flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcard
WHERE flashCards_themes_id = ?;

--Существует ли карточка flashCard
SELECT  TRUE
FROM flashcard
WHERE id = ?;

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

SELECT flashCard_theme.id                                              AS id,
       flashCard_theme.set_name                                        AS name,
       count(flashcard.id) FILTER ( WHERE flashcard.status_knowledge ) AS succsess,
       count(flashcard.id)                                              AS global
FROM flashCard_theme
            LEFT JOIN flashcard ON flashCard_theme.id = flashcard.flashCards_themes_id
GROUP BY flashCard_theme.id;


--изменение колонки status_knowledge при успешном выполнении
UPDATE flashcard
SET status_knowledge = true
WHERE flashcard.id = 1;





