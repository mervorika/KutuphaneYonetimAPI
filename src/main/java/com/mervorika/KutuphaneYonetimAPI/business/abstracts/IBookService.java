package com.mervorika.KutuphaneYonetimAPI.business.abstracts;

import com.mervorika.KutuphaneYonetimAPI.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Book getById(int id);
    Book save(Book book);

    Book uptade(Book book);

    boolean delete(int id);

    Page<Book> cursor(int page, int pageSize);
}
