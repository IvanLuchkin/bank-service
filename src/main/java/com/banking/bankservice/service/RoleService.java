package com.banking.bankservice.service;

import com.banking.bankservice.model.Role;

public interface RoleService {
    Role getByType(Role.RoleType roleType);

    Role addRole(Role role);
}
