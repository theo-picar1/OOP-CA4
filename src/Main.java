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

            if(expenseList.isEmpty()) {
                System.out.println("Expense table is empty! Please add some data first.");
            }
            else {
                for(Expense expenseRow : expenseList) {
                    System.out.println(expenseRow.toString());
                }
            }

            // Income
            System.out.println("\nExecuting findAllIncome()...");
            List<Income> incomeList = IIncomeDao.findAllIncome();

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
