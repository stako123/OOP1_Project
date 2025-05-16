import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String BOOKS_FILE = "books.txt";
    private static final String USERS_FILE = "users.txt";
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "i<2Java";

    public static void initializeFiles() {
        File usersFile = new File(USERS_FILE);
        if (!usersFile.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
                writer.println(String.format("%s,%s,true", DEFAULT_ADMIN_USERNAME, DEFAULT_ADMIN_PASSWORD));
            } catch (IOException e) {
                System.err.println("Error creating users file: " + e.getMessage());
            }
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.println(user.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] keywords = parts[5].split(",");
                books.add(new Book(
                        parts[0], // avtor
                        parts[1], // ime
                        parts[2], // janr
                        parts[3], // opisanie
                        Integer.parseInt(parts[4]), // godina
                        List.of(keywords), // kluchova duma
                        Double.parseDouble(parts[6]), // ocenka
                        parts[7]  // id
                ));
            }
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.println(String.format("%s|%s|%s|%s|%d|%s|%.2f|%s",
                        book.getAuthor(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getDescription(),
                        book.getYear(),
                        String.join(",", book.getKeywords()),
                        book.getRating(),
                        book.getUniqueNumber()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }
}
