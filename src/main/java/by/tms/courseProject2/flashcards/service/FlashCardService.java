package by.tms.courseProject2.flashcards.service;

import by.tms.courseProject2.flashcards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardService  {

    void addNewCard(long flashCardId,String question,String answer);

    void deleteCard(long flashCardId);

    void setStatusOfKnowledge(long flashCardId,boolean isKnown);

    Optional<FlashCards> findCardByIdAndOffset(long flashCardId,long offset);

    List<FlashCards> findCardByThemeId(long flashCardId);




}
