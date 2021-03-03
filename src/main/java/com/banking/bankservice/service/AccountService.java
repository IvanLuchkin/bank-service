package com.banking.bankservice.service;

import com.banking.bankservice.model.Account;
import com.banking.bankservice.model.Transaction;
import java.util.List;

public interface AccountService {
    Account add(Account account);

    List<Account> getByUserPhone(String phoneNumber);

    Account getByAccountNumber(String accountNumber);

    List<Transaction> getPaymentHistory(String accountNumber, int page, int size);

    Account update(Account account);

    void transfer(String senderAccountNumber, String receiverAccountNumber, double amount);
}
