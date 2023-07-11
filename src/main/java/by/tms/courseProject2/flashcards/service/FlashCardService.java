package by.tms.courseProject2.flashcards.service;

import by.tms.courseProject2.flashcards.models.FlashCards;

import java.util.List;

public interface FlashCardService  {

    void addNewCard(long flashCardId,String question,String answer);

    long deleteCard(long flashCardId);

    void setStatusOfKnowledge(long flashCardId);


    List<FlashCards> findCardsByThemeId(long flashCardId);

   FlashCards getNextFlashCard(long themeId,long previousCardId);

    FlashCards getNextCard(long themeId);








}
