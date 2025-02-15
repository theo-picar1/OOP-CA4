import DTOs.Expense;
import DTOs.Income;

import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class Methods {
    static Scanner sc = new Scanner(System.in);

    public static void menuOptions(String[] options) {
        for (int i = 1; i <= options.length; i++) {
            System.out.println(i + ". " + options[i - 1]);
        }
    }

    public static int validateRange(int min, int max) {
        int input = 0;

        while (true) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();

                if (input < min || input > max) {
                    System.out.println("Please enter a valid option (" + min + "-" + max + ")");
                } else {
                    break;
                }
            } else {
                System.out.println("Please enter only integer values");
                sc.next();
            }
        }

        return input;
    }

    public static int validateInt() {
        int input;

        while (true) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();

                return input;
            } else {
                System.out.println("Please enter only integer values");
                sc.next();
            }
        }
    }

    public static double validateAmount() {
        double input;

        while (true) {
            if (sc.hasNextDouble()) {
                input = sc.nextDouble();

                // https:www.baeldung.com/java-double-round-two-decimal-places
                return Math.floor(input * 100) / 100;
            } else {
                System.out.println("Please enter only double values");
                sc.next();
            }
        }
    }

    public static String validateString() {
        while (true) {
            String input = sc.next();

            if (input.matches("[a-zA-Z]{1,20}")) {
                return input;
            } else {
                System.out.println("Data cannot be longer than 20 characters or have any numbers (a-z / A-Z only)");
            }
        }
    }

    public static String validateDate() {
        while (true) {
            String input = sc.next();

            https:
//stackoverflow.com/questions/22061723/regex-date-validation-for-yyyy-mm-dd
            if (input.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
                return input;
            } else {
                System.out.println("Invalid date");
            }
        }
    }

    public static void rowsAffectedMessage(int rowsAffected, String successful) {
        if (rowsAffected > 0) {
            System.out.println(successful);
        } else {
            System.err.println("0 rows affected in table");
        }
    }

    public static String getMonth(int num) {
        String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December",
        };

        return months[num - 1];
    }

    public static Expense expenseFields() {
        String title, category, incurred;
        double amount;

        System.out.println("Please enter your new title...");
        title = Methods.validateString();

        System.out.println("Please enter the amount you spent");
        amount = Methods.validateAmount();

        System.out.println("Please enter the date you spent this (YYYY-MM-DD)");
        incurred = Methods.validateDate();

        System.out.println("Finally, please enter your new category...");
        category = Methods.validateString();

        return new Expense(title, category, amount, incurred);
    }

    public static Income incomeFields() {
        String title, incurred;
        double amount;

        System.out.println("Please enter your new title...");
        title = Methods.validateString();

        System.out.println("Please enter the amount you spent");
        amount = Methods.validateAmount();

        System.out.println("Finally, please enter the date you spent this (YYYY-MM-DD)");
        incurred = Methods.validateDate();

        return new Income(title, amount, incurred);
    }
}
