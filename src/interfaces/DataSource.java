package interfaces;

import models.Book;
import java.util.List;

// Interface for reading data from different sources
public interface DataSource {
    List<Book> loadBooks() throws Exception;
    boolean isAvailable();
}
