# Financial Tracker

## Project Description

This Java console application is a simple financial tracker for managing personal or small business transactions. 
It allows users to add deposits (income) and payments (expenses) by entering transaction details such as date, time, 
description, vendor, and amount. All entries are stored in a CSV file for persistence and future access. Users can 
view their full ledger or filter transactions to see only deposits or only payments. The application also provides 
basic reporting features, including filters for month-to-date, previous month, year-to-date, previous year, and
vendor-specific searches.

Its primary purpose is to give users a lightweight, offline way to monitor and analyze their finances without relying
on complex accounting software. The intended users are individuals, freelancers, or small business owners who are 
comfortable using a command-line interface. The system validates inputs and handles navigation through a menu-driven 
interface, ensuring ease of use. It solves the problem of financial tracking by offering quick, local data entry and 
retrieval in a structured format. Overall, it provides a clear and practical tool for users looking to manage their 
finances in a straightforward, no-frills environment.

## User Stories

- As a user, I want to add deposit entries with details, so that I can track my income.  
- As a user, I want to add payment (debit) entries, so that I can track my spending.
- As a user, I want to view a ledger that shows all transactions, so that I can review my financial activity.
- As a user, I want to view only deposits or only payments, so that I can filter my transactions.
- As a user, I want to generate reports by date or vendor, so I can analyze my financial patterns.
- As a user, I want to return to the home screen or exit the application at any time, for navigation flexibility.

## Setup

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

## Technologies Used

- Java: 17
- java.io.*; 
- java.time.LocalDate; 
- java.time.LocalTime; 
- java.time.format.DateTimeFormatter; 
- java.util.ArrayList; 
- java.util.Scanner;

## Demo

![Ex - Step 1](screenshot.png)
![Ex - Step 2](screenshot1.png)
![Ex - Step 3](screenshot2.png)
![Ex - Step 4](screenshot3.png)
![Ex - Step 5](screenshot4.png)
![Ex - Step 6](screenshot5.png)
![Ex - Step 7](screenshot6.png)
![Ex - Step 8](screenshot7.png)
![Ex - Step 9](screenshot8.png)

## Future Work

- Authentication System
- Transaction Categories
- Summary Dashboard 
- GUI Interface
- Recurring Transactions
- Multi-Currency & Localization Support

## Resources

- [GeeksforGeeks](https://www.geeksforgeeks.org/)
- [Stack Overflow](https://stackoverflow.com/)
- [W3schools](https://www.w3schools.com/)
- [ChatGPT](https://chatgpt.com/)

## Thanks

- Thank you to Raymond Maroun for continuous support and guidance!