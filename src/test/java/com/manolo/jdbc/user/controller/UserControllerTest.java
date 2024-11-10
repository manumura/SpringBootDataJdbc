package com.manolo.jdbc.user.controller;

import com.manolo.jdbc.user.dto.CreateUserDto;
import com.manolo.jdbc.user.dto.UpdateUserDto;
import com.manolo.jdbc.user.dto.UserDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class UserControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

//    @Autowired
//    private TestRestTemplate restTemplate;
    private static RestClient restClient;
    @LocalServerPort
    private Integer port;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        restClient = RestClient.create();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    public void testGetUserById() {
        var userResponse = restClient.get().uri("http://localhost:" + port + "/users/1")
                .retrieve()
                .toEntity(UserDto.class);
//        ResponseEntity<UserDto> userResponse =
//                restTemplate.getForEntity("/users/1", UserDto.class);
//        System.out.println("userResponse: " + userResponse);
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());

        var user = userResponse.getBody();
        assertNotNull(user);
        assertEquals(1, user.id());
        assertEquals("john", user.username());
    }

    @Test
    public void testGetAllUsers() {
        var usersResponse = restClient.get().uri("http://localhost:" + port + "/users")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<UserDto>>() {
                });
//        System.out.println("usersResponse: " + usersResponse);
        assertEquals(HttpStatus.OK, usersResponse.getStatusCode());

        var users = usersResponse.getBody();
        assertNotNull(users);
        assertEquals(3, users.size());

        var ids = List.of(1, 2, 3);
        var usernames = List.of("john", "emile", "zinedine");
        users.forEach(user -> {
            assertNotNull(user);
            assertTrue(ids.contains(user.id()));
            assertTrue(usernames.contains(user.username()));
        });
    }

    @Test
    public void testCreateUpdateDelete() {
        var createUserDto = new CreateUserDto("username", "password", "first", "last", "email", LocalDate.of(1981, 8, 14));
        var createUserResponse = restClient.post().uri("http://localhost:" + port + "/users")
                .body(createUserDto)
                .retrieve()
                .toEntity(UserDto.class);
//        System.out.println("createUserResponse: " + createUserResponse);
        assertEquals(HttpStatus.OK, createUserResponse.getStatusCode());

        var userCreated = createUserResponse.getBody();
        assertNotNull(userCreated);
        assertEquals(4, userCreated.id());
        assertEquals("username", userCreated.username());

        var updateUserDto = new UpdateUserDto("usernameupdated", "password", "first", "last", "email", LocalDate.of(2000, 1, 1));
        var updatedUserResponse = restClient.put().uri("http://localhost:" + port + "/users/4")
                .body(updateUserDto)
                .retrieve()
                .toEntity(UserDto.class);
//        System.out.println("updatedUserResponse: " + updatedUserResponse);
        assertEquals(HttpStatus.OK, updatedUserResponse.getStatusCode());

        var userUpdated = updatedUserResponse.getBody();
        assertNotNull(userUpdated);
        assertEquals(4, userUpdated.id());
        assertEquals("usernameupdated", userUpdated.username());
        assertEquals(LocalDate.of(2000, 1, 1), userUpdated.birthdate());

        var deleteUserResponse = restClient.delete().uri("http://localhost:" + port + "/users/4")
                .retrieve()
                .toEntity(UserDto.class);
//        System.out.println("deleteUserResponse: " + deleteUserResponse);
        assertEquals(HttpStatus.NO_CONTENT, deleteUserResponse.getStatusCode());

        try {
            var userResponse = restClient.get().uri("http://localhost:" + port + "/users/4")
                    .retrieve()
//                    .onStatus(httpStatusCode -> httpStatusCode.equals(HttpStatus.NOT_FOUND),
//                            ((request, response) -> {
//                                System.out.println("User not found: " + response.getBody());
//                                throw new RestClientException("User not found");
//                            })
//                    )
                    .toEntity(UserDto.class);
            System.out.println("userResponse: " + userResponse);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            System.out.println("User not found: " + e.getResponseBodyAs(ProblemDetail.class));
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
