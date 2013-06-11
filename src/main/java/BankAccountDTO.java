/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private String accountNumber;
    private double balance;
    private long timeStamp;

    public BankAccountDTO(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.timeStamp = System.currentTimeMillis();
    }

    public BankAccountDTO(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber (String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTimeStamp (long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BankAccountDTO) {
            BankAccountDTO bankAccountDTOObj = (BankAccountDTO) o;
            return ((this.balance == bankAccountDTOObj.getBalance()) && (this.accountNumber.equals(bankAccountDTOObj.getAccountNumber())));
        }
        return false;
    }
}
