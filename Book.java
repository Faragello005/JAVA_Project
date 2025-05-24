public class Book {
    static int next_id = 1;
    int id;
    String name;
    String category;
    boolean isborrowed;
    int borrow_period;
    String borrow_date;

    Book(String name, String category) {
        this.id = next_id;
        next_id++;
        this.name = name;
        this.category = category;
        this.isborrowed = false;
        this.borrow_period = 0;
        this.borrow_date = "";
    }
}