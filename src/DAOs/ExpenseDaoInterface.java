package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.util.List;

public interface ExpenseDaoInterface {
    List<Expense> findAllExpenses() throws DaoException;

    int addExpense(String title, String category, double amount, String incurred) throws DaoException;

    int deleteExpenseById(int id) throws DaoException;
}
