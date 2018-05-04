package com.waracle.cakemgr;

import com.waracle.cakemgr.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private User userFixture;

    @Test
    public void testGetUsers() {

        ResponseEntity<List<User>> entity = this.restTemplate.exchange(

            "/users",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<User>>() {

        });

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isGreaterThan(0);

    }

    @Test
    public void testCreateUser() {

        User user = new User();

        user.username = "test";
        user.password = "test";

        ResponseEntity<User> entity = this.restTemplate.exchange(

            "/users",
            HttpMethod.POST,
            new HttpEntity<>(user),
            new ParameterizedTypeReference<User>() {

        });

        userFixture = entity.getBody();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(entity.getBody().username).isEqualTo(user.username);
        assertThat(entity.getBody().password).isEqualTo(user.password);

    }

    @Test
    public void testDeleteUser() {

        this.restTemplate.delete("/users/" + userFixture.id);

        ResponseEntity<String> entity = this.restTemplate.getForEntity("/users/" + userFixture.id, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
