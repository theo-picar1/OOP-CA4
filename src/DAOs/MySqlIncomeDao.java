package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface {

    @Override
    public List<Income> findAllIncome() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomeList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM Income";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("earned");

                Income income = new Income(id, title, amount, dateEarned.toString());
                incomeList.add(income);
            }
        }
        catch(SQLException e) {
            throw new DaoException("findAllIncome() error! " + e.getMessage());
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("findAllIncome() error!" + e.getMessage());
            }
        }

        return incomeList;
    }

    @Override
    public int addIncome(String title, double amount, String earned) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected;

        try {
            connection = this.getConnection();

            String addQuery = "INSERT INTO Income VALUES(null, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(addQuery);

            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDate(3, Date.valueOf(earned));

            rowsAffected = preparedStatement.executeUpdate();

        }
        catch(SQLException e) {
            throw new DaoException("addIncome() error! " + e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("addIncome() error!" + e.getMessage());
            }
        }

        return rowsAffected;
    }

    @Override
    public int deleteIncomeById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected;

        try {
            connection = this.getConnection();

            String deleteQuery = "DELETE FROM Income WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, id);

            rowsAffected = preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DaoException("deleteIncomeById() error! " + e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("deleteIncomeById() error!" + e.getMessage());
            }
        }

        return rowsAffected;
    }

    @Override
    public double getTotalByMonth(int month) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        double total = 0.0;

        try {
            connection = this.getConnection();

            // https://stackoverflow.com/questions/851236/how-to-write-a-where-clause-to-find-all-records-in-a-specific-month
            String retrieveQuery = "SELECT SUM(amount) FROM Income WHERE MONTH(earned) = ?";
            preparedStatement = connection.prepareStatement(retrieveQuery);

            preparedStatement.setInt(1, month);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                total = resultSet.getDouble(1);
            }
        }
        catch(SQLException e) {
            throw new DaoException("getTotalByMonth() error!" +e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("deleteExpenseById() error!" + e.getMessage());
            }
        }

        return total;
    }
}
