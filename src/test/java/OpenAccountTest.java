import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenAccountTest {
    BankAccountDao mockDao = new BankAccountDao();

    @Before
    public void setUp() {
        reset(mockDao);
        BankAccount.setBankAccountDao(mockDao);
    }

    @Test
    public void testConnectionToDao() {
        String accountNumber = "1234567890";
        BankAccount.openAccount(accountNumber);
        verify(mockDao);
    }

}
