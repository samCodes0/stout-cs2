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
        return status;
    }

    @Override
    public void borrow() {
        this.status = false;
    }

    @Override
    public void returnBook() {
        this.status = true;
    }

    @Override
    public void printBook() {
        System.out.printf("Title: %s, Author: %s, Status: %b\n", title, author, status);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
