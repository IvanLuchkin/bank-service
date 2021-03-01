package com.banking.bankservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.banking.bankservice.model.User;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    private static User testUser;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        testUser = new User();
        testUser.setPhoneNumber("123456");
        testUser.setName("test");
        testUser.setDateOfBirth(LocalDate.of(2000, 8, 28));
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void whenInsertedIdAssigned() {
        User savedUser = userRepository.save(testUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void testGetByValidPhoneNumber() {
        userRepository.save(testUser);
        User actualUser = userRepository.getByPhoneNumber("123456").get();
        assertEquals("test", actualUser.getName());
    }

    @Test
    public void testDeletion() {
        userRepository.save(testUser);
        assertEquals(userRepository.count(), 1);
        userRepository.delete(testUser);
        assertEquals(userRepository.count(), 0);
    }
}
