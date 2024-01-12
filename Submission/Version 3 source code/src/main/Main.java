package main;

import authentication.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sportfacility.*;
import exceptions.*;
import user.*;

public class Main {
    private static boolean isRunning;
    private static String username;
    private static String password;
    private static Customer customer;
    private static AuthenticationService authInstance = AuthenticationService.getInstance();
    private static List<SportFacility> facilities = new ArrayList<>();
    private static Admin admin; // = Admin.getInstance();

    public static void main(String args[]) {
        // AS.registerAdmin(cred, cred);
        Scanner scanner = new Scanner(System.in);
        isRunning = true;
        admin=null;
        while (isRunning) {
            int response = openScreen(scanner);
            if (response == 1) {
                customer = authInstance.register(username, password);
            } else if (response == 2) {
                if (!username.equals("admin"))
                    customer = authInstance.login(username, password); 
                else
                    admin = authInstance.adminLogin(password);
            }
            if (customer != null)
                mainMenu(scanner);
            else if (authInstance.adminLogin(password) != null)
                adminMenu(scanner);
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
            try {
                String strResponse = scanner.next();
                response = Integer.parseInt(strResponse);
                if (response == 1) {
                    if (admin == null)
                        System.out.print("The system is not yet ready to use. Please try again later. Thank you.\n\n");
                    else {
                        while (!authInstance.validatePassword(password)) {
                            try {
                                System.out.print("Set a username: ");
                                username = scanner.next();
                                if (username.equals("/"))
                                    break;
                                if (!authInstance.findUsername(username)) {
                                    System.out.print("Set a password: ");
                                    password = scanner.next();
                                    if (password.equals("/"))
                                        break;
                                    if (!authInstance.validatePassword(password))
                                        throw new ExPasswordInvalid();
                                } else
                                    throw new ExAccountExists();
                            } catch (ExAccountExists e) {
                                System.out.print(e.getMessage());
                            } catch (ExPasswordInvalid e) {
                                System.out.print(e.getMessage());
                            }
                        }
                    }
                } else if (response == 2) {
                    while (authInstance.findUser(username, password) == null
                            && authInstance.findAdmin(password) == null) {
                        try {
                            System.out.print("Input your username: ");
                            username = scanner.next();
                            if (username.equals("/"))
                                break;
                            if (!authInstance.findUsername(username) && !username.equals("admin")) {
                                throw new ExNoAccount();
                            }
                            System.out.print("Input your password: ");
                            password = scanner.next();
                            if (password.equals("/"))
                                break;
                            if (authInstance.findUser(username, password) == null
                                    && authInstance.findAdmin(password) == null) {
                                throw new ExPasswordIncorrect();
                            }
                        } catch (ExNoAccount e) {
                            System.out.print(e.getMessage());
                        } catch (ExPasswordIncorrect e) {
                            System.out.print(e.getMessage());
                        }
                    }
                } else if (response == 0) {
                    System.out.print("Thank you for using our system.\n");
                    isRunning = false;
                } else {
                    throw new ExInvalidToken();
                }
            } catch (ExInvalidToken e) {
                System.out.print(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.print("Input is not a number. Try again.\nInput: ");
            }
        }
        return response;
    }

    private static void adminMenu(Scanner scanner) {
        int userInput = -1;
        while (userInput != 0) {
            try{
                System.out.print(admin.printTime());
                System.out.print("\n");
                System.out.print("Press 1 to add a facility.\n");
                System.out.print("Press 2 to change system time.\n");
                System.out.print("Press 0 to log out.\n");
                System.out.print("Input: ");

                String strUserInput = scanner.next(); // handle exception where input is not a number
                userInput = Integer.parseInt(strUserInput);

                switch (userInput) {
                    case 1:
                        addFacility(scanner);
                        break;
                    case 2:
                        changeTime(scanner);
                        break;
                    case 0:
                        // logout
                        break;
                    default:
                        throw new ExInvalidToken();
                }
            }
            catch (ExInvalidToken e){
                System.out.print(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.print(e.getMessage());
            }
        }
    }

    private static void mainMenu(Scanner scanner) {
        int userInput = -1;
       
        while (userInput != 0) {
            try {
                System.out.print(admin.printTime());
                System.out.print("\n");
                System.out.print("Press 1 to make a booking.\n");
                System.out.print("Press 2 to view bookings.\n");
                System.out.print("Press 3 to cancel bookings.\n");
                System.out.print("Press 4 to check for any cancelled bookings.\n");
                System.out.print("Press 5 to review any past bookings.\n");
                System.out.print("Press 6 to check facility ratings.\n");
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
                        customer.checkNotifications();
                        System.out.print("Enter 0 to go back to main menu.\n");
                        int input = -1;
                        while (input != 0){
                            String strInput = scanner.next();
                            input = Integer.parseInt(strInput);
                            if (input != 0) {
                                throw new ExInvalidToken();
                            }
                        } 
                        break;
                    case 5:
                        customer.viewCompletedBookings(admin.getClock());
                        System.out.print("Input booking ID to review a booking: ");
                        String strID = scanner.next();
                        int bookID = Integer.parseInt(strID);
                        System.out.print("Enter comment: ");
                        scanner.nextLine();
                        String comment = scanner.nextLine();
                        System.out.print("Enter rating (a number between 1-5): ");
                        String strRate = scanner.next();
                        int rate = Integer.parseInt(strRate);
                        customer.addReview(bookID, comment, rate);
                        System.out.print("Thank you for your feedback.\n");
                        break;
                    case 6:
                        for (int i = 0; i < facilities.size(); i++) {
                            System.out.print(facilities.get(i) + ": \n");
                            facilities.get(i).printReviews();
                            System.out.print("\n");
                        }
                        break;
                    case 0:
                        authInstance.logout(customer);
                        break;
                    default:
                        throw new ExInvalidToken();
                }
            } catch (NumberFormatException e) {
                System.out.print("Input is not a number. Try again.\nInput: ");
            } catch (ExInvalidToken e) {
                System.out.print(e.getMessage());
            } catch (ExWrongPayMethod e) {
                System.out.print(e.getMessage());
            } catch (ExWrongDate e) {
                System.out.print(e.getMessage());
            } catch (ExPastDate e){
                System.out.print(e.getMessage());
            }            
        }
    }

    private static void makeBooking(Scanner scanner) throws ExInvalidToken, ExWrongPayMethod, ExWrongDate, ExPastDate {
        int facilityNo = -1;
        boolean bookSuccessful = false;
        while (facilityNo != 0 && !bookSuccessful) {
            System.out.print("Choose a facility (Enter 0 to go to the main menu).\n");
            for (int i = 0; i < facilities.size(); i++) {
                System.out.print(Integer.toString(i + 1) + ". " + facilities.get(i) + "\n");
            }
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
                case 5:
                    System.out.print("Enter date of booking (DD-MM-YYYY): ");
                    String date = scanner.next();
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

                    bookSuccessful = admin.receiveBookingRequest(customer, chosenFacility, date, time, payment);
                   
                    break;
                case 0:
                    break;
                default:
                    throw new ExInvalidToken();
            }

        }
    }

