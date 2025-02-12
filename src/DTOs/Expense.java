package DTOs;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Expense extends Income{
    // Auto-increment ref: https://stackoverflow.com/questions/24305830/java-auto-increment-id
    private static final AtomicInteger count = new AtomicInteger(0);
    private String category;

    // if ID is already known. Used for retrieving from database
    public Expense(int id, String title, String category, double amount, Date incurred) {
        super(id, title, amount, incurred);
        this.category = category;
    }

    // for creating a new one. ID is incremented here
    public Expense(String title, String category, double amount, Date incurred) {
        super(title, amount, incurred);
        this.category = category;
    }

    // default constructor
    public Expense() { super(); }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return super.toString() + ", Category = " + category;
    }
}
