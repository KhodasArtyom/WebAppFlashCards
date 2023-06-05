package by.tms.courseProject2.FlashCards.controller;

import by.tms.courseProject2.FlashCards.models.FlashCards;
import by.tms.courseProject2.FlashCards.service.FlashCardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = FlashCardsController.PATH_TO_FLASHCARDS)
public class FlashCardsController extends HttpServlet {
    private FlashCardService flashCardService;

    public final static String PATH_TO_FLASHCARDS = "/flashcards";

    @Override
    public void init()  {
        ServletContext servletContext = getServletContext();
        flashCardService = (FlashCardService) servletContext.getAttribute("flashCardService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long themeId = Long.valueOf(req.getParameter("themeId"));
        List<FlashCards> flashCardsList = flashCardService.findAllCardsByThemeId(themeId);
        String responseBody = flashCardsList.isEmpty()?"There is no such theme":flashCardsList.stream()
                .map(flashCards -> "%3s %-20s %2s / %-2s".formatted(flashCards.getId(),
                        flashCards.getQuestion(),
                        flashCards.getAnswer(),
                        flashCards.isStatusKnowledge()
                ))
                .collect(Collectors.joining("\n"));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(responseBody);
    }
}
