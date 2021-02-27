package com.banking.bankservice.dao;

import com.banking.bankservice.model.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleDao extends MongoRepository<Role, ObjectId> {
    Role findByRoleTypeEquals(Role.RoleType roleType);
}
