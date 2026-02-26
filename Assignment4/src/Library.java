public class Library implements LibraryAPI {
    final int maxBooks = 4;
    Book[] books;
    int count;  // number of books currently stored

    public Library() {
        books = new Book[maxBooks];
        count = 0;
    }

    @Override
    public void addBook(Book book) {
        books[count] = book;
        count++;
    }

    @Override
    public boolean borrowBook(String title) {
        boolean result = false;
        for (int i = 0; i < count; i++) {
            if (title.equals(books[i].getTitle()) && books[i].isAvailable()) {
                books[i].borrow();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAvailableBooks() {
        return count < maxBooks;
    }

    @Override
    public void printLibrary() {
        System.out.println("Library Contents:");
        for (Book book : books) {
            book.printBook();
        }
    }
}
