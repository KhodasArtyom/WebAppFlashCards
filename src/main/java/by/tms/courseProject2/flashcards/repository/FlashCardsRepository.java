package by.tms.courseProject2.flashcards.repository;

import by.tms.courseProject2.flashcards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardsRepository {

    void save(long flashCards_themes_id, String question, String answer);

    void remove(long flashCardId);

    void statusUpdateLearned(long flashCardId);


    FlashCards  getOneFlashCardNotLearned(long flashCards_themes_id, long nextCard);

    Optional<FlashCards> findFlashCardById(long flashCardId);


    List<FlashCards> findAllCardsByThemeId(long flashcard_themeId);

    boolean isExist(long flashCardId);

    FlashCards getFlashCardById(long flashCardId);


}
