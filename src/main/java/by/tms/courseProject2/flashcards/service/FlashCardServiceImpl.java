package by.tms.courseProject2.flashcards.service;


import by.tms.courseProject2.flashcards.exception.ServiceException;
import by.tms.courseProject2.flashcards.models.FlashCards;
import by.tms.courseProject2.flashcards.repository.FlashCardsRepository;
import by.tms.courseProject2.flashcards.repository.FlashCardsThemesRepository;

import java.util.List;

public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardsThemesRepository flashCardsThemesRepository;
    private final FlashCardsRepository flashCardsRepository;


    public FlashCardServiceImpl(FlashCardsThemesRepository flashCardsThemesRepository, FlashCardsRepository flashCardsRepository) {
        this.flashCardsThemesRepository = flashCardsThemesRepository;
        this.flashCardsRepository = flashCardsRepository;

    }

    @Override
    public void addNewCard(long flashCardId, String question, String answer) {
        if (flashCardsRepository.isExist(flashCardId)) {
            if (!question.isEmpty() || !answer.isEmpty()) {
                flashCardsRepository.save(flashCardId, question, answer);
            } else {
                throw new ServiceException();
            }
        }
    }

    @Override
    public long deleteCard(long flashCardId) {
        FlashCards flashCard;
        if (flashCardsRepository.isExist(flashCardId)) {
            flashCard = flashCardsRepository.getFlashCardById(flashCardId);
            flashCardsRepository.remove(flashCardId);
        } else {
            throw new ServiceException();
        }
        return flashCard.getTheme_id();
    }

    @Override
    public void setStatusOfKnowledge(long flashCardId) {
        if (flashCardsRepository.isExist(flashCardId)) {
            flashCardsRepository.statusUpdateLearned(flashCardId);
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public List<FlashCards> findCardsByThemeId(long flashCardId) {
        checkIfExist(flashCardId);
        return flashCardsRepository.findAllCardsByThemeId(flashCardId);
    }

    @Override
    public FlashCards getNextCard(long cardId) {
        FlashCards flashCard;
        if (flashCardsRepository.isExist(cardId)) {
            flashCard = flashCardsRepository.getFlashCardById(cardId);

        } else {
            throw new ServiceException();
        }
        return flashCardsRepository.getOneFlashCardNotLearned(flashCard.getTheme_id(), cardId);
    }

    @Override
    public FlashCards getNextFlashCard(long themeId, long greaterCard) {
        if (flashCardsThemesRepository.isExistById(themeId)) {
            return flashCardsRepository.getOneFlashCardNotLearned(themeId, greaterCard);
        } else {
            throw new ServiceException();

        }
    }


    private void checkIfExist(long themeId) {
        if (!flashCardsRepository.isExist(themeId)) {
            throw new ServiceException();
        }
    }
}
