import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class DepositedTest {
    BankAccountDao mockDao = mock(BankAccountDao.class);

    @Before
    public void setUp() {
        reset(mockDao);
        BankAccount.setBankAccountDao(mockDao);
    }

    @Test
    public void testDepositedMoneyToAccount() {
        String accountNumber = "1234567890";
        double balance = 0;
        double depositedMoney = 50;
        String log= "deposit 50";
        BankAccount.openAccount(accountNumber);

        BankAccount.deposit(accountNumber,depositedMoney,log);
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> depositedMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(mockDao).deposit(accountNumberCaptor.capture(),depositedMoneyCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(depositedMoneyCaptor.getValue(),depositedMoney,0.001);
        assertEquals(logCaptor.getValue(),log);
    }
}
