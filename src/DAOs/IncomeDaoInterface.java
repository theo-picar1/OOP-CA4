package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

public interface IncomeDaoInterface {
    List<Income> findAllIncome() throws DaoException;

    void addIncome(String title, double amount, String earned) throws DaoException;
}
