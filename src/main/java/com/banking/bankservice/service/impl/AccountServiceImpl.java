package com.banking.bankservice.service.impl;

import com.banking.bankservice.exception.EntityNotFoundException;
import com.banking.bankservice.model.Account;
import com.banking.bankservice.model.Transaction;
import com.banking.bankservice.repository.AccountRepository;
import com.banking.bankservice.service.AccountService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getByUserPhone(String phoneNumber) {
        return accountRepository.findAccountsByUserPhone(phoneNumber);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account " + accountNumber + " not found"));
    }

    @Override
    public List<Transaction> getPaymentHistory(String accountNumber, int page, int size) {
        return accountRepository.getPaymentHistory(accountNumber, PageRequest.of(page, size));
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void transfer(String senderAccountNumber, String receiverAccountNumber, double amount) {
        Account sender = getByAccountNumber(senderAccountNumber);
        Account receiver = getByAccountNumber(receiverAccountNumber);
        LocalDateTime transactionTimeStamp = LocalDateTime.now().withNano(0);

        Transaction outgoingTransaction = new Transaction(
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                Transaction.Type.OUTGOING, amount,
                transactionTimeStamp);
        Transaction incomingTransaction = new Transaction(
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                Transaction.Type.INCOMING, amount,
                transactionTimeStamp);

        sender.getTransactions().add(outgoingTransaction);
        sender.setBalance(sender.getBalance() - amount);
        receiver.getTransactions().add(incomingTransaction);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
}
