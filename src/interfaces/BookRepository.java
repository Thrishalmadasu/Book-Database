package interfaces;

import models.Book;
import java.util.List;
import java.util.Map;

// Interface for querying book data
public interface BookRepository {
    int getTotalBooksByAuthor(String author);
    List<String> getAllAuthors();
    List<String> getBooksByAuthor(String author);
    List<Book> getBooksByRating(double rating);
    Map<String, Integer> getPricesByAuthor(String author);
}
