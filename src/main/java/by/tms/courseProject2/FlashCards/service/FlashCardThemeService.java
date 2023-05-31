package by.tms.courseProject2.FlashCards.service;

import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;

import java.util.List;

public interface FlashCardThemeService {

    void addNewTheme(String name);

    void removeTheme(long flashCardThemeID);

    List<FlashCardsThemes> seeAllThemes();
}
