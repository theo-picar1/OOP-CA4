package DAOs;

import DTOs.Expense;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface{
    @Override
    public List<Expense> findAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenseList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM Expenses";
            preparedStatement = connection.prepareStatement(query);

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
}
