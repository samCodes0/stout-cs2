public class LibraryApp {
    public static void main() {
        Library library = new Library();
        library.addBook(new Book("Assassin's Apprentice", "Robin Hobb"));
        library.addBook(new Book("The Annotated Turing", "Charles Petzold"));
        library.addBook(new Book("The Maniac", "Benjamín Labatut"));
        library.addBook(new Book("Gardens of the Moon", "Steven Erikson"));
    }
}
