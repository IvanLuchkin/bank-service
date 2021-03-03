package com.banking.bankservice.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String accountNumber;
    private Currency currency;
    private double balance;
    private boolean isActive;
    @DBRef
    @ToString.Exclude
    private User user;
    @EqualsAndHashCode.Exclude
    private List<Transaction> transactions;
}
