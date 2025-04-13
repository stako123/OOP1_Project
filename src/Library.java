import java.io.Console;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentUser = null;
        this.scanner = new Scanner(System.in);
    }

    public void Login() {
        if (currentUser != null) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = readPassword();

        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {
            currentUser = user.get();
            System.out.println("Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private String readPassword() {
        Console console = System.console();
        if (console != null) {
            return new String(console.readPassword());
        } else {
            return scanner.nextLine();
        }
    }

    public void logout() {
        if (currentUser == null) {
            System.out.println("You are not logged in.");
            return;
        }
        currentUser = null;
        System.out.println("Successfully logged out.");
    }

    public void displayAllBooks() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(book -> System.out.println(book.toShortString() + "\n"));
    }
}
