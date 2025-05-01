import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Transaction transaction = new Transaction(null, null, " ", " ", 0.00);

        Transaction transaction2 = new Transaction(null, null, " ", " ", 0.00);


        ArrayList<Transaction> transactions = new ArrayList<>();

        displayHomeScreen(scanner, transactions, transaction, transaction2);

    }

    public static void displayHomeScreen(Scanner scanner, ArrayList<Transaction> transactions, Transaction transaction, Transaction transaction2){

        while(true) {

            System.out.println("\n-- Home Screen --\n");

            System.out.println("D - Add Deposit");
            System.out.println("P - Make Payment (Debit)");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");

            System.out.print("\nChose one of the above options: ");

            String userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase("d")) {

                addDeposit(scanner, transaction, transactions);

            } else if (userChoice.equalsIgnoreCase("p")) {

                addPayment(scanner, transaction, transactions);

            } else if (userChoice.equalsIgnoreCase("l")) {

                displayLedger(transactions, scanner, transaction, transaction2);

            } else if (userChoice.equalsIgnoreCase("x")) {

                break;

            } else
                System.out.println("\nInvalid Input, Try Again!\n");

        }
    }

    public static void addDeposit(Scanner scanner, Transaction transaction, ArrayList<Transaction> transactions){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))){

            System.out.println("\nEnter following information regarding current deposit:\n");

            System.out.print("What is the date? (MM/dd/yyyy):");

            String date = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            //transaction.setDate(localDate);

            System.out.print("What is the time? (HH:mm:ss): ");

            String time = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            //transaction.setTime(localTime);

            System.out.print("What is the description?: ");

            String description = scanner.nextLine();

            //transaction.setDescription(description);

            System.out.print("Who is the vendor?: ");

            String vendor = scanner.nextLine();

            //transaction.setVendor(vendor);

            System.out.print("What is the amount you wish to deposit?: ");

            double amount = scanner.nextDouble();

            //transaction.setAmount(amount);

           // transactions.add(new Transaction(transaction.getDate(), transaction.getTime(),
                   //transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));


            scanner.nextLine();

            bufferedWriter.newLine();

            // "Date | Time | Description | Vendor | Amount" //

            bufferedWriter.write(localDate + " | " + localTime + " | " + description + " | " + vendor + " | " + amount);


        } catch (Exception e) {

            System.out.println("Could Not Write To File!");

        }


    }

    public static void addPayment(Scanner scanner, Transaction transaction2, ArrayList<Transaction> transactions){


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))){

            System.out.println("\nEnter following information regarding current withdraw:\n");

            System.out.print("What is the date? (MM/dd/yyyy):");

            String date = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            //transaction2.setDate(localDate);

            System.out.print("What is the time? (HH:mm:ss): ");

            String time = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            //transaction2.setTime(localTime);

            System.out.print("What is the description?: ");

            String description = scanner.nextLine();

            //transaction2.setDescription(description);

            System.out.print("Who is the vendor?: ");

            String vendor = scanner.nextLine();

            //transaction2.setVendor(vendor);

            System.out.print("What is the amount you wish to withdraw?: ");

            double amount = scanner.nextDouble();

            //transaction2.setAmount(amount);

            //transactions.add(new Transaction(transaction2.getDate(), transaction2.getTime(),
                //    transaction2.getDescription(), transaction2.getVendor(), transaction2.getAmount()));

            scanner.nextLine();

            bufferedWriter.newLine();

            // "Date | Time | Description | Vendor | Amount" //

            bufferedWriter.write(localDate + " | " + localTime + " | " + description + " | " + vendor + " | " + amount);


        } catch (Exception e) {

            System.out.println("Could Not Write To File!");

        }


    }

    public static ArrayList<Transaction> loadTransactions(ArrayList<Transaction> transactions){

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))){

            String line;

            while((line = bufferedReader.readLine()) != null){


                String [] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);

                LocalTime time = LocalTime.parse(parts[1]);

                String [] parts2 = line.split("\\|");

                String description = parts2[2];

                String vendor = parts2[3];

                double amount  = Double.parseDouble(parts2[4]);

                transactions.add(new Transaction(date, time, description, vendor, amount));


            }

        } catch (Exception e) {

            System.out.println("Could Not Read File!");
        }

        return transactions;


    }

    public static void displayLedger(ArrayList<Transaction> transactions, Scanner scanner, Transaction transaction, Transaction transaction2){

        System.out.println("\n-- Ledger Screen --\n");

        System.out.println("A - All");
        System.out.println("D - Deposits");
        System.out.println("P - Payments");
        System.out.println("R - Reports");
        System.out.println("H - Home");


        System.out.print("\nChose one of the above options: ");

        String userChoice = scanner.nextLine();

        if (userChoice.equalsIgnoreCase("a")){

            displayAllEntries(transactions);

        } else if (userChoice.equalsIgnoreCase("d")){

            displayDeposits(transactions, transaction);


        } else if (userChoice.equalsIgnoreCase("p")){


        } else if (userChoice.equalsIgnoreCase("r")) {


        } else if (userChoice.equalsIgnoreCase("h")) {

            displayHomeScreen(scanner, transactions, transaction, transaction2);

        } else {
            System.out.println("Invalid Input, Try Again!");
        }

    }


    public static void displayAllEntries(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            System.out.println(allEntries.get(i));
        }

    }


    public static void displayDeposits(ArrayList<Transaction> transactions, Transaction transaction){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(transaction.getAmount() > 0); {

                System.out.println("Date: " + transaction.getDate() + "Amount: "  + transaction.getAmount());

            }


        }


    }

    public static void displayPayments(){


    }

    public static void reportsScreen(){


    }



}
