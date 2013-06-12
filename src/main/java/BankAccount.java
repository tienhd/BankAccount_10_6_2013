import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static BankAccountDao bankAccountDao;
    private static TransactionDao transactionDao;

    public static void setBankAccountDao(BankAccountDao bankAccountDao) {
        BankAccount.bankAccountDao = bankAccountDao;
    }

    public static BankAccountDao getBankAccountDao() {
        return bankAccountDao;
    }

    public static void openAccount(String accountNumber) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
        bankAccountDao.save(bankAccountDTO);
    }

    public static BankAccountDTO getAccount(String accountNumber) {
        return bankAccountDao.getAccount(accountNumber);
    }

    public static void deposit(String accountNumber, double depositedMoney, String log) {
        BankAccountDTO bankAccountDTO = bankAccountDao.getAccount(accountNumber);
        double newBalance = bankAccountDTO.getBalance() + depositedMoney;
        bankAccountDTO.setBalance(newBalance);
        bankAccountDao.save(bankAccountDTO,log);
    }

    public static void withdraw(String accountNumber, double withdrawMoney, String log) {
        BankAccountDTO bankAccountDTO = bankAccountDao.getAccount(accountNumber);
        if (bankAccountDTO.getBalance() >= withdrawMoney ) {
            double newBalance = bankAccountDTO.getBalance() - withdrawMoney;
            bankAccountDTO.setBalance(newBalance);
            bankAccountDao.save(bankAccountDTO,log);
        }
        else {
            System.out.println("Not enough money to withdraw.");
        }
    }

    public static void setTransactionDao(TransactionDao transactionDao) {
        BankAccount.transactionDao = transactionDao;
    }

    public static ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber) {
        return transactionDao.getTransactionOccurred(accountNumber);

    }

    public static ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber, long startTime, long endTime) {
        return transactionDao.getTransactionOccurred(accountNumber,startTime,endTime);
    }

    public static ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        return transactionDao.getTransactionOccurred(accountNumber,n);
    }
}
