package by.tms.courseProject2.flashcards.exception;

public class ServiceException extends RuntimeException{

    public ServiceException() {
        super("Error.Enter correct data");
    }
}
