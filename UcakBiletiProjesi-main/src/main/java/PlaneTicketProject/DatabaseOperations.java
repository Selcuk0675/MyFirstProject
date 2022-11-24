package PlaneTicketProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DatabaseOperations {

    static Scanner input = new Scanner(System.in);
    public static Map<String, Customer> customerInformation = new HashMap<>();
    public static Map<String, String> emailsAndPasswords = new HashMap<>();


    public static Set<String> emailSet = emailsAndPasswords.keySet();

    public static void checkUserEmailAndPassword(String userEmail) {

        int count = 0;

        if (emailSet.contains(userEmail)) {
            String correctPsw = emailsAndPasswords.get(userEmail);
            Customer customer = customerInformation.get(userEmail);

            System.out.println("Please enter your password");
            String inputPsw = input.next();

            do {
                if (inputPsw.equals(correctPsw)) {
                    UserDashboard.accountMenu(customer);
                    break;
                } else {
                    System.out.println("Incorrect password. Please enter your password again.");
                    inputPsw = input.next();
                    count++;
                }
            } while (count < 4);

            LoginOperations.openLoginScreen();

        } else {
            System.out.println("User not found. Please press '1' if you want to re-type your email, or please press '2' if you want to\n" +
                    " be redirected to Sign Up page...");
            String userInp = input.next();

            do {
                if (userInp.equals("1")) {
                    LoginOperations.SignIn();
                    break;
                } else if (userInp.equals("2")) {
                    LoginOperations.SignUp();
                    break;
                } else {
                    System.out.println("Invalid user input. Please try again.");
                    userInp = input.next();
                }
            } while (true);
        }

    }


}
