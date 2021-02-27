package com.banking.bankservice.dao;

import com.banking.bankservice.model.Role;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleDao extends MongoRepository<Role, ObjectId> {
    Optional<Role> findByRoleTypeEquals(Role.RoleType roleType);
}
