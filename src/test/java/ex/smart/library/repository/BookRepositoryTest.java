package ex.smart.library.repository;

import ex.smart.library.model.Book;
import ex.smart.library.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryTest {

    @Autowired
    BookRepo bookRepo;

    @Test
    void findBookByBookName() {
        final String bookNameTest = "Country of the Blind";

        List<Book> book = bookRepo.findBookByBookName(bookNameTest);

        assertNotNull(book);

        boolean isTrue = book.stream().allMatch(a -> a.getBookName().equals(bookNameTest));
        assertTrue(isTrue);
    }


    @Test
    void findBookByCategory() {
        final String findBookByCategoryTest = "Classic";

        List<Book> category = bookRepo.findBookByCategory(findBookByCategoryTest);

        assertNotNull(category);

        boolean isTrue = category.stream().allMatch(a -> a.getCategory().equals(findBookByCategoryTest));
        assertTrue(isTrue);
    }

}
