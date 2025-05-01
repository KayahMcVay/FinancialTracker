import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Transaction> transactions = new ArrayList<>();

        displayHomeScreen(scanner, transactions);

    }

    public static void displayHomeScreen(Scanner scanner, ArrayList<Transaction> transactions){

        while(true) {

            System.out.println("\n-- Home Screen --\n");

            System.out.println("D - Add Deposit");
            System.out.println("P - Make Payment (Debit)");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");

            System.out.print("\nChose one of the above options: ");

            String userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase("d")) {

                addDeposit(scanner);

            } else if (userChoice.equalsIgnoreCase("p")) {

                addPayment(scanner);

            } else if (userChoice.equalsIgnoreCase("l")) {

                displayLedger(transactions, scanner);

            } else if (userChoice.equalsIgnoreCase("x")) {

                break;

            } else
                System.out.println("\nInvalid Input, Try Again!\n");

        }
    }

    public static void addDeposit(Scanner scanner){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))){

            System.out.println("\nEnter following information regarding current deposit:\n");

            System.out.print("What is the date? (MM/dd/yyyy):");

            String date = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            System.out.print("What is the time? (HH:mm:ss): ");

            String time = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            System.out.print("What is the description?: ");

            String description = scanner.nextLine();

            System.out.print("Who is the vendor?: ");

            String vendor = scanner.nextLine();

            System.out.print("What is the amount you wish to deposit?: ");

            double amount = scanner.nextDouble();

            scanner.nextLine();

            bufferedWriter.newLine();

            // "Date | Time | Description | Vendor | Amount" //

            bufferedWriter.write(localDate + "|" + localTime + "|" + description + "|" + vendor + "|" + amount);


        } catch (Exception e) {

            System.out.println("Could Not Write To File!");

        }


    }

    public static void addPayment(Scanner scanner){


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))){

            System.out.println("\nEnter following information regarding current withdraw:\n");

            System.out.print("What is the date? (MM/dd/yyyy):");

            String date = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            System.out.print("What is the time? (HH:mm:ss): ");

            String time = scanner.nextLine();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            System.out.print("What is the description?: ");

            String description = scanner.nextLine();

            System.out.print("Who is the vendor?: ");

            String vendor = scanner.nextLine();

            System.out.print("What is the amount you wish to withdraw?: ");

            double amount = scanner.nextDouble();

            scanner.nextLine();

            bufferedWriter.newLine();

            // "Date | Time | Description | Vendor | Amount" //

            bufferedWriter.write(localDate + "|" + localTime + "|" + description + "|" + vendor + "|" + amount);


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

                String description = parts[2];

                String vendor = parts[3];

                double amount  = Double.parseDouble(parts[4]);

                transactions.add(new Transaction(date, time, description, vendor, amount));

            }

        } catch (Exception e) {

            System.out.println("Could Not Read File!");
        }

        return transactions;

    }

    public static void displayLedger(ArrayList<Transaction> transactions, Scanner scanner){

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

            displayDeposits(transactions);

        } else if (userChoice.equalsIgnoreCase("p")){

            displayPayments(transactions);

        } else if (userChoice.equalsIgnoreCase("r")) {

            displayReportsScreen(scanner, transactions);

        } else if (userChoice.equalsIgnoreCase("h")) {

            displayHomeScreen(scanner, transactions);

        } else {
            System.out.println("Invalid Input, Try Again!");
        }

    }


    public static void displayAllEntries(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            System.out.println(transactions.get(i));
        }

    }


    public static void displayDeposits(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(transactions.get(i).getAmount() > 0){

                System.out.println(transactions.get(i));

            }
        }
    }

    public static void displayPayments(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(transactions.get(i).getAmount() < 0){

                System.out.println(transactions.get(i));

            }
        }
    }

    public static void displayReportsScreen(Scanner scanner, ArrayList<Transaction> transactions){


        System.out.println("\n-- Reports Screen --\n");

        System.out.println("1 - Month To Date");
        System.out.println("2 - Previous Month");
        System.out.println("3 - Year To Date");
        System.out.println("4 - Previous Year");
        System.out.println("5 - Search By Vendor");
        System.out.println("0 - Back");


        System.out.print("\nChose one of the above options:\n");

        String userChoice = scanner.nextLine();

        switch (userChoice){

            case "1":

                filterTransactionByMonthToDate(transactions);
                break;

            case "2":

                filterTransactionByPreviousMonth(transactions);

                break;

            case "3":

                filterTransactionByYearToDate(transactions);

                break;

            case "4":

                filterTransactionByPreviousYear(transactions);

                break;

            case "5":

                searchByVendor();

                break;

            case "0":

                displayReportsScreen(scanner, transactions);

            default:
                System.out.println("Invalid Input, Try Again!");
                displayReportsScreen(scanner, transactions);

        }
    }

    public static void filterTransactionByMonthToDate(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate currentDate = LocalDate.now();

            int currentYear = currentDate.getYear();

            int allEntireYears = transactions.get(i).getDate().getYear();

            if(transactions.get(i).getDate().getMonth().equals(currentDate.getMonth()) && allEntireYears == currentYear){

                System.out.println(transactions.get(i));

            }
        }

    }

    public static void filterTransactionByPreviousMonth(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate currentDate = LocalDate.now();

            int currentYear = currentDate.getYear();

            LocalDate earlier = currentDate.minusMonths(1);

            int allEntireYears = transactions.get(i).getDate().getYear();

            if(transactions.get(i).getDate().getMonth().equals(earlier.getMonth()) && allEntireYears == currentYear){

                System.out.println(transactions.get(i));

            }
        }
    }

    public static void filterTransactionByYearToDate(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate currentDate = LocalDate.now();

            int currentYear = currentDate.getYear();

            int allEntireYears = transactions.get(i).getDate().getYear();

            if(allEntireYears == currentYear){

                System.out.println(transactions.get(i));

            }
        }


    }

    public static void filterTransactionByPreviousYear(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate currentDate = LocalDate.now();

            int currentYear = currentDate.getYear();

            LocalDate earlier = currentDate.minusYears(1);

            int allEntireYears = transactions.get(i).getDate().getYear();

            if(allEntireYears == earlier.getYear()){

                System.out.println(transactions.get(i));

            }
        }
    }

    public static void searchByVendor(){


    }
}
