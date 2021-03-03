package com.banking.bankservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.banking.bankservice.model.Account;
import com.banking.bankservice.model.Currency;
import com.banking.bankservice.model.Transaction;
import com.banking.bankservice.model.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {
    private static User firstTestUser;
    private static User secondTestUser;
    private static Account firstTestAccount;
    private static Account secondTestAccount;
    private static Account thirdTestAccount;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        firstTestUser = new User();
        firstTestUser.setPhoneNumber("123456");
        firstTestUser.setName("first");

        secondTestUser = new User();
        secondTestUser.setPhoneNumber("001001");
        secondTestUser.setName("second");

        firstTestAccount = new Account();
        firstTestAccount.setActive(true);
        firstTestAccount.setBalance(2000);
        firstTestAccount.setCurrency(Currency.EUR);

        secondTestAccount = new Account();
        secondTestAccount.setActive(true);
        secondTestAccount.setBalance(2000);
        secondTestAccount.setCurrency(Currency.EUR);

        thirdTestAccount = new Account();
        thirdTestAccount.setActive(true);
        thirdTestAccount.setBalance(2000);
        thirdTestAccount.setCurrency(Currency.EUR);
    }

    @AfterEach
    public void tearDown() {
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testCreation() {
        userRepository.save(firstTestUser);
        firstTestAccount.setUser(firstTestUser);
        Account savedAccount = accountRepository.save(firstTestAccount);
        assertNotNull(savedAccount.getAccountNumber());
        firstTestAccount.setUser(null);
    }

    @Test
    public void testFindByAccountNumber() {
        String accountNumber = accountRepository.save(firstTestAccount).getAccountNumber();
        assertEquals(firstTestAccount,
                accountRepository.findByAccountNumber(accountNumber).get());
    }

    @Test
    public void testGetPaymentHistory() {
        userRepository.save(firstTestUser);
        firstTestAccount.setUser(firstTestUser);
        accountRepository.save(firstTestAccount);
        accountRepository.save(secondTestAccount);

        Transaction transaction = new Transaction(
                firstTestAccount.getAccountNumber(),
                secondTestAccount.getAccountNumber(),
                Transaction.Type.OUTGOING, 20, LocalDateTime.now().withNano(0));
        firstTestAccount.setTransactions(List.of(transaction));

        accountRepository.save(firstTestAccount);

        assertEquals(List.of(transaction),
                accountRepository.getPaymentHistory(firstTestAccount.getAccountNumber(),
                        Pageable.unpaged()));

        firstTestAccount.setUser(null);
    }

    /**
     "$unset" mongo aggregation function is not supported in Spring Boot 2.4.3 since there is a
     transitive dependency on the embedded mongo 2.2.0, which is based on the old version of
     MongoDB. Spring Boot 2.5.0-M2 has been released two weeks ago, it supports embedded mongo
     of version 3.0.0, which is based on the latest version of MongoDB, but the Gradle
     plugin has not yet been updated, so this test will always fail.

     I have tested it manually with production DB, which is using the latest MongoDB version.
     It works according to the requirements.
     */
    public void fixme_testFindByUserPhoneNumber() {
        userRepository.saveAll(List.of(firstTestUser, secondTestUser));
        firstTestAccount.setUser(firstTestUser);
        secondTestAccount.setUser(secondTestUser);
        thirdTestAccount.setUser(firstTestUser);
        accountRepository.saveAll(List.of(firstTestAccount, secondTestAccount, thirdTestAccount));

        assertEquals(List.of(firstTestAccount, thirdTestAccount),
                accountRepository.findAccountsByUserPhone(firstTestUser.getPhoneNumber()));
    }
}