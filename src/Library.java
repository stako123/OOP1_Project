import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;

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

    public void displayBookInfo(String libraryNumber) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        books.stream()
                .filter(book -> book.getUniqueNumber().equals(libraryNumber))
                .findFirst()
                .ifPresentOrElse(
                        book -> System.out.println(book.toString()),
                        () -> System.out.println("Book not found.")
                );
    }

    public void findBooks(String option, String input) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        String search = input.toLowerCase();
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            switch (option.toLowerCase()) {
                case "title":
                    if (book.getTitle().toLowerCase().contains(search)) {
                        results.add(book);
                    }
                    break;
                case "author":
                    if (book.getAuthor().toLowerCase().contains(search)) {
                        results.add(book);
                    }
                    break;
                case "tag":
                    for (String keyword : book.getKeywords()) {
                        if (keyword.toLowerCase().contains(search)) {
                            results.add(book);
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid search option.");
                    return;
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : results) {
            System.out.println(book.toShortString() + "\n");
        }
    }


    public void sortBooks(String option, boolean ascending) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        List<Book> sortedBooks = new ArrayList<>(books);
        switch (option.toLowerCase()) {
            case "title":
                sortedBooks.sort((a, b) -> ascending ?
                        a.getTitle().compareToIgnoreCase(b.getTitle()) :
                        b.getTitle().compareToIgnoreCase(a.getTitle()));
                break;
            case "author":
                sortedBooks.sort((a, b) -> ascending ?
                        a.getAuthor().compareToIgnoreCase(b.getAuthor()) :
                        b.getAuthor().compareToIgnoreCase(a.getAuthor()));
                break;
            case "year":
                sortedBooks.sort((a, b) -> ascending ?
                        Integer.compare(a.getYear(), b.getYear()) :
                        Integer.compare(b.getYear(), a.getYear()));
                break;
            case "rating":
                sortedBooks.sort((a, b) -> ascending ?
                        Double.compare(a.getRating(), b.getRating()) :
                        Double.compare(b.getRating(), a.getRating()));
                break;
            default:
                System.out.println("Invalid sort option.");
                return;
        }

        sortedBooks.forEach(book -> System.out.println(book.toShortString() + "\n"));
    }
}
