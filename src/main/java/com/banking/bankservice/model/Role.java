package com.banking.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    @Field(targetType = FieldType.STRING)
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public enum RoleType {
        ADMIN, USER;
    }
}
