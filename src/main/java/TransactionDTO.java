/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/11/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO {
    private String accountNumber;
    private double amountMoney;
    private long timeStamp;
    private String log;

    public TransactionDTO(String accountNumber, double amountMoney, long timeStamp, String log) {
        this.accountNumber = accountNumber;
        this.amountMoney = amountMoney;
        this.timeStamp = timeStamp;
        this.log = log;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof TransactionDTO) {
            TransactionDTO transactionDTOObj = (TransactionDTO) o;
            return ((accountNumber.equals(transactionDTOObj.getAccountNumber())) && (amountMoney == transactionDTOObj.getAmountMoney())
                    && (timeStamp == transactionDTOObj.getTimeStamp()) && (log.equals(transactionDTOObj.getLog())));
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getLog() {
        return log;
    }
}
