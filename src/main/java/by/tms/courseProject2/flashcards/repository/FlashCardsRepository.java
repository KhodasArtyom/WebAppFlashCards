package by.tms.courseProject2.flashcards.repository;

import by.tms.courseProject2.flashcards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardsRepository {

    void save(long flashCards_themes_id, String question, String answer,boolean isLearned);

    void remove(long flashCardId);

    boolean statusUpdateLearned(long flashCardId, boolean isLearned);


    Optional<FlashCards> getOneFlashCard(long flashCards_themes_id, long nextCard);

    List<FlashCards> findAllCardsByThemeId(long flashcard_themeId);

    boolean isExist(long flashCardId);

    FlashCards getFlashCardById(long flashCardId);
}
