package com.banking.bankservice.service;

import com.banking.bankservice.model.Account;
import com.banking.bankservice.model.Transaction;
import java.util.List;

public interface AccountService {
    List<Account> getByUserPhone(String phoneNumber);

    Account getByAccountNumber(String accountNumber);

    List<Transaction> getPaymentHistory(String accountNumber, int page, int size);

    void blockAccount(String accountNumber);

    void transfer(String senderAccountNumber, String receiverAccountNumber, double amount);
}
