package com.banking.bankservice.repository;

import com.banking.bankservice.model.Role;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Optional<Role> findByRoleType(Role.RoleType roleType);
}
