package com.mervorika.KutuphaneYonetimAPI.business.abstracts;

import com.mervorika.KutuphaneYonetimAPI.entities.BookBorrowing;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookBorrowingService {
    BookBorrowing getById(int id);
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
    List<BookBorrowing> findAll();

}
