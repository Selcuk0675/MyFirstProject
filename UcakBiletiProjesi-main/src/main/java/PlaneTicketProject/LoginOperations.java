package PlaneTicketProject;

import java.util.Scanner;

public class LoginOperations {

    static Scanner input = new Scanner(System.in);

    public static void openLoginScreen() {
        System.out.println("==================================");
        System.out.println("WELCOME TO THE buycheaptickets.com");
        System.out.println("==================================");

        do {
            System.out.println("Please select the action you want to.");
            System.out.println("1- Sign In \n2- Sign Up");
            String userInp = input.next();
            if (userInp.equals("1")) {
                SignIn();
                break;
            } else if (userInp.equals("2")) {
                SignUp();
                break;
            } else {
                System.out.println("Invalid input! Please make sure you clicked the correct button.");
            }
        } while (true);

    }

    public static void SignUp() {
        System.out.println("==================================");
        System.out.println("SIGN UP SCREEN");
        System.out.println("==================================");

        System.out.println("Please enter your name");
        String userName = input.next();
        System.out.println("Please enter your last name");
        String userLastName = input.next();

        System.out.println("Please enter your birth year (yyyy)");
        Integer userAge = input.nextInt();

        do {
            if (userAge >= 0) {
                break;
            } else {
                System.out.println("Birth year can not be negative. Please enter your birth year again.");
                userAge = input.nextInt();
            }
        } while (true);


        System.out.println("Please enter your Gmail address");
        String userEmail = input.next();

        System.out.println("Please enter your password");
        String userPassword = input.next();

        Customer customer = new Customer(userName, userLastName, userAge, userEmail);
        DatabaseOperations.customerInformation.put(userEmail, customer);
        DatabaseOperations.emailsAndPasswords.put(userEmail, userPassword);

        System.out.println("You have successfully registered! Welcome to buycheaptickets.com.");
        UserDashboard.accountMenu(customer);
    }

    public static void SignIn() {
        System.out.println("==================================");
        System.out.println("SIGN IN SCREEN");
        System.out.println("==================================");

        System.out.println("Please enter your email address");
        String userEmail = input.next();

        DatabaseOperations.checkUserEmailAndPassword(userEmail);

    }

    public static void logOut() {
        System.out.println("You have successfully logged out!");
        openLoginScreen();
    }


}

