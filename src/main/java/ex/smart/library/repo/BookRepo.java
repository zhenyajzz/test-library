package ex.smart.library.repo;

import ex.smart.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book,Integer> {

    public List<Book> findBookByBookName(String book);

    public List<Book> findBookByCategory(String category);


    @Query(value = "SELECT * FROM book_z ORDER BY category DESC",nativeQuery = true)
    public List<Book> findBooksSortedByCategoryDesc();

    @Query(value = "SELECT * FROM book_z ORDER BY book_name ASC",nativeQuery = true)
    public List<Book> findSortedBooksByBookNameAsc();


}
