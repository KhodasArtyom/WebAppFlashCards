package by.tms.courseProject2.flashcards.controller;

import by.tms.courseProject2.flashcards.models.FlashCardsThemes;
import by.tms.courseProject2.flashcards.service.FlashCardThemeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/themes")
public class FLashCardsThemesController extends HttpServlet {

    private FlashCardThemeService flashCardThemeService;


    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardThemeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlashCardsThemes> themes = flashCardThemeService.seeAllThemes();
        req.setAttribute("themes",themes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/themes.jsp");
        dispatcher.forward(req,resp);

//        String responseBody = flashCardThemesList.isEmpty() ? "There is no themes" : flashCardThemesList.stream()
//                .map(flashCardsThemes -> "%3s %-20s %2s / %-2s".formatted(
//                        flashCardsThemes.getId(),
//                        flashCardsThemes.getTitle(),
//                        flashCardsThemes.getNumberOfLearnedCards(),
//                        flashCardsThemes.getTotalNumberOfCards()
//                ))
//                .collect(Collectors.joining("\n"));
//
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("utf-8");
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println(responseBody);


    }
}
