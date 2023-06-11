package by.tms.courseProject2.flashcards.service;

import by.tms.courseProject2.flashcards.exception.ServiceException;
import by.tms.courseProject2.flashcards.models.FlashCardsThemes;
import by.tms.courseProject2.flashcards.repository.FlashCardsThemesRepository;

import java.util.List;

public class FLashCardThemeServiceImpl implements FlashCardThemeService {

    private final FlashCardsThemesRepository flashCardsThemesRepository;


    public FLashCardThemeServiceImpl(FlashCardsThemesRepository flashCardsThemesRepository) {
        this.flashCardsThemesRepository = flashCardsThemesRepository;
    }


    @Override
    public void addNewTheme(String name) {
        if (!name.isEmpty()) {
            flashCardsThemesRepository.save(name);
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public void removeTheme(long flashCardThemeID) {
        boolean exist = flashCardsThemesRepository.remove(flashCardThemeID);
        if(!exist) {
            throw new ServiceException();
        }
    }



    @Override
    public List<FlashCardsThemes> seeAllThemes() {
        return flashCardsThemesRepository.getAllThemes();
    }
}
