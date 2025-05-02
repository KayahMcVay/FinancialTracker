// Import date & time classes from java.time package //
import java.time.LocalDate;
import java.time.LocalTime;

// Class representing single financial transaction (either deposit or payment) //
public class Transaction {

    // Date of transaction //
    private LocalDate date;

    // Time of transaction //
    private LocalTime time;

    // Description of transaction //
    private String description;

    // Vendor of transaction //
    private String vendor;

    // Amount of transaction (positive for deposit & negative for payment) //
    private double amount;

    // Constructor to initialize all fields of transaction //
    public Transaction(LocalDate date, LocalTime time, String description,
                       String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Setter for date //
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Setter for time //
    public void setTime(LocalTime time) {
        this.time = time;
    }

    // Setter for description //
    public void setDescription(String description) {
        this.description = description;
    }

    // Setter for vendor //
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // Setter for amount //
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter for date //
    public LocalDate getDate() {
        return this.date;
    }

    // Getter for time //
    public LocalTime getTime() {
        return this.time;
    }

    // Getter for description //
    public String getDescription() {
        return this.description;
    }

    // Getter for vendor //
    public String getVendor() {
        return this.vendor;
    }

    // Getter for amount //
    public double getAmount() {
        return this.amount;
    }

    // Returns formatted string representation of transaction //
    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount ;
    }
}
