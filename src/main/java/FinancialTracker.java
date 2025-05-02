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
            System.out.println("\n---------------------------------------------------------------------------------");

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

            System.out.print("What is the date? (MM/dd/yyyy): ");
            String date = scanner.nextLine().trim();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            System.out.print("What is the time? (HH:mm:ss): ");
            String time = scanner.nextLine().trim();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            System.out.print("What is the description?: ");
            String description = scanner.nextLine().trim();

            System.out.print("Who is the vendor?: ");
            String vendor = scanner.nextLine().trim();

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

            System.out.print("What is the date? (MM/dd/yyyy): ");
            String date = scanner.nextLine().trim();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

            System.out.print("What is the time? (HH:mm:ss): ");
            String time = scanner.nextLine().trim();

            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter2);

            System.out.print("What is the description?: ");
            String description = scanner.nextLine().trim();

            System.out.print("Who is the vendor?: ");
            String vendor = scanner.nextLine().trim();

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

        transactions.clear(); // Clear Old Entries //

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

         switch (userChoice.toUpperCase()) {

             case "A":

                 displayAllEntries(transactions);
                 break;

             case "D":

                 displayDeposits(transactions);
                 break;

             case "P":

                 displayPayments(transactions);
                 break;

             case "R":

                 displayReportsScreen(scanner, transactions);
                 break;

             case "H":

                 return; // go back to Home Screen //

             default:

                 System.out.println("Invalid Input, Try Again!");
         }
    }


    public static void displayAllEntries(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            System.out.println(allEntries.get(i));
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }


    public static void displayDeposits(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(allEntries.get(i).getAmount() > 0){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void displayPayments(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(allEntries.get(i).getAmount() < 0){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void displayReportsScreen(Scanner scanner, ArrayList<Transaction> transactions){

        System.out.println("\n-- Reports Screen --\n");

        System.out.println("1 - Month To Date");
        System.out.println("2 - Previous Month");
        System.out.println("3 - Year To Date");
        System.out.println("4 - Previous Year");
        System.out.println("5 - Search By Vendor");
        System.out.println("0 - Back");

        System.out.print("\nChose one of the above options: ");
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

                searchByVendor(scanner, transactions);
                break;

            case "0":

                return; // back to Ledger Screen //

            default:

                System.out.println("Invalid Input, Try Again!");
        }
    }

    public static void filterTransactionByMonthToDate(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        LocalDate currentDate = LocalDate.now();
        LocalDate monthStartDate = currentDate.withDayOfMonth(1);
        LocalDate monthEndDate = LocalDate.now();
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate entryDate = allEntries.get(i).getDate();

            if(!entryDate.isBefore(monthStartDate) && !entryDate.isAfter(monthEndDate)){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void filterTransactionByPreviousMonth(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonth = currentDate.minusMonths(1);
        LocalDate previousMonthStartDate = previousMonth.withDayOfMonth(1);
        LocalDate previousMonthEndDate = previousMonth.withDayOfMonth(previousMonth.lengthOfMonth());
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate entryDate = allEntries.get(i).getDate();

            if(!entryDate.isBefore(previousMonthStartDate) && !entryDate.isAfter(previousMonthEndDate)){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void filterTransactionByYearToDate(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        LocalDate currentDate = LocalDate.now();
        LocalDate yearStartDate = currentDate.withDayOfYear(1);
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate entryDate = allEntries.get(i).getDate();

            if(!entryDate.isBefore(yearStartDate) && !entryDate.isAfter(currentDate)){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void filterTransactionByPreviousYear(ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        int previousYear = LocalDate.now().getYear() -1;
        LocalDate previousYearStartDate = LocalDate.of(previousYear, 1, 1);
        LocalDate previousYearEndDate = LocalDate.of(previousYear, 12,31);
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            LocalDate entryDate = allEntries.get(i).getDate();

            if(!entryDate.isBefore(previousYearStartDate) && !entryDate.isAfter(previousYearEndDate)){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }

    public static void searchByVendor(Scanner scanner, ArrayList<Transaction> transactions){

        ArrayList<Transaction> allEntries = loadTransactions(transactions);

        System.out.print("\nEnter Vendor Name: ");
        String vendor = scanner.nextLine().trim();
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        for (int i = allEntries.size() - 1; i >= 0; i--) {

            if(allEntries.get(i).getVendor().equalsIgnoreCase(vendor)){

                System.out.println(allEntries.get(i));
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------\n");
    }
}