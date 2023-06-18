package by.tms.courseProject2.flashcards.repository;

import by.tms.courseProject2.flashcards.exception.RepositoryException;
import by.tms.courseProject2.flashcards.models.FlashCards;

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
            FlashCards flashCards = new FlashCards(
                    resultSet.getLong("id"),
                    resultSet.getLong("themeId"),
                    resultSet.getString("question"),
                    resultSet.getString("answer"),
                    resultSet.getBoolean("status_knowledge"));
            list.add(flashCards);
        }

        return list;
    }

    @Override
    public void save(long flashCards_themes_id, String question, String answer) {
        String sql = """
                INSERT INTO flashсard(flashcards_themes_id, question, answer, status_knowledge)
                VALUES (?,?,?,?)""";
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, flashCards_themes_id);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, false);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public boolean isExist(long flashCardId) {
        String sql = """
                SELECT  TRUE
                FROM flashсard
                WHERE id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, flashCardId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public boolean remove(long flashCardId) {
        String sql = """
                DELETE
                FROM flashсard
                WHERE id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCardId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return false;
    }

    @Override
    public boolean statusUpdateLearned(long flashCardId, boolean isLearned) {
        String sql = """
                UPDATE flashсard
                SET status_knowledge = ?
                WHERE id=?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isLearned);
            statement.setLong(2, flashCardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return isLearned;
    }

    @Override
    public Optional<FlashCards> getOneFlashCardNotLearned(long flashCards_themes_id, long nextCard) {
        String sql = """
                SELECT id               AS id,
                        flashcards_themes_id as themeId,
                        question         AS question,
                        answer           AS answer,
                        status_knowledge AS status_knowledge
                 FROM flashсard
                 WHERE flashcards_themes_id = ?
                   AND NOT flashсard.status_knowledge
                   AND flashсard.id > ?
                 ORDER BY flashсard.id
                 LIMIT 1""";
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCards_themes_id);
            statement.setLong(2, nextCard);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new FlashCards(
                        resultSet.getLong("id"),
                        resultSet.getLong("theme_id"),
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
    public List<FlashCards> findAllCardsByThemeId(long flashcards_themesId) {
        String sql = """
                SELECT id               AS id,
                       flashcards_themes_id as themeId,
                       question         AS question,
                       answer           AS answer,
                       status_knowledge AS status_knowledge
                FROM flashсard
                WHERE flashCards_themes_id = ?
                ORDER BY flashсard.id""";

        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashcards_themesId);
            ResultSet resultSet = statement.executeQuery();
            List<FlashCards> flashCardsList = new ArrayList<>();
            while (resultSet.next()) {
                flashCardsList.add(new FlashCards(
                        resultSet.getLong("id"),
                        resultSet.getLong("themeId"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("status_knowledge")
                ));
            }
            return getFlashCardsList(statement);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public Optional<FlashCards> findFlashCardById(long flashCardId) {
        String sql = """
                 SELECT id               AS id,
                       flashcards_themes_id as themeId,
                       question         AS question,
                       answer           AS answer,
                       status_knowledge AS status_knowledge
                FROM flashсard
                WHERE flashCards_themes_id = ?""";
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCardId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new FlashCards(
                        resultSet.getLong("id"),
                        resultSet.getLong("themeId"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("status_knowledge")
                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public FlashCards getFlashCardById(long flashCardId) {
        String sql = """
                SELECT id               AS id,
                       flashcards_themes_id as themeId,
                       question         AS question,
                       answer           AS answer,
                       status_knowledge AS status_knowledge
                FROM flashсard
                WHERE flashCards_themes_id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashCardId);
            ResultSet resultSet = statement.executeQuery();
            FlashCards flashCard = new FlashCards(
                    resultSet.getLong("id"),
                    resultSet.getLong("themeId"),
                    resultSet.getString("question"),
                    resultSet.getString("answer"),
                    resultSet.getBoolean("status_knowledge"));
            return flashCard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
