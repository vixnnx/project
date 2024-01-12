public class Account {
    private String name;
    private double balance;

    public Account (String name,Customer person) {
        this.name = name;
        balance = 0;
    }
    public double getBalance() {
        return balance;
    }

    public void add(double amt) {
        balance += amt;
    }

    public void remove(double amt) {
        balance -= amt;
    }

}
