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
        {
            if (flashCardsRepository.isExist(flashCardId)) {
                flashCardsRepository.remove(flashCardId);
            }

        }
    }

    @Override
    public void setStatusOfKnowledge(long flashCardId, boolean isKnown) {
        if(flashCardsRepository.isExist(flashCardId)) {
            flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public Optional<FlashCards> trainingWithCard(long themeId, long nextCard) {
        if (!flashCardsThemesRepository.isExist(themeId)) {
            throw new ServiceException();
        }
        return flashCardsRepository.getOneFlashCard(themeId, nextCard);

    }

    @Override
    public List<FlashCards> findCardsByThemeId(long flashCardId) {
        return flashCardsRepository.findAllCardsByThemeId(flashCardId);
    }

    @Override
    public Optional<FlashCards> getNextFlashCard(long flashCardId) {
        FlashCards card ;
        if(flashCardsRepository.isExist(flashCardId)) {
            card = flashCardsRepository.getFlashCardById(flashCardId);
        }else {
            throw new ServiceException();
        }
        return flashCardsRepository.getOneFlashCard(card.getId(),flashCardId);
    }
}
