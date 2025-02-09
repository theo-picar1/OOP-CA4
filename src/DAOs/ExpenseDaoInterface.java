package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.util.List;

public interface ExpenseDaoInterface {
    List<Expense> findAllExpenses() throws DaoException;
}
