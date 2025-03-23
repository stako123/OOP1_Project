import java.io.*;
import java.util.*;


public class Book {
    private String author;
    private String title;
    private String genre;
    private String description;
    private int year;
    List<String> keywords;
    private double rating;
    private String uniqueNumber;

    Book(String author, String title, String genre, String description, int year, List<String> keywords, double rating, String uniqueNumber) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = keywords;
        this.rating = rating;
        this.uniqueNumber = uniqueNumber;
    }

    @Override
    public String toString() {
        return "Library Number: " + uniqueNumber + "\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Genre: " + genre + "\n" +
                "Description: " + description + "\n" +
                "Year: " + year + "\n" +
                "Keywords: " + String.join(", ", keywords) + "\n" +
                "Rating: " + rating + "\n";
    }
}
