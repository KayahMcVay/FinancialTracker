import java.time.format.DateTimeFormatter;

public class Transaction {

    private DateTimeFormatter date;

    private DateTimeFormatter time;

    private String description;

    private String vendor;

    private double amount;

    public Transaction(DateTimeFormatter date, DateTimeFormatter time, String description,
                       String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public void setTime(DateTimeFormatter time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DateTimeFormatter getDate() {
        return this.date;
    }

    public DateTimeFormatter getTime() {
        return this.time; 
    }

    public String getDescription() {
        return this.description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public double getAmount() {
        return this.amount;
    }

}
