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
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetAccountTest {
    BankAccountDao mockDao = mock(BankAccountDao.class);

    @Before
    public void setUp() {
        reset(mockDao);
        BankAccount.setBankAccountDao(mockDao);
    }

    @Test
    public void testGetTheAccountInformation() {
        String accountNumber = "1234567890";
        double balance = 0;

        BankAccount.getAccount(accountNumber);

        ArgumentCaptor<String> accountInformation = ArgumentCaptor.forClass(String.class);
        verify(mockDao).getAccount(accountInformation.capture());

        assertEquals(accountInformation.getAllValues().get(0),accountNumber);
    }

}
