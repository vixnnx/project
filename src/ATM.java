import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeSet;

public class ATM {
    private Scanner scan = new Scanner(System.in);
    private Customer p1;
    private String loop = "yes";
    private int input;
    private double amt;
    private int twentyBills;
    private Account savings;
    private Account checking;
    private String acc;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");


    public ATM() {   }

    public void start() { // sometimes prints history
        System.out.println("Welcome to the ATM");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.print("Please create a pin: ");
        int pin = scan.nextInt();
        scan.nextLine();
        p1 = new Customer(name, pin);
        savings = new Account("savings account", p1);
        checking = new Account("checking account", p1);

        while (loop.equals("yes") && input != 7) {
            ConsoleUtility.clearScreen();
            access();
            options();
            System.out.print("What do you want to do? ");
            input = scan.nextInt();
            scan.nextLine();
            if (input == 5) {
                System.out.println(TransactionHistory.getHistory());
            } else if (input == 6) { // prints history after?
                System.out.print("Enter a new pin:");
                int newPin = scan.nextInt();
                scan.nextLine();
                p1.setPin(newPin);
                System.out.println(TransactionHistory.reciept("changed pin", 5));
            } else if (input == 4) {
                System.out.print("Which account do you want to check? (savings or checking): ");
                acc = scan.nextLine();
                if (acc.equals("savings")) {
                    System.out.println("Savings Account: $" + decimalFormat.format(savings.getBalance()));
                    System.out.println(TransactionHistory.reciept("Checked savings account balance", 4));
                } else if (acc.equals("checking")) {
                    System.out.println("Checking Account: $" + decimalFormat.format(checking.getBalance()));
                    System.out.println(TransactionHistory.reciept("Checked checking account balance", 4));
                } else {
                    System.out.println("That is not a valid account");
                }
            } else if (input == 3) {
                System.out.print("Which account do you want to transfer from? (savings or checking): ");
                acc = scan.nextLine();
                if (acc.equals("savings") == true || acc.equals("checking") == true) {
                    System.out.print("How much do you want to transfer?: ");
                    amt = scan.nextDouble();
                    scan.nextLine();
                    if (acc.equals("savings")) {
                        if (amt > savings.getBalance()) {
                            System.out.println("insufficient funds!");
                            System.out.println(TransactionHistory.reciept("Money transfer was not successful", 3));
                        } else {
                            savings.remove(amt);
                            checking.add(amt);
                            System.out.println(TransactionHistory.reciept("Transferred $" + decimalFormat.format(amt) + " from savings into checking", 3));
                        }
                    } else if (acc.equals("checking")) {
                        if (amt > checking.getBalance()) {
                            System.out.println("insufficient funds!");
                            System.out.println(TransactionHistory.reciept("Money transfer was not successful", 3));
                        } else {
                            checking.remove(amt);
                            savings.add(amt);
                            System.out.println(TransactionHistory.reciept("Transferred $" + decimalFormat.format(amt) + " from checking into savings", 3));
                        }
                    }
                } else {
                    System.out.println("That is not a valid account");
                }
            } else if (input == 2) {
                System.out.print("Which account do you want to deposit into? (savings or checking): ");
                acc = scan.nextLine();
                if (acc.equals("savings") == true || acc.equals("checking") == true) {
                    System.out.print("How much do you want to deposit?: ");
                    amt = scan.nextDouble();
                    scan.nextLine();
                    if (acc.equals("savings")) {
                        savings.add(amt);
                        System.out.println(TransactionHistory.reciept("Deposited $" + decimalFormat.format(amt) + " into savings account", 2));
                        System.out.println("Savings Account: $" + decimalFormat.format(savings.getBalance()));
                    } else if (acc.equals("checking")) {
                        checking.add(amt);
                        System.out.println(TransactionHistory.reciept("Deposited $" + decimalFormat.format(amt) + " into checking account", 2));
                        System.out.println("Checking Account: $" + decimalFormat.format(checking.getBalance()));
                    }
                } else {
                    System.out.println("That is not a valid account");
                }
            } else if (input == 1) {
                System.out.print("Which account do you want to withdraw from? (savings or checking): ");
                acc = scan.nextLine();
                if (acc.equals("savings") == true || acc.equals("checking") == true) {
                    System.out.print("How much do you want to withdraw?: ");
                    amt = scan.nextDouble();
                    scan.nextLine();
                    if (amt % 5 == 0) {
                        if (acc.equals("savings")) {
                            if (amt > savings.getBalance()) {
                                System.out.println("insufficient funds!");
                                System.out.println(TransactionHistory.reciept("$" + decimalFormat.format(amt) + " withdraw from savings account was unsuccessful", 1));
                            } else {
                                if (amt >= 20 && amt % 5 == 0) {
                                    System.out.print("How many twenty dollar bills do you want?");
                                    if (twentyBills * 20 <= amt) {
                                        twentyBills = scan.nextInt();
                                        scan.nextLine();
                                        if (twentyBills * 20 == amt) {
                                            System.out.println("Here is " + twentyBills + " twenty's");
                                        } else {
                                            System.out.println("Here is " + twentyBills + " twenty's and " + (amt - 20 * twentyBills) / 5 + " five's");
                                        }
                                    }
                                } else if (amt % 5 == 0) { // less than 20
                                    System.out.println("Here is " + (amt - 20 * twentyBills) / 5 + " five's");
                                }
                                System.out.println("Savings Account: $" + decimalFormat.format(savings.getBalance()));
                                savings.remove(amt);
                                System.out.println(TransactionHistory.reciept("Withdraw $" + decimalFormat.format(amt) + " from savings", 1));
                            }
                        } else if (acc.equals("checking")) {
                            if (amt > checking.getBalance()) {
                                System.out.println("insufficient funds!");
                                System.out.println(TransactionHistory.reciept("$" + decimalFormat.format(amt) + " withdraw from checking account was unsuccessful", 1));
                            } else {
                                if (amt >= 20 && amt % 5 == 0) {
                                    System.out.print("How many twenty dollar bills do you want?");
                                    if (twentyBills * 20 <= amt) {
                                        twentyBills = scan.nextInt();
                                        scan.nextLine();
                                        if (twentyBills * 20 == amt) {
                                            System.out.println("Here is " + twentyBills + " twenty's");
                                        } else {
                                            System.out.println("Here is " + twentyBills + " twenty's and " + (amt - 20 * twentyBills) / 5 + " five's");
                                        }
                                    }
                                } else if (amt % 5 == 0) { // less than 20
                                System.out.println("Here is " + (amt - 20 * twentyBills) / 5 + " five's");
                                }
                                System.out.println("Checking Account: $" + decimalFormat.format(checking.getBalance()));
                                checking.remove(amt);
                                System.out.println(TransactionHistory.reciept("Withdraw $" + decimalFormat.format(amt) + " from checking", 1));
                            }
                        }
                    } else {
                        System.out.println("invalid amount!");
                        System.out.println(TransactionHistory.reciept("Money withdraw was not successful", 1));
                    }
                } else {
                    System.out.println("That is not a valid account");
                }
            }
                if (input != 7) {
                    System.out.println();
                    System.out.print("Do you want to do anything else? (yes or no): ");
                    loop = scan.nextLine();
                }
        }
        System.out.println("Thank you for using this ATM!");
    }

    public void options() {
        System.out.println("1. Withdraw money\n" +
                "2. Deposit money\n" +
                "3. Transfer money between accounts\n" +
                "4. Get account balances\n" +
                "5. Get transaction history\n" +
                "6. Change PIN\n" +
                "7. Exit\n");
    }

    public void access() {
        System.out.print("Please enter your pin: ");
        int attempt = scan.nextInt();
        scan.nextLine();
        while (attempt != p1.getPin()) {
            System.out.print("Access Denied, Please enter your correct pin: ");
            attempt = scan.nextInt();
            scan.nextLine();
        }
    }
}
