import java.text.DecimalFormat;
public class TransactionHistory {

    private static int Anum = 0000;
    private static int Snum = 0000;
    private static String history = "";
    private static String statement;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0000");

    public TransactionHistory() {}

    private static void addStatement(String statement) {
        history += statement + "\n";
    }

    public static String reciept(String s, int num){ // sees too much
        if (num == 1 || num == 2 || num == 3) {
            statement = "A" + decimalFormat.format(Anum) + " " + s;
            Anum++;
        } else {
            statement = "S" + decimalFormat.format(Snum) + " " + s;
            Snum++;
        }
        addStatement(statement);
        return statement;
    }

    public static String getHistory() {
        return history;
    }


}


