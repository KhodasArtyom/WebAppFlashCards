package by.tms.courseProject2.flashcards.service;

import by.tms.courseProject2.flashcards.models.FlashCardsThemes;

import java.util.List;

public interface FlashCardThemeService {

    void addNewTheme(String name);

    void removeTheme(long flashCardThemeID);

    List<FlashCardsThemes> seeAllThemes();
}
