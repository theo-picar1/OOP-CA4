package DTOs;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Expense {
    // Auto-increment ref: https://stackoverflow.com/questions/24305830/java-auto-increment-id
    private static final AtomicInteger count = new AtomicInteger(0);

    private int id; // auto-incremented
    private String title;
    private String category;
    private double amount;
    private Date incurred;

    // if ID is already known. Used for retrieving from database
    public Expense(int id, String title, String category, double amount, Date incurred) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.incurred = incurred;
    }

    // for creating a new one. ID is incremented here
    public Expense(String title, String category, double amount, Date incurred) {
        this.id = count.incrementAndGet();
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.incurred = incurred;
    }

    // default constructor
    public Expense() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return incurred;
    }

    public void setDate(Date incurred) {
        this.incurred = incurred;
    }

    @Override
    public String toString() {
        return "Expense{" + "ID = " + id + ", Title = " + title + ", Category = " + category + ", Amount = €" + amount + ", Date Incurred = " + incurred + '}';
    }
}
