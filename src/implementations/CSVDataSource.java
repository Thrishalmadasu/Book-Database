package implementations;

import interfaces.DataSource;
import models.Book;

import java.io.*;
import java.util.*;

public class CSVDataSource implements DataSource {
    private String filePath;
    
    public CSVDataSource(String filePath) {
        this.filePath = filePath;
    }
    
    public List<Book> loadBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        reader.readLine();
        String line;
        
        while ((line = reader.readLine()) != null) {
            Book book = parseLine(line);
            if (book != null) {
                books.add(book);
            }
        }
        reader.close();
        return books;
    }
    
    private Book parseLine(String line) {
        try {
            String[] parts = parseCSVLine(line);
            String name = parts[0].trim();
            String author = parts[1].trim();
            double rating = Double.parseDouble(parts[2].trim());
            int reviews = Integer.parseInt(parts[3].trim());
            int price = Integer.parseInt(parts[4].trim());
            int year = Integer.parseInt(parts[5].trim());
            String genre = parts[6].trim();
            
            return new Book(name, author, rating, reviews, price, year, genre);
        } catch (Exception e) {
            return null;
        }
    }
    
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result.toArray(new String[0]);
    }
    
    public boolean isAvailable() {
        return new File(filePath).exists();
    }
}
