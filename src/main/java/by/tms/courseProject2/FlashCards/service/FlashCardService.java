package by.tms.courseProject2.FlashCards.service;

import by.tms.courseProject2.FlashCards.models.FlashCards;

import java.util.List;
import java.util.Optional;

public interface FlashCardService  {

    void addNewCard(long flashCardId,String question,String answer,boolean isKnown);

    void deleteCard(long flashCardId);

    void getStatusOfKnowledge(long flashCardId,boolean isKnown);

    Optional<FlashCards> findAllCardsByIdAndOffset(long flashCardId,long offset);

    List<FlashCards> findAllCardsByThemeId(long flashCardId);




}
