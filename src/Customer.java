public class Customer {
    private int pin;
    private String name;

    public Customer(String name, int pin) {
        this.pin = pin;
        this.name = name;
    }

    public int getPin(){
        return pin;
    } // returns pin

    public void setPin(int pin){
        this.pin = pin;
    } // changes pin
}
