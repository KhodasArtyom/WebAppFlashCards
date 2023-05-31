package by.tms.courseProject2.FlashCards.service;


import by.tms.courseProject2.FlashCards.exception.ServiceException;
import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlashCardServiceImpl implements FlashCardService{

    private final FlashCardsRepository flashCardsRepository;

    public FlashCardServiceImpl(FlashCardsRepository flashCardsRepository) {
        this.flashCardsRepository = flashCardsRepository;
    }

    @Override
    public void addNewCard(long flashCardId, String question, String answer, boolean isKnown) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new ServiceException();
        } else {
            flashCardsRepository.save(flashCardId,question,answer,isKnown);
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
    public void getStatusOfKnowledge(long flashCardId, boolean isKnown) {
                try {
                    flashCardsRepository.statusUpdateLearned(flashCardId,isKnown);
                }catch (ServiceException e) {
                    throw new ServiceException();
                }
    }

    @Override
    public Optional<FlashCards> findAllCardsByIdAndOffset(long flashCardId, long offset) {
        Optional<FlashCards> flashCards = flashCardsRepository.findAllFlashCardsByIdAndOffset(flashCardId,offset);
        try{
            return flashCards;
        }catch (ServiceException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<FlashCards> findAllCardsByThemeId(long flashCardId) {
        List<FlashCards> list = new ArrayList<>();
        if(!list.isEmpty()) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}
