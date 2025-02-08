package DTOs;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Income {
    // Auto-increment ref: https://stackoverflow.com/questions/24305830/java-auto-increment-id
    private static final AtomicInteger count = new AtomicInteger(0);

    private int id; // auto-incremented
    private String title;
    private double amount;
    private Date earned;

    // if ID is already known. Used for retrieving from database
    public Income(int id, String title, double amount, Date earned) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.earned = earned;
    }

    // for creating a new one. ID is incremented here
    public Income(String title, double amount, Date earned) {
        this.id = count.incrementAndGet();
        this.title = title;
        this.amount = amount;
        this.earned = earned;
    }

    // default constructor
    public Income() { }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return earned;
    }

    public void setDate(Date earned) {
        this.earned = earned;
    }
}
