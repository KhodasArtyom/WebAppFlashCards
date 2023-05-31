package by.tms.courseProject2.FlashCards.repository;

import by.tms.courseProject2.FlashCards.exception.RepositoryException;
import by.tms.courseProject2.FlashCards.models.FlashCards;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlashCardsJDBCRepository implements FlashCardsRepository {

    private final DataSource db;

    public FlashCardsJDBCRepository(DataSource db) {
        this.db = db;
    }

    private List<FlashCards> getFlashCardsList(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<FlashCards> list = new ArrayList<>();
        while (resultSet.next()) {
            FlashCards flashCards = new FlashCards(resultSet.getLong("id"),
                    resultSet.getString("question"),
                    resultSet.getString("answer"),
                    resultSet.getBoolean("status_knowledge"));
            list.add(flashCards);
        }

        return list;
    }

    @Override
    public void save(long flashCards_themes_id, String question, String answer, boolean isLearned) {
        String sql = """
                INSERT INTO flashcard(flashCards_themes_id, question, answer, status_knowledge)
                VALUES (?,?,?,?)             
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, flashCards_themes_id);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, isLearned);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public void remove(long flashCardId) {
        String sql = """
                DELETE
                FROM flashcard
                WHERE id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCardId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void statusUpdateLearned(long flashCardId, boolean isLearned) {
        String sql = """
                UPDATE flashcard
                SET status_knowledge = ?
                WHERE flashcard.id=?;
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isLearned);
            statement.setLong(2, flashCardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<FlashCards> findAllFlashCardsByIdAndOffset(long flashCards_themes_id, long offset) {
        String sql = """
                SELECT id               AS id,
                       question         AS question,
                       answer           AS answer,
                       status_knowledge AS status_knowledge
                FROM flashcard
                WHERE flashcards_themes_id = ?
                  AND NOT flashcard.status_knowledge
      
                ORDER BY flashcard.id
                LIMIT 1 OFFSET ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCards_themes_id);
            statement.setLong(2, offset);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new FlashCards(resultSet.getLong("id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("status_knowledge")));

            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<FlashCards> findAllByThemeId(long flashcards_themesId) {
        String sql = """
                SELECT id               AS id,
                       question         AS question,
                       answer           AS answer,
                       status_knowledge AS status_knowledge
                FROM flashcard
                WHERE flashCards_themes_id = ?;
                """;

        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashcards_themesId);
            return getFlashCardsList(statement);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }


}
