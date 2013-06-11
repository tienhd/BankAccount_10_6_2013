import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/11/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionTest {
    TransactionDao mockTransactionDao = mock(TransactionDao.class);

    @Before
    public void setUp() {
        reset(mockTransactionDao);
        Transaction.setTransactionDao(mockTransactionDao);
    }

    @Test
    public void testDepositedMoneyLog() {
        String accountNumber = "1234567890";
        double balance = 0;
        double depositedMoney = 50;
        String log = "deposit 50";
        long timeStamp = System.currentTimeMillis();

        Transaction.depositedMoneyLog(accountNumber,depositedMoney,timeStamp,log);
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> depositedMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(mockTransactionDao).depositedMoneyLog(accountNumberCaptor.capture(),depositedMoneyCaptor.capture(),timeStampCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(depositedMoneyCaptor.getValue(),depositedMoney,0.001);
        assertEquals(timeStampCaptor.getValue(),timeStamp,0.001);
        assertEquals(logCaptor.getValue(),log);

    }

    @Test
    public void testWithdrawMoneyLog() {
        String accountNumber = "1234567890";
        double balance = 100;
        double withdrawMoney = 50;
        String log = "withdraw 50";
        long timeStamp = System.currentTimeMillis();

        Transaction.withdrawMoneyLog(accountNumber,withdrawMoney,timeStamp,log);
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> withdrawMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(mockTransactionDao).withdrawMoneyLog(accountNumberCaptor.capture(),withdrawMoneyCaptor.capture(),timeStampCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(withdrawMoneyCaptor.getValue(),-withdrawMoney,0.001);
        assertEquals(timeStampCaptor.getValue(),timeStamp,0.001);
        assertEquals(logCaptor.getValue(),log);

    }
}
