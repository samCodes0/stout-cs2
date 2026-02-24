public class Book implements BookAPI {
    private String title;
    private String author;
    boolean status = true;  // true of the book is available. False if the book has been borrowed

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void borrow() {

    }

    @Override
    public void returnBook() {

    }

    @Override
    public void printBook() {

    }
}
