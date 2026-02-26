
public interface BookAPI {
    /**
     * Checks if the book is available
     * @return true if the book is available to be borrowed. False if it has already been borrowed
     */
    boolean isAvailable();
    /**
     * Marks the book as borrowed
     */
    void borrow();
    /**
     * Marks the book as available
     */
    void returnBook();
    /**
     * prints the books title, author and status
     */
    void printBook();
}