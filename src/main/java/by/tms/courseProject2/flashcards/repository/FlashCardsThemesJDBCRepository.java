package by.tms.courseProject2.flashcards.repository;

import by.tms.courseProject2.flashcards.exception.RepositoryException;
import by.tms.courseProject2.flashcards.models.FlashCardsThemes;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlashCardsThemesJDBCRepository implements FlashCardsThemesRepository {
    private final DataSource db;

    public FlashCardsThemesJDBCRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public boolean isExist(long themeId) {
        String sql = """
                SELECT TRUE
                FROM flashcard_theme
                WHERE flashcard_theme.id = ?""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setLong(1, themeId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(String nameOfTheme) {
        String sql = """
                INSERT INTO flashcard_theme( set_name)
                VALUES (?)
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameOfTheme);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public boolean remove(long id) {
        String sql = """
                DELETE
                FROM flashCard_theme
                WHERE id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return false;
    }

    @Override
    public List<FlashCardsThemes> getAllThemes() {
        String sql = """
                SELECT flashCard_theme.id                                              AS id,
                       flashCard_theme.set_name                                          AS name,
                       count(flashcard.id) FILTER ( WHERE flashcard.status_knowledge ) AS succsess,
                       count(flashcard.id)                                              AS global
                FROM flashCard_theme
                            LEFT JOIN flashcard ON flashCard_theme.id = flashcard.flashCards_themes_id
                GROUP BY flashCard_theme.id
                """;
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<FlashCardsThemes> listResult = new ArrayList<>();
            while (resultSet.next()) {
                FlashCardsThemes flashCardsThemes = new FlashCardsThemes(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("succsess"),
                        resultSet.getLong("global"));
                listResult.add(flashCardsThemes);
            }
            return listResult;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }
}
