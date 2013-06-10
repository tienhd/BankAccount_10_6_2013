import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTimeStampTest {

    BankAccountTimeStamp mockBankAccountTimeStamp = mock(BankAccountTimeStamp.class);

    @Before
    public void setUp() {
        reset(mockBankAccountTimeStamp);
        BankAccount.setTimeStamp(mockBankAccountTimeStamp);
    }

    @Test
    public void testGetCurrentTimeFromTimeStamp() {

        BankAccount.getCurrentTime();

        when(mockBankAccountTimeStamp.getTimeStamp()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Date mockDate = new Date();
                mockBankAccountTimeStamp.setTimeStamp(mockDate);
                return mockDate.toGMTString();
            }
        });

        verify(mockBankAccountTimeStamp).getTimeStamp();
    }

}
