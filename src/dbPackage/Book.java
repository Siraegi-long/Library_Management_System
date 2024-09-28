package dbPackage;

public class Book {
    private int bookID;
    private String bookName;
    private String author;
    private String publisher;
    private String category;
    private int quantity; // 남은 도서 수량
    private boolean isRented; // 도서 대여 여부

    public Book(int bookID, String bookName, String author, String publisher, String category, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
        this.isRented = false;
    }

    // Getter 및 Setter
    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    // 도서 대여
    public void rentBook(int memberId) {
        if (quantity > 0 && !isRented) {
            quantity--;
            isRented = true;
            System.out.println("도서가 대여되었습니다.");
        } else {
            System.out.println("대여 불가능. 재고가 없습니다.");
        }
    }

    // 도서 반납
    public void returnBook() {
        quantity++;
        isRented = false;
        System.out.println("도서가 반납되었습니다.");
    }

    // 대여 연장 요청
    public void extendRentalPeriod() {
        System.out.println("대여 연장 요청 처리 중...");
        // 연장 요청 로직 구현 필요
    }

    // 대여 연장 상태 확인
    public void checkExtensionStatus() {
        System.out.println("연장 상태를 확인합니다.");
        // 연장 상태 확인 로직 구현 필요
    }
}
