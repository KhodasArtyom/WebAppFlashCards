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
            flashCardsRepository.save(flashCardId, question, answer);
        }
    }

    @Override
    public FlashCards deleteCard(long flashCardId) {
        {
            FlashCards flashCard = flashCardsRepository.findFlashCardById(flashCardId)
                    .orElseThrow(ServiceException::new);
            flashCardsRepository.remove(flashCardId);
            return flashCard;

        }

    }

    @Override
    public FlashCards setStatusOfKnowledge(long flashCardId, boolean isKnown) {
        boolean isExist = flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
        if(!isExist) {
            throw new ServiceException();
        }
        return  flashCardsRepository.findFlashCardById(flashCardId).orElseThrow();
    }


    @Override
    public List<FlashCards> findCardsByThemeId(long flashCardId) {
        checkIfExist(flashCardId);
        return flashCardsRepository.findAllCardsByThemeId(flashCardId);
    }

    @Override
    public Optional<FlashCards> getNextFlashCard(long themeId, long flashCardId) {
        FlashCards flashCard;

        if (flashCardsRepository.isExist(themeId)) {
              flashCard = flashCardsRepository.getFlashCardById(themeId);
        }else {
            throw new ServiceException();
        }
        return flashCardsRepository.getOneFlashCardNotLearned(flashCard.getId(),themeId);
    }

    @Override
    public FlashCards getTheFirstFlashCard(long themeId) {
        return flashCardsRepository.getOneFlashCardNotLearned(themeId, 0).orElseThrow(ServiceException::new);

    }

    @Override
    public void markIsDone(long flashCardId) {
        boolean isExist = flashCardsRepository.statusUpdateLearned(flashCardId, true);
        if (!isExist) throw new ServiceException();
    }

    private void checkIfExist(long themeId) {
        if (!flashCardsThemesRepository.isExist(themeId)) {
            throw new ServiceException();
        }
    }
}
