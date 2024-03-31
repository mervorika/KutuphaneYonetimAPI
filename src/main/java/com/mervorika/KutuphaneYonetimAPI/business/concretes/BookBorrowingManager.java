package com.mervorika.KutuphaneYonetimAPI.business.concretes;

import com.mervorika.KutuphaneYonetimAPI.core.utilies.Message;
import com.mervorika.KutuphaneYonetimAPI.dao.IBookBorrowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IBookBorrowingService;
import com.mervorika.KutuphaneYonetimAPI.core.exception.NotFoundException;
import com.mervorika.KutuphaneYonetimAPI.entities.BookBorrowing;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {

    @Autowired
    private IBookBorrowingRepo bookBorrowingRepo;
    @Override
    public BookBorrowing getById(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(()->new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        if (bookBorrowing.getBook().getStock()>0){
            return this.bookBorrowingRepo.save(bookBorrowing);
        }
        return null;
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.getById(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing=this.getById(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize);

        return this.bookBorrowingRepo.findAll(pageable);
    }

    @Override
    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepo.findAll();
    }


}
