package models;

public class Book {
    private String name;
    private String author;
    private double userRating;
    private int reviews;
    private int price;
    private int year;
    private String genre;
    
    public Book(String name, String author, double userRating, int reviews, int price, int year, String genre) {
        this.name = name;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }
    
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public double getUserRating() { return userRating; }
    public int getReviews() { return reviews; }
    public int getPrice() { return price; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    
    public void setName(String name) { this.name = name; }
    public void setAuthor(String author) { this.author = author; }
    public void setUserRating(double userRating) { this.userRating = userRating; }
    public void setReviews(int reviews) { this.reviews = reviews; }
    public void setPrice(int price) { this.price = price; }
    public void setYear(int year) { this.year = year; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public void printDetails() {
        System.out.println("Title: " + name);
        System.out.println("Author: " + author);
        System.out.println("Rating: " + userRating);
        System.out.println("Reviews: " + reviews);
        System.out.println("Price: $" + price);
        System.out.println("Year: " + year);
        System.out.println("Genre: " + genre);
        System.out.println();
    }
}
