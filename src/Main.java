import DAOs.IncomeDaoInterface;
import DAOs.MySqlIncomeDao;
import DTOs.Income;
import Exceptions.DaoException;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao();

        try {
            System.out.println("Executing findAllIncome()");
            List<Income> incomeList = IIncomeDao.findAllIncome();

            if(incomeList.isEmpty()) {
                System.out.println("Income table is empty! Please add some data first.");
            }
            else {
                for(Income incomeRow : incomeList) {
                    System.out.println(incomeRow.toString());
                }
            }
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
