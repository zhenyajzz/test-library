package ex.smart.library.controller;

import ex.smart.library.model.Book;
import ex.smart.library.repo.BookRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class BookController {

    final
    BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return (List<Book>) bookRepo.findAll();
    }

    @GetMapping("/book/{id}")
    public Book findBookById(@PathVariable Integer id) {
        return bookRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping("/updateBook/{id}")
    public Book updateUser(@PathVariable Integer id, @RequestBody Book book) {
        Book findBook = bookRepo.findById(id).get();
        if (book == null) {
            throw new NoSuchElementException();
        }
        findBook.setBookName(book.getBookName());
        findBook.setCategory(book.getCategory());

        return bookRepo.save(findBook);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBookById(@PathVariable Integer id) {
        bookRepo.deleteById(id);
        return "Delete book with id: " + id;

    }

    @GetMapping("/bookName/{bookName}")
    public List<Book> findBookByBookName(@PathVariable String bookName) {
        return bookRepo.findBookByBookName(bookName);
    }

    @GetMapping("/category/{category}")
    public List<Book> findBookByCategory(@PathVariable String category) {
        return bookRepo.findBookByCategory(category);
    }

    @GetMapping("/orderByCategoryDesc")
    public List<Book> findOrderCategory() {
        return bookRepo.findBooksSortedByCategoryDesc();
    }

    @GetMapping("/orderByBookNameAsc")
    public List<Book> findBooksByBookName() {
        return bookRepo.findSortedBooksByBookNameAsc();
    }
}
