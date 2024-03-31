package com.mervorika.KutuphaneYonetimAPI.business.abstracts;

import com.mervorika.KutuphaneYonetimAPI.entities.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAuthorService {
    Author getById(int id);
    List<Author> getAll();
    Author save(Author author);
    Author update(Author author);
    boolean delete(int id);
    Page<Author> cursor(int page, int pageSize);
}
