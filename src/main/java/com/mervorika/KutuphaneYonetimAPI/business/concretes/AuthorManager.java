package com.mervorika.KutuphaneYonetimAPI.business.concretes;

import com.mervorika.KutuphaneYonetimAPI.core.utilies.Message;
import com.mervorika.KutuphaneYonetimAPI.dao.IAuthorRepo;
import com.mervorika.KutuphaneYonetimAPI.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IAuthorService;
import com.mervorika.KutuphaneYonetimAPI.core.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorManager implements IAuthorService {
    @Autowired
    private IAuthorRepo authorRepo;
    @Override
    public Author getById(int id) {
        return this.authorRepo.findById(id).orElseThrow(()->new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepo.findAll();
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        this.getById(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        Author author=this.getById(id);
        this.authorRepo.delete(author);
        return true;
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize);
        return this.authorRepo.findAll(pageable);
    }
}
