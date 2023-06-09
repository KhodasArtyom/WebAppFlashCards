package by.tms.courseProject2.flashcards.models;

public class FlashCards {
    private final long id;
    private final long theme_id;
    private final String question;
    private final String answer;

    private final boolean statusKnowledge;

    public FlashCards(long id, long themeId, String question, String answer, boolean statusKnowledge) {
        this.id = id;
        this.theme_id = themeId;
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

    public long getTheme_id() {
        return theme_id;
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
