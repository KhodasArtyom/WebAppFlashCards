package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardsRepository {

void save(long flashCards_themes_id,String question,String answer,boolean isLearned);

void remove (long flashCardId);

void statusUpdateLearned(long flashCardId,boolean isLearned);


Optional<FlashCards> findAllFlashCardsByIdAndOffset(long flashCards_themes_id, long offset);

List<FlashCards> findAllByThemeId(long flashcards_themesId);
}
