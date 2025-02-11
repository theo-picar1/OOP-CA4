package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface{
    @Override
    public List<Expense> findAllExpenses() throws DaoException {
        List<Expense> expenseList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection();

            String selectAllQuery = "SELECT * FROM Expenses";
            preparedStatement = connection.prepareStatement(selectAllQuery);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("incurred");

                Expense income = new Expense(id, title, category, amount, dateEarned);
                expenseList.add(income);
            }
        }
        catch(SQLException e) {
            throw new DaoException("findAllExpenses() error! " + e.getMessage());
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
                throw new DaoException("findAllExpenses() error!" + e.getMessage());
            }
        }

        return expenseList;
    }

    @Override
    public void addExpense(String title, String category, double amount, String incurred) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String addQuery = "INSERT INTO Expenses VALUES(null, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(addQuery);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDate(4, Date.valueOf(incurred));

            preparedStatement.executeUpdate();

        }
        catch(SQLException e) {
            throw new DaoException("addExpense() error! " + e.getMessage());
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
                throw new DaoException("addExpense() error!" + e.getMessage());
            }
        }
    }

    @Override
    public void deleteExpenseById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String deleteQuery = "DELETE FROM Expenses WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DaoException("deleteExpenseById() error! " + e.getMessage());
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
    }
}