    private static void viewBooking(Scanner scanner) throws ExInvalidToken {
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
        String bookID = "";
        while (bookID.equals("") || Integer.parseInt(bookID) != 0){
            System.out.print("\nEnter Booking ID to cancel a booking.\n");
            System.out.print("Enter 0 to go to the main menu.\n");
            customer.viewBookings();
            System.out.print("Input: ");
            bookID = scanner.next();
            if (Integer.parseInt(bookID) == 0)
                break;
            int cancelled = admin.receiveCancelRequest(customer, Integer.parseInt(bookID));
            if (cancelled == 3){
                break;
            }
            else if (cancelled == 2)
                System.out.print("Sorry, this booking cannot be cancelled and is therefore non-refundable.\n" +
                "A booking can only be cancelled 6 hours or more in advance.\n");
            else if (cancelled == 1)
                System.out.print("Booking does not exist. Try again.");
        }
    }

    private static void changeTime(Scanner scanner) {
        String date = "";
        String hour = "";

        System.out.print("Enter the date you want to change to (DD-MM-YYYY): ");
        date = scanner.next();
        System.out.print("Enter the hour you want to change to (If you want to change hour to 13:00, enter 13): ");
        hour = scanner.next();

        admin.changeTime(date, hour); 
    }


    private static void addFacility(Scanner scanner) {
        System.out.print("Choose a facility to add.\n");
        System.out.print("1. Swimming\n2. Badminton\n3. Basketball\n4. Tennis\n5. Football\n");
        System.out.print("Input: ");
        String strFac = scanner.next();
        int fac = Integer.parseInt(strFac);
        String[] facilityStr = { "Swimming", "Badminton", "Basketball", "Tennis", "Football" };
        facilities = admin.addFacility(facilityStr[fac - 1]);
    }
}