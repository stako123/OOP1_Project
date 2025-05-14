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
}
