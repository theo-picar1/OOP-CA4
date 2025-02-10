package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.util.List;

public interface ExpenseDaoInterface {
    List<Expense> findAllExpenses() throws DaoException;

    void addExpense(String title, String category, double amount, String incurred) throws DaoException;
}
