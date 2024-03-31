package com.mervorika.KutuphaneYonetimAPI.business.concretes;

import com.mervorika.KutuphaneYonetimAPI.core.utilies.Message;
import com.mervorika.KutuphaneYonetimAPI.dao.IBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IBookService;
import com.mervorika.KutuphaneYonetimAPI.core.exception.NotFoundException;
import com.mervorika.KutuphaneYonetimAPI.entities.Book;

@Service
public class BookManager implements IBookService {
    @Autowired
    private IBookRepo bookRepo;
    @Override
    public Book getById(int id) {
        return this.bookRepo.findById(id).orElseThrow(()->new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book uptade(Book book) {
        this.getById(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(int id) {
        Book book=this.getById(id);
        this.bookRepo.delete(book);
        return true;
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize);

        return this.bookRepo.findAll(pageable);
    }

}
