package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;

public interface IncomeDaoInterface {
    List<Income> findAllIncome() throws DaoException;
}
