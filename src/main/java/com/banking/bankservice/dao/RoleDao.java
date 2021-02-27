package com.banking.bankservice.dao;

import com.banking.bankservice.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleDao extends MongoRepository<Role, String> {
    Role findByRoleTypeEquals(Role.RoleType roleType);
}
