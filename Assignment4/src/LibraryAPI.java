public interface LibraryAPI {
    /**
     * Adds a book to the collection
     * @param book the book to add to the collection
     */
    void addBook(Book book);

    /**
     * Tries to borrow a book by title
     * @param title the title of the book to attempt to borrow
     * @return true of the book was successfully borrowed. False if it was unsuccessful
     */
    boolean borrowBook(String title);

    /**
     * check if at least one book is available
     * @return true if there is at least one book available. False if no books are available
     */
    boolean hasAvailableBooks();

    /**
     * prints all books in the library
     */
    void printLibrary();
}