package by.tms.courseProject2.FlashCards;

import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.repository.FlashCardsJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        HikariDataSource dataSource = new HikariDataSource();
        log.info("The program has started");
        dataSource.setJdbcUrl(System.getenv("variableUrl"));
        dataSource.setUsername(System.getenv("variableUser"));
        dataSource.setPassword(System.getenv("variablePassword"));
        try (HikariDataSource db = new HikariDataSource(dataSource)) {
            FlashCardsThemesRepository flashCardsThemesRepository = new FlashCardsThemesJDBCRepository(dataSource);
            FlashCardsRepository flashCardsRepository = new FlashCardsJDBCRepository(dataSource);


            flashCardsThemesRepository.save("COLORS");
            flashCardsThemesRepository.save("NUMBERS");


            flashCardsRepository.save(1, "Black", "Чёрный", false);
            flashCardsRepository.save(1, "White", "Белый", false);
            flashCardsRepository.save(1, "Orange", "Оранжевый", false);
            flashCardsRepository.save(1, "Green", "Зелёный", false);
            flashCardsRepository.save(1, "Жёлтый", "Жёлтый", false);

            List<FlashCards> allByThemeId = flashCardsRepository.findAllByThemeId(1);
            System.out.println(allByThemeId);

            System.out.println("=========================");

            Optional<FlashCards> allFlashCardsByIdAndOffset = flashCardsRepository.findAllFlashCardsByIdAndOffset(1, 0);
            System.out.println(allFlashCardsByIdAndOffset);

            flashCardsRepository.statusUpdateLearned(1, true);
            flashCardsRepository.statusUpdateLearned(2, true);
            log.info("The program ended");




        }
    }
}
