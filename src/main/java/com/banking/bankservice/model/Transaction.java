package com.banking.bankservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Type type;
    private double amount;
    private LocalDateTime date;

    public enum Type {
        OUTGOING,
        INCOMING
    }
}
