public interface LibraryAPI {
    void addBook(Book book);
    boolean borrowBook(String title);
    boolean hasAvailableBooks();
    void printLibrary();
}