import DAOs.IncomeDaoInterface;
import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DAOs.MySqlIncomeDao;

import DTOs.Income;
import DTOs.Expense;

import Exceptions.DaoException;

import java.util.Date;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();
        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        try {
            // findAllExpenses()...
            System.out.println("Executing findAllExpenses()...");
            double totalSpent = 0;

            List<Expense> expenseList = IExpenseDao.findAllExpenses();

            if(expenseList.isEmpty()) {
                System.out.println("Expense table is empty! Please add some data first.");
            }
            else {
                for(Expense expenseRow : expenseList) {
                    totalSpent += expenseRow.getAmount();
                    System.out.println(expenseRow.toString());
                }
            }

            System.out.println("Total money spent is: €" +totalSpent);

            // addExpense()...
            System.out.println("Executing addExpense()...");
            String title = "testTitle";
            String category = "testCategory";
            double amount = 10.10;
            String incurred = "2025-01-14";

            IExpenseDao.addExpense(title, category, amount, incurred);
            System.out.println("Successfully added details...");

            if(expenseList.isEmpty()) {
                System.out.println("Expense table is empty! Please add some data first.");
            }
            else {
                for(Expense expenseRow : expenseList) {
                    System.out.println(expenseRow.toString());
                }
            }

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

            // addIncome()...
            System.out.println("Executing addIncome()...");
            String incTitle = "testTitle";
            double incAmount = 10.10;
            String earned = "2025-01-14";

            IIncomeDao.addIncome(incTitle, incAmount, earned);
            System.out.println("Successfully added details...");

            if(incomeList.isEmpty()) {
                System.out.println("Income table is empty! Please add some data first.");
            }
            else {
                for(Income incomeRow : incomeList) {
                    System.out.println(incomeRow.toString());
                }
            }
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
