package com.banking.bankservice.service.impl;

import com.banking.bankservice.dao.RoleDao;
import com.banking.bankservice.model.Role;
import com.banking.bankservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getByType(Role.RoleType roleType) {
        return roleDao.findByRoleTypeEquals(roleType);
    }

    @Override
    public Role addRole(Role role) {
        return roleDao.save(role);
    }
}
