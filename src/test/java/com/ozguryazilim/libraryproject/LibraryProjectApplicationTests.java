package com.ozguryazilim.libraryproject;

import com.ozguryazilim.libraryproject.DAO.AuthorDAO;
import com.ozguryazilim.libraryproject.controller.AuthorController;
import com.ozguryazilim.libraryproject.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryProjectApplicationTests {

    @LocalServerPort
    private int port;

    private String localhost = "http://localhost:";

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorController authorController;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(authorController)
                .isNotNull();
    }

    @Test
    void authorPageShouldReturnAuthors() {
        String response = restTemplate
                .getForObject(localhost + port + "/authors", String.class);
        assertThat(response).contains("c");
    }

    @Test
    void shouldFindAuthorByName() {
        AuthorDAO authorDAO = new AuthorDAO("ahmet", "ahmet is ahmet");
        restTemplate
                .postForObject(localhost + port + "/authors/create", authorDAO, String.class);

        assertThat(authorService.findByName("ahmet"))
                .isNotNull();
    }

}
