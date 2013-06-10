import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

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

    @Test
    public void testOpenAccountThenGetTheAccountInformation() {
        String accountNumber = "1234567890";
        BankAccount.openAccount(accountNumber);

        ArgumentCaptor <BankAccountDTO> argumentList = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockDao,times(1)).save(argumentList.capture());
        BankAccountDTO argumentDTO = argumentList.getAllValues().get(0);
        System.out.println(argumentDTO.getBalance() + " " + argumentDTO.getAccountNumber());

        when(mockDao.getAccount(accountNumber)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String accountNumber2 = "1234567890";
                BankAccountDTO answerDTO = new BankAccountDTO(accountNumber2);
                return answerDTO;
            }
        });

        BankAccountDTO answerDTO = mockDao.getAccount(accountNumber);
        System.out.println(answerDTO.getBalance() + " " + answerDTO.getAccountNumber());

        assertEquals(mockDao.getAccount(accountNumber).getAccountNumber(), accountNumber);
        assertEquals(mockDao.getAccount(accountNumber).getBalance(), 0, 0.001);

        assertEquals(answerDTO,argumentDTO);
    }

}
