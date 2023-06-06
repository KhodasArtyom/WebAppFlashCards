package by.tms.courseProject2.flashcards.repository;

import by.tms.courseProject2.flashcards.models.FlashCardsThemes;

import java.util.List;

public interface FlashCardsThemesRepository {

    void save(String nameOfTheme);

    boolean remove(long id);

    List<FlashCardsThemes> getAllThemes();

    boolean isExist(long themeId);
}
