package com.manolo.jdbc.user.repository;

import com.manolo.jdbc.user.entity.Authority;
import com.manolo.jdbc.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
// do not replace the testcontainer data source
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("integration-test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @Test
    public void testUserCRUD() {

        // save user
        var user = new User(
                null,
                "username",
                "password",
                true,
                true,
                true,
                true,
                "John",
                "Doe",
                "email",
                LocalDate.of(1981, 8, 14),
                Set.of()
        );
        var userCreated =  userRepository.save(user);

        // find user
        Optional<User> result = userRepository.findById(userCreated.id());
        assertTrue(result.isPresent());

        var userFetched = result.get();
        System.out.println("userFetched: " + userFetched);
        assertEquals("email", user.emailAddress());
        assertEquals("username", userFetched.username());

        // save authority
        var authority = new Authority(null, "ROLE_TEST");
        var authorityCreated = authorityRepository.save(authority);

        // update user with authority
        var newUser = new User(
                userFetched.id(),
                userFetched.username(),
                userFetched.password(),
                userFetched.accountNonExpired(),
                userFetched.accountNonLocked(),
                userFetched.credentialsNonExpired(),
                userFetched.enabled(),
                "firstName",
                "lastName",
                userFetched.emailAddress(),
                userFetched.birthdate(),
                new HashSet<>()
        );
        newUser.addAuthorityRef(authorityCreated.id());
        var userUpdated = userRepository.save(newUser);
        System.out.println("userUpdated: " + userUpdated);

        assertEquals("firstName", userUpdated.firstName());
        assertEquals("lastName", userUpdated.lastName());
        assertEquals(1, userUpdated.authorityRefs().size());

        var authorityIds = userUpdated.authorityRefs().stream().map(userAuthority -> userAuthority.authorityId().getId()).toList();
        assertEquals(authorityIds.getFirst(), authorityCreated.id());

        userUpdated.removeAuthorityRef(authorityCreated.id());
        var userUpdatedWithoutAuthority = userRepository.save(userUpdated);
        assertEquals(0, userUpdatedWithoutAuthority.authorityRefs().size());

        // delete user
        userRepository.deleteById(userUpdatedWithoutAuthority.id());

        // find user deleted
        Optional<User> resultDeleted = userRepository.findById(userCreated.id());
        assertTrue(resultDeleted.isEmpty());
    }
}
