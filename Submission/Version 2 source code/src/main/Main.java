package main;

import authentication.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sportfacility.*;
import transaction.PayPalPayment;
import exceptions.*;
import user.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static boolean isRunning;
    private static String username;
    private static String password;
    private static Customer customer;
    private static AuthenticationService authInstance = AuthenticationService.getInstance();
    private static List<SportFacility> facilities = new ArrayList<>();
    public static void main(String args[]) {
        // AS.registerAdmin(cred, cred);
        Scanner scanner = new Scanner(System.in);
        facilities.add(new SwimmingPool(9, 20, 10));
        facilities.add(new BadmintonCourt(9, 23, 20));
        facilities.add(new BasketballCourt(9, 22, 120));
        facilities.add(new TennisCourt(9, 22, 50));
        isRunning = true;

        while (isRunning) {
            int response = openScreen(scanner);
            if (response == 1) {
                customer = authInstance.register(username, password);
            } else if (response == 2) {
                customer = authInstance.login(username, password); 
            }
            if (customer != null)
                mainMenu(scanner);
        }
        scanner.close(); // Close the scanner only once here
    }

    private static int openScreen(Scanner scanner) {
        customer = null;
        username = "";
        password = "";
        System.out.print("Shahbagh Sports Complex\n");
        System.out.print("---------------------------------------------------------\n");
        System.out.print("Press 1 to Sign-Up | Press 2 to Sign-In | Press 0 to exit\n");
        System.out.print("Input: ");
        int response = -1;
        while (response != 1 && response != 2 && response != 0) {
            try{
                String strResponse = scanner.next();
                response = Integer.parseInt(strResponse);
                if (response == 1) {
                    while (!authInstance.validatePassword(password)) {
                        try {
                            System.out.print("Set a username: ");
                            username = scanner.next();
                            if (username.equals("/"))
                                break;
                            if (!authInstance.findUsername(username)) {
                                System.out.print("Set a password: ");
                                password = scanner.next();
                                if (username.equals("/"))
                                    break;
                                if (!authInstance.validatePassword(password))
                                    throw new ExPasswordInvalid();
                            } else
                                throw new ExAccountExists();
                        }
                        catch (ExAccountExists e){
                            System.out.print(e.getMessage());
                        }
                        catch (ExPasswordInvalid e){
                            System.out.print(e.getMessage());
                        }
                    }
                } else if (response == 2) {
                    while (authInstance.findUser(username, password) == null) {
                        try{
                            System.out.print("Input your username: ");
                            username = scanner.next();

                            if (!authInstance.findUsername(username)) {
                                throw new ExNoAccount();
                            }
                            if (username.equals("/"))
                                break;
                            System.out.print("Input your password: ");
                            password = scanner.next();
                            if (username.equals("/"))
                                break;
                            if (authInstance.findUser(username, password)==null) {
                                throw new ExPasswordIncorrect();
                            }
                        }
                        catch (ExNoAccount e){
                            System.out.print(e.getMessage());
                        }
                        catch (ExPasswordIncorrect e){
                            System.out.print(e.getMessage());
                        }
                    }
                } else if (response == 0) {
                    System.out.print("Thank you for using our system.\n");
                    isRunning = false;
                } else {
                    throw new ExInvalidToken();
                }
            }
            catch (ExInvalidToken e){
                System.out.print(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.print("Input is not a number. Try again.\nInput: ");
            }
        }
        return response;
    }

    private static void mainMenu(Scanner scanner) {
        int userInput = -1;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateNtime = now.format(formatter);
        System.out.print(dateNtime + "\n");
        while (userInput != 0) {
            try{
                System.out.print("Press 1 to make a booking.\n");
                System.out.print("Press 2 to view bookings.\n");
                System.out.print("Press 3 to cancel bookings.\n");
                System.out.print("Press 4 to show available facilities.\n");
                System.out.print("Press 0 to log out.\n");
                System.out.print("Input: ");
                String strUserInput = scanner.next(); // handle exception where input is not a number
                
                userInput = Integer.parseInt(strUserInput);
                switch (userInput) {
                    case 1:
                        makeBooking(scanner);
                        break;
                    case 2:
                        viewBooking(scanner);
                        break;
                    case 3:
                        cancelBooking(scanner);
                        break;
                    case 4:
                        break;
                    case 0:
                        authInstance.logout(customer);
                        break;
                    default:
                        throw new ExInvalidToken();

                }
            }
            catch (NumberFormatException e){
                System.out.print("Input is not a number. Try again.\nInput: ");
            }
            catch (ExInvalidToken e){
                System.out.print(e.getMessage());
            }
            catch (ExWrongPayMethod e){
                System.out.print(e.getMessage());
            }
        }
    }

    private static void makeBooking(Scanner scanner) throws ExInvalidToken, ExWrongPayMethod{
        int facilityNo = -1;
        boolean bookSuccessful = false;
        while (facilityNo != 0 && !bookSuccessful) {
            try{
                System.out.print("Choose a facility (Enter 0 to go to the main menu).\n");
            System.out.print("1. Swimming\n2. Badminton\n3. Basketball\n4. Tennis\n");
            System.out.print("Input: ");
            String strFacility = scanner.next();
            if (strFacility.equals("/"))
                break;
            facilityNo = Integer.parseInt(strFacility);
            switch (facilityNo) {
                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.print("Enter date of booking (DD-MM-YYYY): ");
                    String date = scanner.next();
                    if (date.equals("/"))
                        break;
                    SportFacility chosenFacility = facilities.get(facilityNo - 1);
                    chosenFacility.showAvailableSlots(date);
                    System.out.print("Enter preferred time slot (e.g. if you want to book at 20:00, type 20): ");
                    String strTime = scanner.next();
                    if (strTime.equals("/"))
                        break;
                    int time = Integer.parseInt(strTime);
                    System.out.print("Select a payment method:\n");
                    System.out.print("1. Credit Card (Enter CC)\n2. PayPal (Enter PL)\n");
                    System.out.print("Input: ");
                    String payment = scanner.next();
                    if (payment.equals("/"))
                        break;
                    if (!payment.equals("CC") && !payment.equals("PL"))
                        throw new ExWrongPayMethod();
                    bookSuccessful = customer.createBooking(chosenFacility, date, time, payment);
                    if (bookSuccessful) {
                        System.out.print("Booking successfully created for " + date + " from " +
                                Integer.toString(time) + ":00 to " + Integer.toString(time + 1)
                                + ":00. Go to main menu to manage your bookings.\n");
                        System.out.print(
                                "------------------------------------------------------------------------------------------------------------------------------------------\n");
                    }
                    break;
                default:
                    throw new ExInvalidToken();
                }
            }catch (ExWrongDate e) {
                System.out.print(e.getMessage());
            }

        }
    }

    private static void viewBooking(Scanner scanner) throws ExInvalidToken{
        customer.viewBookings();
        System.out.print("Enter 0 to go to the main menu.\n");
        int input = -1;
        while (input != 0) {
            String strInput = scanner.next();
            input = Integer.parseInt(strInput);
            if (input != 0) {
                throw new ExInvalidToken();
            }
        }
    }

    private static void cancelBooking(Scanner scanner) {
        boolean cancelled = true;
        System.out.print("Enter Booking ID to cancel a booking.\n");
        customer.viewBookings();
        System.out.print("Input: ");
        String bookID = scanner.next();
        customer.cancelBooking(Integer.parseInt(bookID));
        if (cancelled)
            System.out.print("Booking cancelled successfully.\n");
        else
            System.out.print("Booking does not exist, try again.\n");
    }
}
