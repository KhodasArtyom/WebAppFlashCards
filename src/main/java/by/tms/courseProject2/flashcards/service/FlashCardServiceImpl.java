package by.tms.courseProject2.flashcards.service;


import by.tms.courseProject2.flashcards.exception.ServiceException;
import by.tms.courseProject2.flashcards.models.FlashCards;
import by.tms.courseProject2.flashcards.repository.FlashCardsRepository;
import by.tms.courseProject2.flashcards.repository.FlashCardsThemesRepository;

import java.util.List;
import java.util.Optional;

public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardsRepository flashCardsRepository;
    private final FlashCardsThemesRepository flashCardsThemesRepository;
    private static final boolean DEFAULT_IS_LEARNED = false;

    public FlashCardServiceImpl(FlashCardsRepository flashCardsRepository, FlashCardsThemesRepository flashCardsThemesRepository) {
        this.flashCardsRepository = flashCardsRepository;
        this.flashCardsThemesRepository = flashCardsThemesRepository;
    }

    @Override
    public void addNewCard(long flashCardId, String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new ServiceException();
        } else if (!flashCardsThemesRepository.isExist(flashCardId)) {
            throw new ServiceException();
        } else {
            flashCardsRepository.save(flashCardId, question, answer, DEFAULT_IS_LEARNED);
        }
    }

    @Override
    public void deleteCard(long flashCardId) {
        boolean isExist = flashCardsThemesRepository.remove(flashCardId);
        if(!isExist) {
            throw new ServiceException();
        }
    }

    @Override
    public void setStatusOfKnowledge(long flashCardId, boolean isKnown) {
        boolean isUpdated = flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
        if(!isUpdated) {
            throw new ServiceException();
        }
    }

    @Override
    public Optional<FlashCards> trainingWithCardCardByTHemeIdAndOffset(long themeId, long offset) {
        if(!flashCardsThemesRepository.isExist(themeId)) {
            throw new ServiceException();
        }
        return flashCardsRepository.findFlashCardByThemeIdAndOffset(themeId, offset);

    }

    @Override
    public List<FlashCards> findCardByThemeId(long flashCardId) {
        return flashCardsRepository.findAllByThemeId(flashCardId);
    }
}
