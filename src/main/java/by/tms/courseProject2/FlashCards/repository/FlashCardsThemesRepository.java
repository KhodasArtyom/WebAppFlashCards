package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;

import java.util.List;

public interface FlashCardsThemesRepository {

    void save(String nameOfTheme);

    boolean remove(long id);

    List<FlashCardsThemes> getAllThemes();
}
