package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

public interface IncomeDaoInterface {
    List<Income> findAllIncome() throws DaoException;

    int addIncome(String title, double amount, String earned) throws DaoException;

    int deleteIncomeById(int id) throws DaoException;

    double getTotalByMonth(int id) throws DaoException;
}
