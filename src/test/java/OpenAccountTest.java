import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenAccountTest {
    BankAccountDao mockDao = mock(BankAccountDao.class);

    @Before
    public void setUp() {
        reset(mockDao);
        BankAccount.setBankAccountDao(mockDao);
    }

    @Test
    public void testConnectionToDao() {
        String accountNumber = "1234567890";
        BankAccount.openAccount(accountNumber);
        //verify(mockDao);
    }

    @Test
    public void testOpenAccountAndIsPersistentToDB() {
        String accountNumber = "1234567890";
        double accountBalance = 0;
        BankAccount.openAccount(accountNumber);

        ArgumentCaptor<BankAccountDTO> bankDTOCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockDao,times(1)).save(bankDTOCaptor.capture());

        assertEquals(bankDTOCaptor.getAllValues().get(0).getAccountNumber(),accountNumber);
        assertEquals(bankDTOCaptor.getAllValues().get(0).getBalance(),accountBalance,0.001);

    }

}
