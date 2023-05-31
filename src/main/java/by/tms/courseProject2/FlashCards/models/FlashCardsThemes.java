package by.tms.courseProject2.FlashCards.models;

public class FlashCardsThemes {

    private final long id;
    private final String title;

    private final long numberOfLearnedCards;
    private final long totalNumberOfCards;


    public FlashCardsThemes(long id, String title, long numberOfLearnedCards, long totalNumberOfCards) {
        this.id = id;
        this.title = title;
        this.numberOfLearnedCards = numberOfLearnedCards;
        this.totalNumberOfCards = totalNumberOfCards;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getNumberOfLearnedCards() {
        return numberOfLearnedCards;
    }

    public long getTotalNumberOfCards() {
        return totalNumberOfCards;
    }


    @Override
    public String toString() {
        return "FlashCardsThemes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberOfLearnedCards=" + numberOfLearnedCards +
                ", totalNumberOfCards=" + totalNumberOfCards +
                '}';
    }
}
