import DAOs.IncomeDaoInterface;
import DAOs.ExpenseDaoInterface;
import DAOs.MySqlExpenseDao;
import DAOs.MySqlIncomeDao;

import DTOs.Income;
import DTOs.Expense;

import Exceptions.DaoException;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();
        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        try {
            //Expense
            System.out.println("Executing findAllExpenses()...");
            List<Expense> expenseList = IExpenseDao.findAllExpenses();
            double totalSpent = 0;

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

            // Income
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
        catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
