package by.tms.courseProject2.FlashCards.controller;

import by.tms.courseProject2.FlashCards.service.FlashCardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(urlPatterns = "/flashcards")
public class FlashCardsController extends HttpServlet {
    private FlashCardService flashCardService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        flashCardService = (FlashCardService) servletContext.getAttribute("flashCardService");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }
}
