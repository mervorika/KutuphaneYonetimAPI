package com.mervorika.KutuphaneYonetimAPI.api;

import com.mervorika.KutuphaneYonetimAPI.core.config.IModelMapperService;
import com.mervorika.KutuphaneYonetimAPI.core.result.Result;
import com.mervorika.KutuphaneYonetimAPI.core.result.ResultData;
import com.mervorika.KutuphaneYonetimAPI.core.utilies.ResultHelper;
import com.mervorika.KutuphaneYonetimAPI.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import com.mervorika.KutuphaneYonetimAPI.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import com.mervorika.KutuphaneYonetimAPI.dto.response.CursorResponse;
import com.mervorika.KutuphaneYonetimAPI.dto.response.bookBorrowing.BookBorrowingResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IBookBorrowingService;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IBookService;
import com.mervorika.KutuphaneYonetimAPI.entities.Book;
import com.mervorika.KutuphaneYonetimAPI.entities.BookBorrowing;

@RestController
@RequestMapping("/v1/bookborrowings")
public class BookBorrowingController {
    @Autowired
    private IBookBorrowingService bookBorrowingService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save (@Valid @RequestBody BookBorrowingSaveRequest borrowingSaveRequestS){
        BookBorrowing saveBorrowing=this.modelMapper.forRequest().map(borrowingSaveRequestS, BookBorrowing.class);
        Book book=this.bookService.getById(borrowingSaveRequestS.getBook_id());
        saveBorrowing.setBook(book);
        this.bookBorrowingService.save(saveBorrowing);
        BookBorrowingResponse borrowingResponse= this.modelMapper.forResponse().map(saveBorrowing,BookBorrowingResponse.class);
        return ResultHelper.created(borrowingResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update (@Valid @RequestBody BookBorrowingUpdateRequest borrowingUpdateRequest){
        this.bookBorrowingService.getById(borrowingUpdateRequest.getId());
        BookBorrowing updateBorrowing=this.modelMapper.forRequest().map(borrowingUpdateRequest, BookBorrowing.class);
        if (this.bookBorrowingService.update(updateBorrowing)!=null ){
            BookBorrowingResponse borrowingResponse= this.modelMapper.forResponse().map(updateBorrowing,BookBorrowingResponse.class);
            return ResultHelper.success(borrowingResponse);
        }
        return (ResultData<BookBorrowingResponse>) ResultHelper.error("Kitap stoğu olmadığı için kayıt yapılamıyor.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id){
        BookBorrowing borrowing=this.bookBorrowingService.getById(id);
        BookBorrowingResponse borrowingResponse=this.modelMapper.forResponse().map(borrowing,BookBorrowingResponse.class);
        return ResultHelper.success(borrowingResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(@RequestParam(name = "page",required = false,defaultValue = "0") int page, @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize){
        Page<BookBorrowing> borrowingPage=this.bookBorrowingService.cursor(page,pageSize);
        Page<BookBorrowingResponse> borrowingResponsePage=borrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class));
        return ResultHelper.cursor(borrowingResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookBorrowingService.delete(id);
        return ResultHelper.success();
    }

}
