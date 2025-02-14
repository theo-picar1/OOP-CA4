import DAOs.IncomeDaoInterface;
import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DAOs.MySqlIncomeDao;

import DTOs.Income;
import DTOs.Expense;

import Exceptions.DaoException;

import java.util.Scanner;
import java.util.List;

import java.sql.Date;

public class Main {
    static IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();
    static ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

    static Scanner sc = new Scanner(System.in);

//    static double totalSpent = 0;
//    static double totalEarned = 0;

    public static void main(String[] args) {
        System.out.println("EXPENSE & INCOME DATABASE (OOP CA4)");
        System.out.println("Please choose one of the following options...");

        menu();
    }

    public static void menu() {
        String[] options = {
            "Display 'Expenses' table",
            "Add expense",
            "Delete expense",
            "Display 'Income' table",
            "Add income",
            "Delete income",
            "Total income in month",
            "Total expense in month",
            "End application"
        };

        Methods.menuOptions(options);

        int choice = Methods.validateRange(1, 9);

        if(choice == 1 || choice == 4) {
            displayATable(choice);
        }
        else if(choice == 2 || choice == 5) {
            addRowToTable(choice);
        }
        else if(choice == 3 || choice == 6) {
            deleteRowFromTable(choice);
        }
        else if(choice == 7 || choice == 8) {
            displayTotalFromMonth(choice);
        }
        else {
            System.out.println("Disconnecting from the database...\nDone! Goodbye");
        }
    }

    // SELECT * FROM tableName
    public static void displayATable(int choice) {
        try {
            double totalSpent = 0;
            double totalEarned = 0;

            if (choice == 1) {
                // findAllExpenses()...
                System.out.println("Executing findAllExpenses()...");

                List<Expense> expenseList = IExpenseDao.findAllExpenses();

                if (expenseList.isEmpty()) {
                    System.out.println("Expense table is empty! Please add some data first.");
                } else {
                    for (Expense expenseRow : expenseList) {
                        totalSpent += expenseRow.getAmount();
                        System.out.println("{" +expenseRow.toString()+ "}");
                    }
                }

                System.out.println("Total money spent is: €" + Math.floor(totalSpent * 100) / 100);
            }
            else {
                // findAllIncome()...
                System.out.println("\nExecuting findAllIncome()...");
                List<Income> incomeList = IIncomeDao.findAllIncome();

                if(incomeList.isEmpty()) {
                    System.out.println("Income table is empty! Please add some data first.");
                }
                else {
                    for(Income incomeRow : incomeList) {
                        totalEarned += incomeRow.getAmount();
                        System.out.println("{" +incomeRow.toString()+ "}");
                    }
                }

                System.out.println("Total money earned is: €" +Math.floor(totalEarned * 100) / 100);
            }

            System.out.println();
            menu();
        }
        catch(DaoException e) {
            e.printStackTrace();
        }
    }

    // INSERT INTO tableName VALUES (x, y, z)
    public static void addRowToTable(int choice) {
        try {
            if(choice == 2) {
                // addExpense()...
                Expense expense = Methods.expenseFields();

                int rowsAffected = IExpenseDao.addExpense(expense.getTitle(), expense.getCategory(), expense.getAmount(), expense.getDate());

                Methods.rowsAffectedMessage(rowsAffected, "Successfully added details...");
            }
            else {
                // addIncome()...
                Income income = Methods.incomeFields();

                int rowsAffected = IIncomeDao.addIncome(income.getTitle(), income.getAmount(), income.getDate());

                Methods.rowsAffectedMessage(rowsAffected, "Successfully added details...");
            }

            System.out.println();
            menu();
        }
        catch(DaoException e) {
            e.printStackTrace();
        }
    }

    // DELETE FROM tableName WHERE ID = x
    public static void deleteRowFromTable(int choice) {
        try {
            System.out.println("\nPlease enter the row ID you would like to delete:");
            int id = Methods.validateInt();

            if(choice == 3) {
                // deleteExpenseById()
                int rowsAffected = IExpenseDao.deleteExpenseById(id);

                Methods.rowsAffectedMessage(rowsAffected, "Successfully deleted row with provided id");
            }
            else {
                // deleteIncomeById()
                int rowsAffected = IIncomeDao.deleteIncomeById(id);

                Methods.rowsAffectedMessage(rowsAffected, "Successfully deleted row with provided id");
            }

            System.out.println();
            menu();
        }
        catch(DaoException e) {
            e.printStackTrace();
        }
    }

    public static void displayTotalFromMonth(int choice) {
        try {
            System.out.println("Please enter your month as a number (e.g. January = 1)");
            int month = Methods.validateRange(1, 12);

            String monthString = Methods.getMonth(month);

            double totalIncome;
            double totalExpense;

            if (choice == 7) {
                // Income
                totalExpense = IIncomeDao.getTotalByMonth(month);

                System.out.println("You spent a total of € " +totalExpense+ " in " +monthString);
            }
            else {
                // Expense
                totalIncome = IExpenseDao.getTotalByMonth(month);

                System.out.println("You spent a total of € " +totalIncome+ " in " +monthString);
            }

            menu();
        }
        catch(DaoException e) {
            e.printStackTrace();
        }
    }
}
