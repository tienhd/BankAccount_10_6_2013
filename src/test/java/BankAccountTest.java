import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/11/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    BankAccountDao mockDao = mock(BankAccountDao.class);
    TransactionDao mockTransactionDao = mock(TransactionDao.class);

    @Before
    public void setUp() {
        reset(mockDao);
        BankAccount.setBankAccountDao(mockDao);
        BankAccount.setTransactionDao(mockTransactionDao);
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

        when(mockDao.getAccount(accountNumber)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String accountNumber2 = "1234567890";
                BankAccountDTO answerDTO = new BankAccountDTO(accountNumber2);
                return answerDTO;
            }
        });

        BankAccountDTO answerDTO = mockDao.getAccount(accountNumber);

        assertEquals(mockDao.getAccount(accountNumber).getAccountNumber(), accountNumber);
        assertEquals(mockDao.getAccount(accountNumber).getBalance(), 0, 0.001);

        assertEquals(answerDTO,argumentDTO);
    }

    @Test
    public void testDepositedMoneyToAccount() {
        String accountNumber = "1234567890";
        double balance = 0;
        double depositedMoney = 50;
        String log = "deposit 50";

        when(mockDao.getAccount(accountNumber)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String accountNumber2 = "1234567890";
                double balance2 = 0;
                BankAccountDTO answerDTO = new BankAccountDTO(accountNumber2,balance2);
                return answerDTO;
            }
        });

        BankAccount.deposit(accountNumber,depositedMoney,log);

        ArgumentCaptor<BankAccountDTO> savedAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        ArgumentCaptor<String> savedLog = ArgumentCaptor.forClass(String.class);
        verify(mockDao).save(savedAccount.capture(),savedLog.capture());

        assertEquals(savedAccount.getValue().getAccountNumber(),accountNumber);
        assertEquals(savedAccount.getValue().getBalance(), 50 ,0.001);

        assertEquals(savedLog.getValue(),log);
    }

    @Test
    public void testWithdrawMoneyFromAccount() {
        String accountNumber = "1234567890";
        double balance = 100;
        double withdrawMoney = 50;
        String log = "withdraw 50";

        when(mockDao.getAccount(accountNumber)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String accountNumber2 = "1234567890";
                double balance2 = 100;
                BankAccountDTO answerDTO = new BankAccountDTO(accountNumber2,balance2);
                return answerDTO;
            }
        });

        BankAccount.withdraw(accountNumber,withdrawMoney,log);

        ArgumentCaptor<BankAccountDTO> savedAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        ArgumentCaptor<String> savedLog = ArgumentCaptor.forClass(String.class);
        verify(mockDao).save(savedAccount.capture(),savedLog.capture());

        assertEquals(savedAccount.getValue().getAccountNumber(),accountNumber);
        assertEquals(savedAccount.getValue().getBalance(),50,0.001);

        assertEquals(savedLog.getValue(),log);
    }

    @Test
    public void testGetTransactionOccurredByAccountNumber() {
        String accountNumber = "1234567890";
        BankAccount.getTransactionOccurred(accountNumber);

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);

        verify(mockTransactionDao).getTransactionOccurred(accountNumberCaptor.capture());
        assertEquals(accountNumberCaptor.getValue(),accountNumber);

    }
    @Test
    public void testGetAllTransactionOccurredByAccountNumberAdvantage() {
        String accountNumber = "1234567890";
        long startTime = 100000;
        long endTime = 100100;
        BankAccount.getTransactionOccurred(accountNumber,startTime,endTime);

        //Init the return
        ArrayList<TransactionDTO> dummyTransactionDTO = new ArrayList<TransactionDTO>();
        TransactionDTO transactionDTO1 = new TransactionDTO(accountNumber,50,100002,"deposited 50");
        TransactionDTO transactionDTO2 = new TransactionDTO(accountNumber,-50,100005,"withdraw 50");
        TransactionDTO transactionDTO3 = new TransactionDTO(accountNumber,-50,100200,"withdraw 50");
        dummyTransactionDTO.add(transactionDTO1);
        dummyTransactionDTO.add(transactionDTO2);
        dummyTransactionDTO.add(transactionDTO3);

        when(mockTransactionDao.getTransactionOccurred(accountNumber)).thenReturn(dummyTransactionDTO);
        ArrayList<TransactionDTO> transactionList = BankAccount.getTransactionOccurred(accountNumber);
        int i = 0;
        for (TransactionDTO trans : dummyTransactionDTO) {
            assertEquals(trans,transactionList.get(i));
            i++;
        }
    }

    @Test
    public void getTransactionOccurredByAccountNumberAndInTime() {
        String accountNumber = "1234567890";
        long startTime = 100000;
        long endTime = 100100;
        BankAccount.getTransactionOccurred(accountNumber,startTime,endTime);

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> startTimeCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> endTimeCaptor = ArgumentCaptor.forClass(Long.class);

        verify(mockTransactionDao).getTransactionOccurred(accountNumberCaptor.capture(),startTimeCaptor.capture(),endTimeCaptor.capture());
        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(startTimeCaptor.getValue(),startTime,0.001);
        assertEquals(endTimeCaptor.getValue(),endTime,0.001);
    }
}
