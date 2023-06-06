package by.tms.courseProject2.flashcards;

import by.tms.courseProject2.flashcards.repository.FlashCardsJDBCRepository;
import by.tms.courseProject2.flashcards.repository.FlashCardsRepository;
import by.tms.courseProject2.flashcards.repository.FlashCardsThemesJDBCRepository;
import by.tms.courseProject2.flashcards.repository.FlashCardsThemesRepository;
import by.tms.courseProject2.flashcards.service.FLashCardThemeServiceImpl;
import by.tms.courseProject2.flashcards.service.FlashCardService;
import by.tms.courseProject2.flashcards.service.FlashCardServiceImpl;
import by.tms.courseProject2.flashcards.service.FlashCardThemeService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlashCardsApplication implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("EV_Url"));
        hikariConfig.setUsername(System.getenv("EV_UserName"));
        hikariConfig.setPassword(System.getenv("EV_password"));
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


