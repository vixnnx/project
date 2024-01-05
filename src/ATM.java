import java.util.Scanner;
public class ATM {
    private Scanner scan = new Scanner(System.in);
    private Customer p1;
    private String loop = "yes";
    private int input;
    private double amt;
    private int twentyBills;
    private Account savings;
    private Account checking;


    public ATM() {   }

    public void start() {
        System.out.println("Welcome to the ATM");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.print("Please create a pin: ");
        int pin = scan.nextInt();
        p1 = new Customer(name, pin);
        savings = new Account("savings account", p1);
        checking = new Account("checking account", p1);

        while (loop.equals("yes") || input != 7) {
            access();
            options();
            System.out.print("What do you want to do?");
            input = scan.nextInt();
            if (input == 5) {
                TransactionHistory.getHistory();
            } else if (input == 6) {
                System.out.print("Enter a new pin:");
                int newPin = scan.nextInt();
                p1.setPin(newPin);
                System.out.println(TransactionHistory.reciept("changed pin", 5));
            } else if (input== 4) {
                System.out.print("Which account do you want to check? (savings or checking): ");
                String acc = scan.nextLine();
                if (acc.equals("savings")) {
                    System.out.println("You have $" + savings.getBalance() + " in your savings account");
                    TransactionHistory.reciept("Checked savings account balance", 4);
                } else if (acc.equals("checking")) {
                    System.out.println("You have $" + checking.getBalance() + " in your checking account");
                    TransactionHistory.reciept("Checked checking account balance", 4);
                }
            } else if (input == 3) {

        }

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
        while (attempt != p1.getPin()) {
            System.out.print("Access Denied, Please enter your correct pin: ");
            attempt = scan.nextInt();
        }
    }
}
