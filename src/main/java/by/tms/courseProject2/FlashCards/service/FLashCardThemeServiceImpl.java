package by.tms.courseProject2.FlashCards.service;

import by.tms.courseProject2.FlashCards.exception.ServiceException;
import by.tms.courseProject2.FlashCards.models.FlashCardsThemes;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;

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
        try {
            flashCardsThemesRepository.remove(flashCardThemeID);
        } catch (ServiceException e) {
            throw new ServiceException();
        }

    }

    @Override
    public List<FlashCardsThemes> seeAllThemes() {

        return flashCardsThemesRepository.getAllThemes();
    }
}
