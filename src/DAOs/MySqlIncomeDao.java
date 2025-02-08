package DAOs;

import DTOs.Income;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

                Income income = new Income(id, title, amount, dateEarned);
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
}
