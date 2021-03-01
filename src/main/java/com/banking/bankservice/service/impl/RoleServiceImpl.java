package com.banking.bankservice.service.impl;

import com.banking.bankservice.exception.EntityNotFoundException;
import com.banking.bankservice.model.Role;
import com.banking.bankservice.repository.RoleRepository;
import com.banking.bankservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByType(Role.RoleType roleType) {
        return roleRepository.findByRoleType(roleType).orElseThrow(() ->
                new EntityNotFoundException("Role " + roleType + " does not exist"));
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}
