package implementations;

import interfaces.BookRepository;
import interfaces.DataSource;
import models.Book;

import java.util.*;

public class HashMapBookRepository implements BookRepository {
    private Map<String, List<Book>> authorToBooks;
    private Map<Double, List<Book>> ratingToBooks;
    
    public HashMapBookRepository(DataSource dataSource) throws Exception {
        authorToBooks = new HashMap<>();
        ratingToBooks = new HashMap<>();
        
        if (!dataSource.isAvailable()) {
            throw new Exception("Data source is not available");
        }
        
        List<Book> books = dataSource.loadBooks();
        loadBooks(books);
    }
    
    private void loadBooks(List<Book> books) {
        for (Book book : books) {
            if (!authorToBooks.containsKey(book.getAuthor())) {
                authorToBooks.put(book.getAuthor(), new ArrayList<>());
            }
            authorToBooks.get(book.getAuthor()).add(book);
            
            if (!ratingToBooks.containsKey(book.getUserRating())) {
                ratingToBooks.put(book.getUserRating(), new ArrayList<>());
            }
            ratingToBooks.get(book.getUserRating()).add(book);
        }
    }
    
    public int getTotalBooksByAuthor(String author) {
        List<Book> books = authorToBooks.get(author);
        return books != null ? books.size() : 0;
    }
    
    public List<String> getAllAuthors() {
        return new ArrayList<>(authorToBooks.keySet());
    }
    
    public List<String> getBooksByAuthor(String author) {
        List<String> bookNames = new ArrayList<>();
        List<Book> books = authorToBooks.get(author);
        if (books != null) {
            for (Book book : books) {
                bookNames.add(book.getName());
            }
        }
        return bookNames;
    }
    
    public List<Book> getBooksByRating(double rating) {
        List<Book> books = ratingToBooks.get(rating);
        return books != null ? books : new ArrayList<>();
    }
    
    public Map<String, Integer> getPricesByAuthor(String author) {
        Map<String, Integer> prices = new HashMap<>();
        List<Book> books = authorToBooks.get(author);
        if (books != null) {
            for (Book book : books) {
                prices.put(book.getName(), book.getPrice());
            }
        }
        return prices;
    }
}
