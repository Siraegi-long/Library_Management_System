package dbPackage;

public class Book {
    private int bookID; // 도서 고유 번호
    private String bookName; // 도서명
    private String author; // 저자
    private String publisher; // 출판사
    private String category; // 도서 카테고리
    private int quantity; // 도서 수량

    public Book() {
    }

    public Book(int bookID, String bookName, String author, String publisher, String category, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
