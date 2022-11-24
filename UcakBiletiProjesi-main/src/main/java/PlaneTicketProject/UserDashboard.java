package PlaneTicketProject;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class UserDashboard {

    static Scanner input = new Scanner(System.in);

    public static void accountMenu(Customer customer) {
        System.out.println("==================================");
        System.out.println("YOUR PROFILE");
        System.out.println("==================================");
        System.out.println("Hello " + customer.getCustomerName() + " " + customer.getCustomerLastName());

        do {
            System.out.println("Please select the action you want to.");
            System.out.println("1- Buy Ticket\n2- My Tickets\n3- My Information\n4- Log Out");
            String userInp = input.next();
            if (userInp.equals("1")) {
                buyTicket(customer);
                break;
            } else if (userInp.equals("2")) {
                showPurchasedTickets(customer);
                break;
            } else if (userInp.equals("3")) {
                showCustomerInformation(customer);
                break;
            } else if (userInp.equals("4")) {
                LoginOperations.logOut();
                break;
            } else {
                System.out.println("Invalid user input. Please try again.");
            }

        } while (true);
    }

    public static void buyTicket(Customer customer) {
        System.out.println("==================================");
        System.out.println("BUY TICKET SCREEN");
        System.out.println("==================================");

        Integer customerAge = customer.getAge();
        int distance;
        String city;
        boolean roundRoundDiscount;

        do {
            System.out.println("Please select the city you want to fly to:");
            System.out.println("1- Ankara\n2- Erzurum\n3- Istanbul\n4- Izmir\n5- Bayburt");
            String userCity = input.next();

            //Bu kisim biraz hard-coding oldu...
            if (userCity.equals("1")) {
                city = "Ankara";
                distance = 750;
                break;
            } else if (userCity.equals("2")) {
                city = "Erzurum";
                distance = 1000;
                break;
            } else if (userCity.equals("3")) {
                city = "Istanbul";
                distance = 1300;
                break;
            } else if (userCity.equals("4")) {
                city = "Izmir";
                distance = 450;
                break;
            } else if (userCity.equals("5")) {
                city = "Bayburt";
                distance = 690;
                break;
            } else {
                System.out.println("Invalid user input. Please try again...");
            }
        } while (true);

        do {
            System.out.println("1- One Way\n2- Round-round");
            String userInp = input.next();

            if (userInp.equals("1")) {
                roundRoundDiscount = false;
                break;
            } else if (userInp.equals("2")) {
                roundRoundDiscount = true;
                break;
            } else {
                System.out.println("Invalid user input. Please try again...");
            }

        } while (true);

        double totalCost = UserDashboard.calculateTotalCost(customerAge, distance, roundRoundDiscount);
        System.out.println("Distance from your current location to " + city + " is " + distance + " km\n" +
                "Total cost is: " + totalCost + "$\n" +
                "Total discount is: " + ((distance * 0.1) - totalCost) + "$");

        do {
            System.out.println("Confirm to purchase please click 'P', or to cancel and return the dashboard please click 'C'. ");
            String userConfirmationInput = input.next().toUpperCase();
            if (userConfirmationInput.equals("P")) {
                System.out.println("The ticket has been created. You can see your ticket info in the 'My Tickets' section...");
                String ticket = "Destination: " + city + ", Ticket Date: " + LocalDate.now() + ", Total Cost: " + totalCost + "$\n";
                Integer ticketID = (int) (Math.random() * 100000);
                customer.customerTickets.put(ticketID, ticket);
                accountMenu(customer);
                break;
            } else if (userConfirmationInput.equals("C")) {
                System.out.println("Ticket creation cancelled...");
                accountMenu(customer);
                break;
            }
        } while (true);

    }

    public static void showPurchasedTickets(Customer customer) {

        System.out.println("==================================");
        System.out.println("MY TICKETS SCREEN");
        System.out.println("==================================");

        do {
            for (Map.Entry<Integer, String> w : customer.CustomerTicketsSet) {
                System.out.println("Ticket ID: " + w.getKey() + " " + w.getValue());
            }
            accountMenu(customer);
            break;

        } while (true);
    }

    public static void showCustomerInformation(Customer customer) {

        do {
            System.out.println("==================================");
            System.out.println("MY INFORMATION SCREEN");
            System.out.println("==================================");

            System.out.println("Your Name: " + customer.getCustomerName() + " " + customer.getCustomerLastName());
            System.out.println("Your Age: " + LocalDate.now().minusYears(customer.getAge()).getYear());
            System.out.println("Your Registered Email Address: " + customer.getEmail());

            System.out.println("Please select the action you want to.");
            System.out.println("1- Change Registered Email\n2- Change Password\n3- Log Out\n4- Back to Main Menu");

            String userInp = input.next();
            if (userInp.equals("1")) {
                changeRegisteredEmail(customer);
                break;
            } else if (userInp.equals("2")) {
                System.out.println("Yapim asamasinda...");
                accountMenu(customer);
                break;
            } else if (userInp.equals("3")) {
                LoginOperations.logOut();
                break;
            } else if (userInp.equals("4")) {
                accountMenu(customer);
                break;
            } else {
                System.out.println("Invalid user input. Please try again.");
                System.out.println();
            }

        } while (true);

    }

    public static double calculateTotalCost(Integer customerAge, int distance, boolean isRoundRound) {
        int age = LocalDate.now().minusYears(customerAge).getYear();

        double totalCost = distance * 0.1;
        double discount = 0;

        if (age < 12) {
            totalCost = (distance * 0.10) / 2;
        } else if (age < 24) {
            discount = distance * 0.10 * 0.10;
            totalCost = (distance * 0.10) - discount;
        } else if (age > 65) {
            discount = distance * 0.10 * 0.30;
            totalCost = (distance * 0.10) - discount;
        }

        if(isRoundRound) {
            totalCost -= (totalCost * 0.2);
        }

        return totalCost;
    }

    public static void changeRegisteredEmail(Customer customer) {

        System.out.println("Your current Registered Email Address: " + customer.getEmail());
        System.out.println("If you want to change your registered email address, please click '1' or if you want to cancel and return to menu\n" +
                "please click '2'.");
        String userInp = input.next();
        do {
            if (userInp.equals("1")) {
                System.out.println("Please enter your new email address:");
                String newEmail = input.next();

                String customerpassword = DatabaseOperations.emailsAndPasswords.get(customer.getEmail());
                String oldEmail = customer.getEmail();
                customer.setEmail(newEmail);

                DatabaseOperations.emailsAndPasswords.remove(oldEmail);//Eski veri database'den silindi
                DatabaseOperations.customerInformation.remove(oldEmail);//Eski veri database'den silindi

                DatabaseOperations.customerInformation.put(newEmail, customer);//Yeni veri yeni email ile database'e eklendi
                DatabaseOperations.emailsAndPasswords.put(newEmail, customerpassword);//Yeni veri yeni email ile database'e eklendi

                System.out.println("Email address has been changed. New registered email address: " + newEmail);
                accountMenu(customer);
                break;
            } else if (userInp.equals("2")) {
                accountMenu(customer);
                break;
            } else {
                System.out.println("Invalid user input. Please try again.");
                userInp = input.next();
            }
        } while (true);

    }


}















































