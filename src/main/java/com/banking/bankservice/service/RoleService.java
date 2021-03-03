package com.banking.bankservice.service;

import com.banking.bankservice.model.Role;

public interface RoleService {
    Role getByType(Role.Type type);

    Role addRole(Role role);
}
