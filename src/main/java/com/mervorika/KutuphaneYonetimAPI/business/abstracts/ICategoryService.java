package com.mervorika.KutuphaneYonetimAPI.business.abstracts;

import com.mervorika.KutuphaneYonetimAPI.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Category getById(int id);
    Category save(Category category);
    Category update(Category category);
    boolean delete(int id);
    Page<Category> cursor(int page,int pageSize);

    List<Category> findAll();

    Category assignBookToCategory(int categoryId,int bookId);
}
