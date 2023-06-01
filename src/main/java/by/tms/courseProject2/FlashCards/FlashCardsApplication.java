package by.tms.courseProject2.FlashCards;

import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.repository.FlashCardsJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesJDBCRepository;
import by.tms.courseProject2.FlashCards.repository.FlashCardsThemesRepository;
import by.tms.courseProject2.FlashCards.service.FLashCardThemeServiceImpl;
import by.tms.courseProject2.FlashCards.service.FlashCardService;
import by.tms.courseProject2.FlashCards.service.FlashCardServiceImpl;
import by.tms.courseProject2.FlashCards.service.FlashCardThemeService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
@WebListener
public class FlashCardsApplication implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("1279");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        FlashCardsThemesRepository flashCardsThemesRepository = new FlashCardsThemesJDBCRepository(hikariDataSource);
        FlashCardsRepository flashCardsRepository = new FlashCardsJDBCRepository(hikariDataSource);

        FlashCardThemeService flashCardThemeService = new FLashCardThemeServiceImpl(flashCardsThemesRepository);
        FlashCardService flashCardService = new FlashCardServiceImpl(flashCardsRepository);

        ServletContext context = event.getServletContext();
        context.setAttribute("hikariDataSource", hikariDataSource);
        context.setAttribute("flashCardService", flashCardService);
        context.setAttribute("flashCardThemeService", flashCardThemeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        HikariDataSource hikariDataSource = (HikariDataSource) context.getAttribute("hikariDataSource");
        hikariDataSource.close();
    }


}


