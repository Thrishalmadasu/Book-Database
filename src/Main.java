import interfaces.*;
import implementations.*;
import models.Book;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            DataSource dataSource = new CSVDataSource("../bestsellers with categories.csv");
            BookRepository repository = new HashMapBookRepository(dataSource);
            
            System.out.println("Book database system initialized successfully");
            System.out.println();
            
            showMenu(repository);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void showMenu(BookRepository repository) {
        while (true) {
            System.out.println("Choose a task:");
            System.out.println("1. Count books by author");
            System.out.println("2. List all authors");
            System.out.println("3. Get books by author");
            System.out.println("4. Find books by rating");
            System.out.println("5. Get book prices by author");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());
                System.out.println();
                
                switch (choice) {
                    case 1:
                        countBooksByAuthor(repository);
                        break;
                    case 2:
                        listAllAuthors(repository);
                        break;
                    case 3:
                        getBooksByAuthor(repository);
                        break;
                    case 4:
                        findBooksByRating(repository);
                        break;
                    case 5:
                        getPricesByAuthor(repository);
                        break;
                    case 6:
                        System.out.println("Thank you for using the Book Database System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            System.out.println();
        }
    }
    
    private static void countBooksByAuthor(BookRepository repository) {
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        int count = repository.getTotalBooksByAuthor(author);
        System.out.println(author + " has " + count + " book(s) in the dataset.");
    }
    
    private static void listAllAuthors(BookRepository repository) {
        List<String> authors = repository.getAllAuthors();
        System.out.println("Total authors: " + authors.size());
        for (String author : authors) {
            System.out.println("- " + author);
        }
    }
    
    private static void getBooksByAuthor(BookRepository repository) {
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        List<String> books = repository.getBooksByAuthor(author);
        System.out.println("Books by " + author + " (" + books.size() + " total):");
        for (String book : books) {
            System.out.println("- " + book);
        }
    }
    
    private static void findBooksByRating(BookRepository repository) {
        System.out.print("Enter rating (e.g., 4.7): ");
        try {
            double rating = Double.parseDouble(sc.nextLine());
            List<Book> books = repository.getBooksByRating(rating);
            System.out.println("Books with rating " + rating + " (" + books.size() + " total):");
            for (Book book : books) {
                System.out.println("- " + book.getName() + " by " + book.getAuthor());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating. Please enter a decimal number.");
        }
    }
    
    private static void getPricesByAuthor(BookRepository repository) {
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        Map<String, Integer> prices = repository.getPricesByAuthor(author);
        System.out.println("Book prices by " + author + " (" + prices.size() + " books):");
        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
            System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
        }
    }
}
