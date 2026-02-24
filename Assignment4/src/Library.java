public class Library implements LibraryAPI {
    final int maxBooks = 4;
    Book[] books;
    int count;  // number of books currently stored

    @Override
    public void addBook(Book book) {

    }

    @Override
    public boolean borrowBook(String title) {
        return false;
    }

    @Override
    public boolean hasAvailableBooks() {
        return false;
    }

    @Override
    public void printLibrary() {

    }
}
