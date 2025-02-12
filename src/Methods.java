import java.util.Scanner;

public class Methods {
    static Scanner sc = new Scanner(System.in);
    
    public static void menuOptions(String[] options) {
        for(int i = 1; i <= options.length; i++) {
            System.out.println(i + ". " + options[i-1]);
        }
    }

    public static int validateRange(int min, int max) {
        int input = 0;

        while(true) {
            if(sc.hasNextInt()) {
                input = sc.nextInt();

                if(input < min || input > max) {
                    System.out.println("Please enter a valid option (" +min+ "-" +max+ ")");
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Please enter only integer values");
                sc.next();
            }
        }

        return input;
    }

    public static int validateInt() {
        int input;

        while(true) {
            if(sc.hasNextInt()) {
                input = sc.nextInt();

                return input;
            }

            else {
                System.out.println("Please enter only integer values");
                sc.next();
            }
        }
    }

    public static double validateAmount() {
        double input;

        while(true) {
            if(sc.hasNextDouble()) {
                input = sc.nextDouble();

                https://www.baeldung.com/java-double-round-two-decimal-places
                return Math.floor(input * 100) / 100;
            }
            else {
                System.out.println("Please enter only double values");
                sc.next();
            }
        }
    }

    public static String validateString() {
        while(true) {
            String input = sc.next();

            if(input.matches("[a-zA-Z]{1,20}")) {
                return input;
            }
            else {
                System.out.println("Data cannot be longer than 20 characters or have any numbers (a-z / A-Z only)");
            }
        }
    }

    public static String validateDate() {
        while(true) {
            String input = sc.next();

            https://stackoverflow.com/questions/22061723/regex-date-validation-for-yyyy-mm-dd
            if(input.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
                return input;
            }
            else {
                System.out.println("Invalid date");
            }
        }
    }

    public static void rowsAffectedMessage(int rowsAffected, String successful) {
        if(rowsAffected > 0) {
            System.out.println(successful);
        }
        else {
            System.err.println("0 rows affected in table");
        }
    }
}
