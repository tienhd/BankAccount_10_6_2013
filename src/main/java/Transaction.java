/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/11/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private static TransactionDao transactionDao;
    public static void setTransactionDao(TransactionDao transactionDao) {
        Transaction.transactionDao = transactionDao;
    }

    public static void depositedMoneyLog(String accountNumber, double depositedMoney, long timeStamp, String log) {
        transactionDao.depositedMoneyLog(accountNumber,depositedMoney,timeStamp,log);
    }

    public static void withdrawMoneyLog(String accountNumber, double withdrawMoney, long timeStamp, String log) {
        transactionDao.withdrawMoneyLog(accountNumber,-withdrawMoney,timeStamp,log);
    }
}
