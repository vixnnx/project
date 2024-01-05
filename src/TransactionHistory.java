public class TransactionHistory {

    private static int Anum = 0000;
    private static int Snum = 0000;
    private static String history = "";
    private static String statement;

    public TransactionHistory() {}

    private static void addStatement(String statement) {
        history += statement + "\n";
    }

    public static String reciept(String s, int num){
        if (num == 1 || num == 2 || num == 3) {
            statement = "A" + Anum + " " + s;
            Anum++;
        } else {
            statement += "S" + Snum + " " + s;
            Snum++;
        }
        addStatement(statement);
        return statement;
    }

    public static String getHistory() {
        return history;
    }

}
