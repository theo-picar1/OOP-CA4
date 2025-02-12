import DAOs.IncomeDaoInterface;
import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DAOs.MySqlIncomeDao;

import DTOs.Income;
import DTOs.Expense;

import Exceptions.DaoException;

import java.util.Scanner;

import java.util.List;
public class Main {
    static IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();
    static ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

    static Scanner sc = new Scanner(System.in);

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
                "End application"
        };

        Methods.menuOptions(options);

        int choice = Methods.validateRange(1, 7);

        if(choice == 1 || choice == 4) {
            displayATable(choice);
        }
        else if(choice == 2 || choice == 5) {
            addRowToTable(choice);
        }
        else if(choice == 3 || choice == 6) {
            deleteRowFromTable(choice);
        }
        else {
            System.out.println("Disconnecting from the database...\nDone! Goodbye");
        }
    }

    // SELECT * FROM tableName
    public static void displayATable(int choice) {
        try {
            if (choice == 1) {
                // findAllExpenses()...
                System.out.println("Executing findAllExpenses()...");
                double totalSpent = 0;

                List<Expense> expenseList = IExpenseDao.findAllExpenses();

                if (expenseList.isEmpty()) {
                    System.out.println("Expense table is empty! Please add some data first.");
                } else {
                    for (Expense expenseRow : expenseList) {
                        totalSpent += expenseRow.getAmount();
                        System.out.println(expenseRow.toString());
                    }
                }

                System.out.println("Total money spent is: €" + totalSpent);
            }
            else {
                // findAllIncome()...
                System.out.println("\nExecuting findAllIncome()...");
                List<Income> incomeList = IIncomeDao.findAllIncome();
                double totalEarned = 0;

                if(incomeList.isEmpty()) {
                    System.out.println("Income table is empty! Please add some data first.");
                }
                else {
                    for(Income incomeRow : incomeList) {
                        totalEarned += incomeRow.getAmount();
                        System.out.println(incomeRow.toString());
                    }
                }

                System.out.println("Total money earned is: €" +totalEarned);
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
                String title, category, incurred;
                double amount;

                System.out.println("Please enter your new title...");
                title = Methods.validateString();

                System.out.println("Please enter your new category...");
                category = Methods.validateString();

                System.out.println("Please enter the amount you spent");
                amount = Methods.validateAmount();

                System.out.println("Finally, please enter the date you spent this (YYYY-MM-DD)");
                incurred = Methods.validateDate();

                IExpenseDao.addExpense(title, category, amount, incurred);
                System.out.println("Successfully added details...");
            }
            else {
                // addIncome()...
                String title, earned;
                double amount;

                System.out.println("Please enter your new title...");
                title = Methods.validateString();

                System.out.println("Please enter the amount you spent");
                amount = Methods.validateAmount();

                System.out.println("Finally, please enter the date you spent this (YYYY-MM-DD)");
                earned = Methods.validateDate();

                IIncomeDao.addIncome(title, amount, earned);
                System.out.println("Successfully added details...");
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
            if(choice == 3) {
                // deleteExpenseById()
                System.out.println("\nPlease enter the row ID you would like to delete:");
                int id = sc.nextInt();

                IExpenseDao.deleteExpenseById(id);
                System.out.println("Successfully deleted row with ID " +id);
            }
            else {
                // deleteIncomeById()
                System.out.println("\nPlease enter the row ID you would like to delete:");
                int id = Methods.validateInt();

                IIncomeDao.deleteIncomeById(id);
                System.out.println("Successfully deleted row with ID " +id);
            }

            System.out.println();
            menu();
        }
        catch(DaoException e) {
            e.printStackTrace();
        }
    }
}
