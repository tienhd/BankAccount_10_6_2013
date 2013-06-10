/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static BankAccountDao bankAccountDao;

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
}
