package com.mervorika.KutuphaneYonetimAPI.api;

import com.mervorika.KutuphaneYonetimAPI.core.result.Result;
import com.mervorika.KutuphaneYonetimAPI.core.result.ResultData;
import com.mervorika.KutuphaneYonetimAPI.core.utilies.ResultHelper;
import com.mervorika.KutuphaneYonetimAPI.dto.response.CursorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IAuthorService;
import com.mervorika.KutuphaneYonetimAPI.entities.Author;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Author> save(@Valid @RequestBody Author author){
        this.authorService.save(author);
        return ResultHelper.created(author);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Author> update(@Valid @RequestBody Author author){
        this.authorService.getById(author.getId());
        this.authorService.update(author);
        return ResultHelper.success(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") int id){
        this.authorService.getById(id);
        this.authorService.delete(id);
        return ResultHelper.success();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Author> get(@PathVariable("id") int id){
        Author author=this.authorService.getById(id);
        return ResultHelper.success(author);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Author>> getAll(){
        List<Author> authors=this.authorService.getAll();
        return ResultHelper.success(authors);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<Author>> cursor(@RequestParam(name = "page",required = false,defaultValue = "0") int page, @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize){
        Page<Author> authorPage=this.authorService.cursor(page,pageSize);
        return ResultHelper.cursor(authorPage);
    }


}
