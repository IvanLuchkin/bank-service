package com.banking.bankservice.repository;

import com.banking.bankservice.model.Account;
import com.banking.bankservice.model.Transaction;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, ObjectId> {
    Optional<Account> findByAccountNumber(String accountNumber);

    @Aggregation(pipeline = {
            "{$lookup: {from: 'users', localField: 'user.$id', foreignField: '_id', as: 'temp'}}",
            "{$match: {'temp': {$elemMatch: {'phoneNumber': ?0}}}}",
            "{$unset: 'temp'}"})
    List<Account> findAccountsByUserPhone(String phoneNumber);

    @Aggregation(pipeline = {
            "{$match: {'_id': ObjectId('?0')}}",
            "{$project: {'transactions': 1, '_id': 0}}",
            "{$unwind: {path: '$transactions'}}",
            "{$replaceRoot: {newRoot: '$transactions'}}"})
    List<Transaction> getPaymentHistory(String accountNumber, final Pageable pageable);
}
