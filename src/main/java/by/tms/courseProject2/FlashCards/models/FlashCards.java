package by.tms.courseProject2.FlashCards.models;

public class FlashCards {
    private final long id;
    private final String question;
    private final String answer;

    private final boolean statusKnowledge;

    public FlashCards(long id, String question, String answer, boolean statusKnowledge) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.statusKnowledge = statusKnowledge;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isStatusKnowledge() {
        return statusKnowledge;
    }

    @Override
    public String toString() {
        return "FlashCards{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", statusKnowledge=" + statusKnowledge +
                '}';
    }
}
