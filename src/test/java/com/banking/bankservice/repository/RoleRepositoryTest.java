package com.banking.bankservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.banking.bankservice.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class RoleRepositoryTest {
    private static final Role TEST_ADMIN_ROLE = new Role(Role.Type.ADMIN);
    private static final Role TEST_USER_ROLE = new Role(Role.Type.USER);
    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    public void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    public void whenInsertedIdAssigned() {
        Role savedRole = roleRepository.save(TEST_ADMIN_ROLE);
        assertNotNull(savedRole.getId());
    }

    @Test
    public void testFindByRoleName() {
        roleRepository.save(TEST_USER_ROLE);
        Role actualRole = roleRepository.findByType(Role.Type.USER).get();
        assertNotNull(actualRole.getId());
    }

    @Test
    public void testDeletion() {
        roleRepository.save(TEST_ADMIN_ROLE);
        assertEquals(roleRepository.count(), 1);
        roleRepository.delete(TEST_ADMIN_ROLE);
        assertEquals(roleRepository.count(), 0);
    }
}
