package com.mervorika.KutuphaneYonetimAPI.api;

import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IAuthorService;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IBookService;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IPublisherService;
import com.mervorika.KutuphaneYonetimAPI.core.config.IModelMapperService;
import com.mervorika.KutuphaneYonetimAPI.core.result.Result;
import com.mervorika.KutuphaneYonetimAPI.core.result.ResultData;
import com.mervorika.KutuphaneYonetimAPI.core.utilies.ResultHelper;
import com.mervorika.KutuphaneYonetimAPI.dto.request.book.BookSaveRequest;
import com.mervorika.KutuphaneYonetimAPI.dto.response.book.BookResponse;
import com.mervorika.KutuphaneYonetimAPI.entities.Author;
import com.mervorika.KutuphaneYonetimAPI.entities.Book;
import com.mervorika.KutuphaneYonetimAPI.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.*;
import com.mervorika.KutuphaneYonetimAPI.entities.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Autowired
    IBookService bookService;
    @Autowired
    private IModelMapperService modelMapper;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IPublisherService publisherService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save (@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook=this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        Author author=this.authorService.getById(bookSaveRequest.getAuthor_Id());
        saveBook.setAuthor(author);
        Publisher publisher=this.publisherService.getById(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);
        this.bookService.save(saveBook);
        BookResponse bookResponse= this.modelMapper.forResponse().map(saveBook,BookResponse.class);
        return ResultHelper.created(bookResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Book update(@RequestBody BookResponse bookResponse){
        Book updateBook=this.bookService.getById(bookResponse.getId());
        updateBook.setName(bookResponse.getName());
        updateBook.setStock(bookResponse.getStock());
        updateBook.setPublicationYear(bookResponse.getPublicationYear());
        return this.bookService.uptade(updateBook);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookService.delete(id);
        return ResultHelper.success();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> getById(@PathVariable("id") int id){
        Book book=this.bookService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book,BookResponse.class));
    }


}
