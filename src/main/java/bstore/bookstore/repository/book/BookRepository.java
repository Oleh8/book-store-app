package bstore.bookstore.repository.book;

import bstore.bookstore.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query(value = "SELECT b.* "
            + "FROM books b "
            + "JOIN books_categories bc ON b.id = bc.book_id "
            + "WHERE bc.category_id = :categoryId", nativeQuery = true)
    List<Book> findAllByCategoryId(@Param("categoryId") Long categoryId);

}
