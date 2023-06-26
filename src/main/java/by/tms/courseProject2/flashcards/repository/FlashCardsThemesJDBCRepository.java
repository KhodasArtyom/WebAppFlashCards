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
    public boolean isExistById(long themeId) {
        String sql = """
                SELECT TRUE
                FROM flashсard_theme
                WHERE flashсard_theme.id = ?""";
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
                INSERT INTO flashсard_theme( set_name)
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
                FROM flashсard_theme
                WHERE flashсard_theme.id = ?
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate()==1;

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public List<FlashCardsThemes> getAllThemes() {
        String sql = """
                SELECT flashсard_theme.id                                              AS id,
                       flashсard_theme.set_name                                          AS name,
                       count(flashсard.id) FILTER ( WHERE flashсard.status_knowledge ) AS succsess,
                       count(flashсard.id)                                              AS global
                FROM flashсard_theme
                            LEFT JOIN flashсard ON flashсard_theme.id = flashсard.flashCards_themes_id
                GROUP BY flashсard_theme.id
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
