import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DepositedLogTest {
    BankAccountDao mockBankAccountDao = mock(BankAccountDao.class);
    BankAccountTimeStamp mockBankAccountTimeStamp = mock(BankAccountTimeStamp.class);

    @Before
    public void setUp() {
        reset(mockBankAccountDao);
        reset(mockBankAccountTimeStamp);
        BankAccount.setBankAccountDao(mockBankAccountDao);
        BankAccount.setTimeStamp(mockBankAccountTimeStamp);
    }

    @Test
    public void testDepositedMoneyLog() {
        String accountNumber = "1234567890";
        double balance = 0;
        double depositedMoney = 50;
        String log = "deposit 50";

        when(mockBankAccountTimeStamp.getTimeStamp()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Date mockDate = new Date();
                mockBankAccountTimeStamp.setTimeStamp(mockDate);
                return mockDate.toGMTString();
            }
        });

        String timeStamp = mockBankAccountTimeStamp.getTimeStamp();
        BankAccount.depositedMoneyLog(accountNumber, depositedMoney, log);

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> depositedMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> timeStampCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDao).depositedMoneyLog(accountNumberCaptor.capture(),depositedMoneyCaptor.capture(),timeStampCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(depositedMoneyCaptor.getValue(),depositedMoney,0.001);
        assertEquals(timeStampCaptor.getValue(),timeStamp);
        assertEquals(logCaptor.getValue(),log);

    }
}
