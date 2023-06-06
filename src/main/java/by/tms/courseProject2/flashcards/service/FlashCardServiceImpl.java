package by.tms.courseProject2.flashcards.service;


import by.tms.courseProject2.flashcards.exception.ServiceException;
import by.tms.courseProject2.flashcards.models.FlashCards;
import by.tms.courseProject2.flashcards.repository.FlashCardsRepository;

import java.util.List;
import java.util.Optional;

public class FlashCardServiceImpl implements FlashCardService{

    private final FlashCardsRepository flashCardsRepository;

    public FlashCardServiceImpl(FlashCardsRepository flashCardsRepository) {
        this.flashCardsRepository = flashCardsRepository;
    }

    @Override
    public void addNewCard(long flashCardId, String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new ServiceException();
        } else {
            flashCardsRepository.save(flashCardId,question,answer);
        }
    }

    @Override
    public void deleteCard(long flashCardId) {
        try{
            flashCardsRepository.remove(flashCardId);
        }catch (ServiceException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void setStatusOfKnowledge(long flashCardId, boolean isKnown) {
                try {
                    flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
                }catch (ServiceException e) {
                    throw new ServiceException();
                }
    }

    @Override
    public Optional<FlashCards> findCardByIdAndOffset(long flashCardId, long offset) {
        Optional<FlashCards> flashCards = flashCardsRepository.findFlashCardByThemeIdAndOffset(flashCardId,offset);
        try{
            return flashCards;
        }catch (ServiceException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<FlashCards> findCardByThemeId(long flashCardId) {
        return flashCardsRepository.findAllByThemeId(flashCardId);
    }
}
