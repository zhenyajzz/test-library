package ex.smart.library.controller;

import ex.smart.library.model.Book;
import ex.smart.library.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    BookController bookController;

    @Autowired
    BookRepo bookRepo;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
                .getForEntity("/api/books", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> responseEntity = this.restTemplate.withBasicAuth("admin", "admin")
                .getForEntity("http://localhost:" + port + "/api/books", Book[].class);
        assertNotNull(responseEntity);

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(bookRepo.findAll(), Arrays.asList(books));

    }

    @Test
    void findBookById() {
        final Integer bookId = 1;
        ResponseEntity<Book> responseEntity = this.restTemplate.withBasicAuth("admin", "admin")
                .getForEntity("http://localhost:" + port + "/api/book/" + bookId, Book.class);
        assertNotNull(responseEntity);

        Book book = bookRepo.findById(bookId).get();

        assertNotNull(book);

        assertEquals(book, responseEntity.getBody());


    }

    @Test
    void findBookByBookName() {
        final String findBookByBookName = "Tom and Jerry";
        ResponseEntity<Book[]> responseEntity = this.restTemplate.withBasicAuth("admin", "admin")
                .getForEntity("http://localhost:" + port + "/api/bookName/" + findBookByBookName, Book[].class);
        assertNotNull(responseEntity);

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertEquals(bookRepo.findBookByBookName(findBookByBookName),
                Arrays.asList(books));


    }
}
