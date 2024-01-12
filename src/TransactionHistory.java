import java.text.DecimalFormat;
public class TransactionHistory {

    private static int Anum = 0000;
    private static int Snum = 0000;
    private static String history = "";
    private static String statement;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0000");

    public TransactionHistory() {}


    public static String getHistory() {
        return history;
    } //returns history to be printed

    public static String reciept(String s, int num){ // creates a reciept for each transaction
        if (num == 1 || num == 2 || num == 3) {
            statement = "A" + decimalFormat.format(Anum) + " " + s;
            Anum++;
        } else {
            statement = "S" + decimalFormat.format(Snum) + " " + s;
            Snum++;
        }
        addStatement(statement); // adds transaction to history
        return statement;
    }


    private static void addStatement(String statement) {
        history += statement + "\n";
    } // adds transaction to history
}


