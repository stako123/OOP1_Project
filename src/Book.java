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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
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

    public String toShortString() {
        return "Title" + title +
                "Author" + author +
                "Gerne" + genre +
                "Libraly Number" + uniqueNumber;
    }
}
